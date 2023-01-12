#version 330 core

layout (location = 0) in vec3 aVertexPosition_modelSpace;

void main() {
	gl_Position.xyz = aVertexPosition_modelSpace;
	gl_Position.w = 1.0;
}
