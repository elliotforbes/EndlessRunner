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
	
	private static String[] texPaths = new String[]{
		"assets/tile.png"
	};
	
	public Tile() {
		super(vertices, indices, texCoords, texPaths);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(){
		tex[0].bind();
		Shader.shader2.enable();
		Shader.shader2.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
		VAO.render();
		Shader.shader2.disable();
		tex[0].unbind();
	}
	
}
