package Game;

import org.lwjgl.glfw.GLFW;

import Graphics.Shader;
import Graphics.Texture;
import Graphics.VertexArray;
import Math.Matrix4f;
import Math.Vector3f;
import Input.Input;

public class GameObject {

	public String texPath;
	public VertexArray VAO;
	public Texture tex;
	public float[] vertices, texCoords;
	public byte[] indices;
	public static Shader shader;
	
	public Vector3f position = new Vector3f();
	
	public float delta = 0.01f;
	
	public GameObject(float[] vertices, byte[] indices, float[] texCoords, String texPath){	
		this.texPath = texPath;
		this.vertices = vertices;
		this.indices = indices;
		this.texCoords = texCoords;
		tex = new Texture(texPath);
		VAO = new VertexArray(this.vertices, this.indices, this.texCoords);
	}
	
	public void loadShader(){
		shader = new Shader("shaders/bg.vert", "shaders/bg.frag");
	}
	
	public void translate(Vector3f vector){
		position.x += vector.x;
		position.y += vector.y;
		position.z += vector.z;
	}

	public void sinUpdate(){
		position.y += (float) Math.sin(delta)/105.0f;
	}
	
	public void render(){
		// TODO Auto-generated method stub
		tex.bind();
		Shader.shader1.enable();
		Shader.shader1.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		Shader.shader1.disable();
		tex.unbind();
		
	}
	
	public void update(){
		sinUpdate();
		delta += 0.1f;
	}
	
	
}
