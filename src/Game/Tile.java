package Game;

import Graphics.Shader;
import Math.Matrix4f;
import Math.Vector3f;

public class Tile extends GameObject{

	public static float width = 1.2f;

	public static float height = 1.0f;
	
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
	
	public Tile() {
		super(vertices, indices, texCoords, "assets/tile.png");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(){
		tex.bind();
		Shader.shader1.enable();
		for(int i = 0; i< 20; i++){
			Shader.shader1.setUniformMat4f("ml_matrix", Matrix4f.translate(new Vector3f(-10.0f + (i + 1.0f), -4.7f, 0.0f)));
			VAO.render();
		}
		Shader.shader1.disable();
		tex.unbind();
	}
	
}
