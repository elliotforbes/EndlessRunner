package Game;

import Graphics.Shader;
import Math.Matrix4f;
import Math.Vector3f;

public class Coin extends GameObject{

	public static float width = 0.6f;

	public static float height = 0.5f;
	
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
	
	public Coin() {
		super(vertices, indices, texCoords, "assets/coin2.png");
		// TODO Auto-generated constructor stub
		super.translate(new Vector3f(0.0f, -2.4f, 0.0f));
	}

	@Override
	public void update(){
		sinUpdate();
		delta += 0.1f;
	}
	
	@Override
	public void render(){
		tex.bind();
		Shader.shader1.enable();
		Shader.shader1.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		Shader.shader1.disable();
		tex.unbind();
	}

}
