package Util;

import static org.lwjgl.opengl.GL11.*;
// the GL20 library allows us to use most
// opengl shader things.
import static org.lwjgl.opengl.GL20.*;

public class ShaderUtils {
	
	private ShaderUtils(){
	}

	public static int load(String vertPath, String fragPath){
		// These lines of code first take in the file path 
		// for both our vertex shader and our fragment shader
		// and then create a string containing all 
		// of the source code of both shaders and put them 
		// into vert and frag. These will later be passed into
		// our created shader objects in our create() function
		String vert = Utilities.loadAsString(vertPath);
		String frag = Utilities.loadAsString(fragPath);
		return create(vert, frag);
	}
	
	public static int create(String vert, String frag){
		// creates a program object and assigns it to the
		// variable program.
		int program = glCreateProgram();
		// glCreateShader specificies the type of shader
		// that we want created. For the vertex shader 
		// we define it as GL_VERTEX_SHADER
		int vertID = glCreateShader(GL_VERTEX_SHADER);
		// Specificies that we want to create a 
		// GL_FRAGMENT_SHADER
		int fragID = glCreateShader(GL_FRAGMENT_SHADER);
		// glShaderSource replaces the source code in a shader
		// object.
		// We've defined our vertex shader object and now
		// we want to pass in our vertex shader that we
		// managed to build as a string in our load
		// function.
		//
		glShaderSource(vertID, vert);
		// does the same for our fragment shader
		glShaderSource(fragID, frag);
		
		// This group of code tries to compile our shader object
		// it then gets the status of that compiled shader and
		// if it proves to be false then it prints an error to 
		// the command line.
		glCompileShader(vertID);
		if(glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE){
			System.err.println("Failed to compile vertexd shader!");
			System.err.println(glGetShaderInfoLog(vertID));
		}
		
		// This group of code tries to compile our shader object
		// it then gets the status of that compiled shader and
		// if it proves to be false then it prints an error to 
		// the command line.		
		glCompileShader(fragID);
		if(glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE){
			System.err.println("Failed to compile fragment shader!");
			System.err.println(glGetShaderInfoLog(fragID));
		}
		
		
		// This attaches our vertex and fragment shaders
		// to the program object that we defined at the
		// start of this tutorial.
		glAttachShader(program, vertID);
		glAttachShader(program, fragID);
		// this links our program object
		glLinkProgram(program);
		// 
		glValidateProgram(program);
		
		// this then returns our created program
		// object.
		return program;
	}
	
}
