package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Utilities {
	
	
	public static String loadAsString(String location){
		StringBuilder result = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(location));
			String buffer = "";
			while((buffer = reader.readLine())!= null){
				result.append(buffer);
				result.append("\n");
			}
			reader.close();
			
		} catch(IOException e){
			System.err.println(e);
		}
		return result.toString();
	}

	public static ByteBuffer createByteBuffer(byte[] array){
		ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		result.put(array).flip();
		return result;
	}
	
	public static FloatBuffer createFloatBuffer(float[] array){
		FloatBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		result.put(array).flip();
		return result;
	}
	
	public static IntBuffer createIntBuffer(int[] array){
		IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		result.put(array).flip();
		return result;
	}
	
}
