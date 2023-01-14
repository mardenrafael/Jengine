package core.Jengine.components;

import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgramiv;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderiv;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import core.Jengine.nodes.Node;
import core.utils.Logger;

public class ShaderLoader extends Node {

	private final String vertexFilePath;
	private final String fragmentFilePath;
	private Integer vertexShaderID;
	private Integer fragmentShaderID; 
	private Integer programID;
	
	public ShaderLoader(String vertexFilePath, String fragmentFilePath) {
		super(null);
		this.vertexFilePath = vertexFilePath;
		this.fragmentFilePath = fragmentFilePath;
	}
		
	public void createProgram() throws Exception {
		this.programID = glCreateProgram();

		linkProgram(programID, vertexShaderID, fragmentShaderID);
		glDetachShader(programID, this.vertexShaderID);
		glDetachShader(programID, this.fragmentShaderID);

		glDeleteShader(this.vertexShaderID);
		glDeleteShader(this.fragmentShaderID);
	}

	public void loadShaders() throws FileNotFoundException, IOException, Exception {
		loadShaders(this.vertexFilePath, this.fragmentFilePath);
	}
	
	private void loadShaders(final String vertexFilePath, final String fragmentFilePath) throws FileNotFoundException, IOException, Exception {
		this.vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
		this.fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);


		String vertexShaderSourceCode = "";
		String fragmentShaderSourceCode = "";
		vertexShaderSourceCode = readShader(vertexFilePath, "vertex");
		fragmentShaderSourceCode = readShader(fragmentFilePath, "fragment");

		compileShader(vertexShaderID, vertexShaderSourceCode, "vertex");
		compileShader(fragmentShaderID, fragmentShaderSourceCode, "fragment");
	}

	private void linkProgram(Integer programID, Integer vertexShaderID, Integer fragmentShaderID) throws Exception {

		Logger.debugLog("Linking program...");
		glAttachShader(programID, vertexShaderID);
		glAttachShader(programID, fragmentShaderID);
		glLinkProgram(programID);

		Logger.debugLog("Done!");
		checkIntegrity(programID);
	}

	/**
	 * @param programID
	 * @return false if program link has error
	 * @throws Exception 
	 */
	private void checkIntegrity(Integer programID) throws Exception {
		Logger.debugLog("Checking program...");
		IntBuffer result = ByteBuffer.allocateDirect(4).asIntBuffer();
		int status;

		glGetProgramiv(programID, GL_LINK_STATUS, result);
		status = result.get(0);

		if (status == 0) {
			glGetProgramiv(programID, GL_INFO_LOG_LENGTH, result);
			status = result.get(0);

			if (status > 1) {
				Logger.logError("Program log: " + glGetProgramInfoLog(programID), true);
			}
			throw new Exception();
		}
		Logger.debugLog("Done!");
	}

	/**
	 * @param shaderID
	 * @param shaderType
	 * @return false if shader compile has error
	 */
	private void checkIntegrity(Integer shaderID, String shaderType) throws Exception {
		IntBuffer result = ByteBuffer.allocateDirect(4).asIntBuffer();
		int status;

		Logger.debugLog("Checking " + shaderType + " shader status");
		glGetShaderiv(shaderID, GL_COMPILE_STATUS, result);
		status = result.get(0);

		if (status == 0) {

			glGetShaderiv(shaderID, GL_INFO_LOG_LENGTH, result);

			status = result.get(0);

			if (status > 1) {
				Logger.logError("shader log: " + glGetShaderInfoLog(shaderID), true);

				glDeleteShader(shaderID);
			}
			throw new Exception();
		}
		Logger.debugLog("Success!");
	}

	/**
	 * 
	 * @param shaderID Shader id for compile
	 * @param sourceCode Shader code
	 * @param shaderType Shader type just for correct logs
	 * @return false if compile has an error
	 * @throws Exception 
	 */
	private void compileShader(Integer shaderID, String sourceCode, String shaderType) throws Exception {

		Logger.debugLog("Compiling " + shaderType + " shader...");
		glShaderSource(shaderID, sourceCode);
		glCompileShader(shaderID);
		Logger.debugLog("Done!");
		checkIntegrity(shaderID, shaderType);
	}

	private String readShader(String filePath, String shaderType) throws FileNotFoundException, IOException {
		Logger.debugLog("Reading " + shaderType + " shader...");
		return readShader(filePath);
	}

	/**
	 * 
	 * @param path Path to file which will read
	 * @return file content
	 * @throws FileNotFoundException
	 */
	private String readShader(String path) throws FileNotFoundException, IOException {

		StringBuilder fileData = new StringBuilder();
		FileReader fileToRead = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileToRead);
		String buffer = "";

		while((buffer = bufferedReader.readLine()) != null) {
			fileData.append(buffer + "\n");
		}
		bufferedReader.close();
		return fileData.toString();
	}

	public Integer getProgramID() {
		return programID;
	}
}
