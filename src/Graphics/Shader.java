package Graphics;

import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

import Math.Matrix4f;
import Math.Vector3f;
import Util.ShaderUtils;

public class Shader {

	public static final int VERTEX_ATTRIB = 0;
	public static final int TEXTURE_COORDS_ATTRIB = 1;
	
	private final int ID;
	private Map<String, Integer> locationCache = new HashMap<String, Integer>();
	
	public Shader(String vertex, String fragment){
		ID = ShaderUtils.load(vertex, fragment);
	}
	
	public int getUniform(String name){
		if (locationCache.containsKey(name)){
			return locationCache.get(name);
		}
		int result = glGetUniformLocation(ID, name);
		if(result == -1)
			System.err.println("Could not find uniform variable'" + name + "'!");
		else
			locationCache.put(name, result);
		return glGetUniformLocation(ID, name);
	}
	
	public void setUniform1i(String name, int value){
		glUniform1i(getUniform(name), value);
	}
	
	public void setUniform2f(String name, float x, float y){
		glUniform2f(getUniform(name), x, y);
	}
	
	public void setUniform3f(String name, Vector3f vector){
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
	
	public void setUniformMat4f(String name, Matrix4f matrix){
		glUniformMatrix4(getUniform(name), false, matrix.toFloatBuffer());
	}
	
	public void enable(){
		glUseProgram(ID);
	}
	
	public void disable(){
		glUseProgram(0);
	}
	
}
