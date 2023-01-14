package core.Jengine.nodes;

import core.Jengine.components.ShaderLoader;
import core.Jengine.nodes2D.Shape2D;

public class Triangle extends Shape2D {
	
	public Triangle(float[] vertexArray, ShaderLoader shaderLoader, String name) {
		super(vertexArray, shaderLoader, name);		
	}
}
