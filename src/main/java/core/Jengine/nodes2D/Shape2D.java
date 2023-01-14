package core.Jengine.nodes2D;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import core.Jengine.components.ShaderLoader;
import core.Jengine.nodes.Node;
import core.utils.Logger;

public abstract class Shape2D extends Node {
	private int vbo;
	protected int vao;
	private int program;
	private ShaderLoader shaderLoader = null; 
	private float[] vertices;

	public Shape2D(float[] vertexArray, ShaderLoader shaderLoader, String name) {
		super(name);
		this.vertices = vertexArray;	

		if (shaderLoader != null) {
			this.shaderLoader = shaderLoader;
		}

		if (this.shaderLoader != null) {			
			try {
				Logger.debugLog("Load shader for: " + getName());

				this.shaderLoader.loadShaders();
				this.shaderLoader.createProgram();

				this.program = this.shaderLoader.getProgramID();
				Logger.debugLog("Loaded shader for: " + getName());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		createVao();
		createVbo();	
		
		Logger.debugLog("Created shape: " + getName());
	}

	private void createVao() {
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
	}

	private void createVbo() {
		vbo  = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
	}

	abstract protected void bindAndPrepareVao();
	
	public void draw() {

		glUseProgram(program);

		
		
		this.bindAndPrepareVao();

		glDrawArrays(GL_TRIANGLES, 0, 3);
		glDisableVertexAttribArray(0);
	}
}
