package core.Jengine.nodes;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import core.Jengine.components.ShaderLoader;
import core.Jengine.nodes2D.Shape2D;

public class Triangle extends Shape2D {
	
	public Triangle(float[] vertexArray, ShaderLoader shaderLoader, String name) {
		super(vertexArray, shaderLoader, name);		
	}

	@Override
	protected void bindAndPrepareVao() {
		glEnableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, vao);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
	}
}
