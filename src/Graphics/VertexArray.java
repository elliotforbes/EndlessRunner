package Graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import Util.Utilities;

public class VertexArray {
	
	private int VAO, VBO, IBO, TCBO;
	
	private int count;
	
	public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates){
		count = indices.length;
		
        // this initializes our Vertex Array Object - VAO
        // now all we have to do is create buffer objects
        // for our vertices, indices and textureCoordinates
        // and then bind them to our VAO
		VAO = glGenVertexArrays();
		glBindVertexArray(VAO);
		
		VBO = glGenBuffers();
		// binds a named buffer object, in this case our
		// VBO
		glBindBuffer(GL_ARRAY_BUFFER, VBO);
		// creates and initializes a buffer objects' data store
		// aka it passes in our vertices array into our newly 
		// created Vertex Buffer Object.
		glBufferData(GL_ARRAY_BUFFER, Utilities.createFloatBuffer(vertices),  GL_STATIC_DRAW);
		// This specifies the characteristics of the data we've just passed in
		// Shader.VERTEX_ATTRIB is equal to 0 in this case and it sets the ID of 
		// this VBO to 0 or our first position.
		// '3' then represents the 3 points that make up each vertex (x, y, z)
		// 'GL_FLOAT' specifies the type of data
		// and false signifies that we do not want this data to be normalised
		// For the purpose of this tutorial you don't have to worry about the
		// last two parameters.
		glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
		// This line of code enables our vertex attribute array at position 0
		glEnableVertexAttribArray(0);
		
		
		// We follow the same pattern as above for our 
		// texture coordinates Vertex Buffer Object.
		TCBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, TCBO);
		glBufferData(GL_ARRAY_BUFFER, Utilities.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
		// Note this time that we are passing in Shader.TEXTURE_COORDS_ATTRIB
		// which we have defined as the value 1 (the second value in our array which stats
		// at position 0.
		// We also pass in the value 2 instead of 3 as texture coordinates are 
		// represented in 2D and not 3D like our vertices.
		glVertexAttribPointer(Shader.TEXTURE_COORDS_ATTRIB, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.TEXTURE_COORDS_ATTRIB);
		
		// Again we follow a similar pattern to above
		IBO = glGenBuffers();
		// This time however we are defining our buffer
		// as a GL_ELEMENT_ARRAY_BUFFER instead of our
		// normal GL_ARRAY_BUFFER
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
		// We are passing in a byteBuffer object storing all our indices here instead
		// of a floatbuffer as we had above.
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Utilities.createByteBuffer(indices), GL_STATIC_DRAW);
		
		
		// Lastly we want to unbind all our buffers
		// Another tutorial describes this as 
		// unselecting a layer in photoshop
		// so that you can effectively work on other
		// layers.
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	public void bind(){
		glBindVertexArray(VAO);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
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
