package Game;

import Graphics.Shader;
import Graphics.Texture;
import Input.Input;
import Math.Matrix4f;
import Math.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

public class Player extends GameObject{

	public static float width = 0.8f;
	public static float height = 0.8f;
	public Vector3f delta = new Vector3f();
	
	public boolean running = false;
	public boolean jumping = false;
	public boolean idle = true;
	public boolean walking = false;
	
	
	public int spritePos = 0;
	public int counter = 0;
	public int animState = 0;
	
	
	private static float[] vertices = new float[]{
			-width, -height, 0.2f,
			-width,  height, 0.2f,
			  width,  height, 0.2f,
			  width, -height, 0.2f
	};
	
	private static float[] texCoords = new float[]{
			0, 1,
			0, 0,
			1, 0,
			1, 1
	};
	
	private static byte[] indices = new byte[]{
			0,1,2,
			2,3,0
	};
	
	private static String[] texPaths = {
		"assets/Player/PlayerBox.png"
		
	};
	
	public Player(){
		super(vertices, indices, texCoords, texPaths);
	}
	
	public boolean checkFloorCollision(){
		if(position.y <= 3.0f){
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void render(){
		
		tex[0].bind();
		Shader.shader1.enable();
		Shader.shader1.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		Shader.shader1.disable();
		tex[0].unbind();
				
	}
	
	@Override
	public void update(){
		position.y -= delta.y;
		position.x -= delta.x;
		
		
   		if(position.y <= -2.75f){
			position.y = -2.75f;
   			animState = 0;
		}
		if(Input.isKeyDown(GLFW_KEY_SPACE)){
			delta.y = -0.15f;
			animState = 2;
			jumping = true;
		}
		else if(Input.isKeyDown(GLFW_KEY_D)){
			delta.x = -0.15f;
			idle = false;
			running = true;
			animState = 1;
		}
		else if(Input.isKeyDown(GLFW_KEY_A)){
			delta.x = 0.15f;
			idle = false;
			running = true;
			animState = 1;  
		}
		else{
//			animState = 0;
			delta.y += 0.01f;
			delta.x = 0.0f;
		}
		
		
	
	}

	
}
