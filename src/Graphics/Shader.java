package Graphics;

import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

import Math.Matrix4f;
import Math.Vector3f;
import Util.ShaderUtils;

public class Shader {
	// We used these variables to set where we 
    // would be storing both our vertex and texture
    // coordinates in our Vertex Array Object!
	public static final int VERTEX_ATTRIB = 0;
	public static final int TEXTURE_COORDS_ATTRIB = 1;
	// our way to identify what shader this is.
    private final int ID;
    // A performance increasing tactic taught I learned from 
    // @TheCherno - You should definitely check out his
    // tutorials as I based the starting code on some of 
    // the stuff I learned from his videos!
    private Map<String, Integer> locationCache = new HashMap<String, Integer>();
    
    private boolean enabled = false;
    
    public static Shader shader1;
	
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
	
	public static void loadAll(){
		shader1 = new Shader("shaders/bg.vert", "shaders/bg.frag");
	}
	
	public static void loadShader(String vertShader, String fragShader){
		new Shader(vertShader, fragShader);
	}
	
	public void setUniform1i(String name, int value) {
		if (!enabled) enable();
		glUniform1i(getUniform(name), value);
	}
	
	public void setUniform1f(String name, float value) {
		if (!enabled) enable();
		glUniform1f(getUniform(name), value);
	}
	
	public void setUniform2f(String name, float x, float y) {
		if (!enabled) enable();
		glUniform2f(getUniform(name), x, y);
	}
	
	public void setUniform3f(String name, Vector3f vector) {
		if (!enabled) enable();
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
	
	public void setUniformMat4f(String name, Matrix4f matrix) {
		if (!enabled) enable();
		glUniformMatrix4(getUniform(name), false, matrix.toFloatBuffer());
	}
	
	public void enable() {
		glUseProgram(ID);
		enabled = true;
	}
	
	public void disable() {
		glUseProgram(0);
		enabled = false;
	}

}