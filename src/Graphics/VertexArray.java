package Graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import Util.Utilities;

public class VertexArray {
	
	private int vao, vbo, ibo, tco;
	
	private int count;
	
	public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates){
		count = indices.length;
	
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Utilities.createFloatBuffer(vertices),  GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		
		tco = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tco);
		glBufferData(GL_ARRAY_BUFFER, Utilities.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.TEXTURE_COORDS_ATTRIB, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.TEXTURE_COORDS_ATTRIB);
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utilities.createByteBuffer(indices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	public void bind(){
		glBindVertexArray(vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
	}
	
	public void unbind(){
		glBindVertexArray(0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	public void draw(){
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
	}
	
	public void render(){
		bind();
		draw();
	}
	
	
	
}
