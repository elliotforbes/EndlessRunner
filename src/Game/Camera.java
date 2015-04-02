package Game;

import Math.Matrix4f;
import Math.Vector3f;
import Graphics.Shader;
import Input.Input;
import static org.lwjgl.glfw.GLFW.*;

public class Camera {

	public Vector3f position = new Vector3f();
	public Vector3f offset = new Vector3f();
	
	float rot = 0.0f;
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(){
		offset = new Vector3f(7.0f, 3.5f, 0.0f);
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f pos){
		this.position.x = pos.x + offset.x;
		this.position.y = pos.y + offset.y;
		this.position.z = pos.z + offset.z;
	}
	

	public void update(){
	
	}


	public void render(){
		Shader.shader1.enable();
		Shader.shader1.setUniformMat4f("vw_matrix", Matrix4f.translate(
				new Vector3f(-position.x, -position.y, -position.z)));	
		Shader.shader1.disable();
	}
	
}
