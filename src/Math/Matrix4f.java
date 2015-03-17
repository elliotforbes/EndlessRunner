package Math;

import java.nio.FloatBuffer;

import Util.Utilities;

public class Matrix4f {

	public static final int SIZE = 4 * 4;
	public float[] elements = new float[SIZE];
	
	public Matrix4f(){ }
	
	public static Matrix4f identity(){
		// Initialize a new matrix
		Matrix4f matrix = new Matrix4f();
		
		// The Identity Matrix:
		// [ 1 , 0 , 0 , 0 ]
		// [ 0 , 1 , 0 , 0 ]
		// [ 0 , 0 , 1 , 0 ]
		// [ 0 , 0 , 0 , 1 ]
		matrix.elements[0 + 0 * 4] = 1.0f;
		matrix.elements[1 + 1 * 4] = 1.0f;
		matrix.elements[2 + 2 * 4] = 1.0f;
		matrix.elements[3 + 3 * 4] = 1.0f;
		
		// Return our created Identity Matrix
		return matrix;
	}
	
	
	// Gives us our orthographic matrix
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far){
		Matrix4f matrix = new Matrix4f();
		
		matrix.elements[0 + 0 * 4] = 2.0f / (right - left);
		matrix.elements[1 + 1 * 4] = 2.0f / (top - bottom);
		matrix.elements[2 + 2 * 4] = 2.0f / (near - far);

		matrix.elements[0 + 3 * 4] = (left + right) / (left - right);
		matrix.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
		matrix.elements[2 + 3 * 4] = (far + near) / (far - near);
		
		matrix.elements[3 + 3 * 4] = 1.0f;
		
		return matrix;
	}
	
	// this translation function basically gives us the ability to
	// move any of our objects by simply inputing a vector3f to it.
	// Say you wanted to move an object 5 places to the right, you'd
	// simple add a vector3f which equalled (0.0f, 5.0f, 0.0f) to your
	// matrix and it would translate it 5 places to the right.
	public static Matrix4f translate(Vector3f vector){
		Matrix4f matrix = identity();
		matrix.elements[0 + 3 * 4] = vector.x;
		matrix.elements[1 + 3 * 4] = vector.y;
		matrix.elements[2 + 3 * 4] = vector.z;
		return matrix;
	}
	
	// Our rotation function does exactly what it says on 
	// the tin. We give it the angle with which we want to
	// rotate our object and it converts it to radians and 
	// performs a matrix rotation given the cos and sin
	// values computed from this radian.
	public static Matrix4f rotate(float angle){
		Matrix4f matrix = identity();
		float r = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(r);
		float sin = (float) Math.sin(r);
		
		matrix.elements[0 + 0 * 4] = cos;
		matrix.elements[1 + 0 * 4] = sin;
		matrix.elements[0 + 1 * 4] = -sin;
		matrix.elements[1 + 1 * 4] = cos;		
		return matrix;
	}
	
	
	// Our Matrix multiplication function which
	// gives us the ability to multiply any given
	// matrix using the mathematical rules for matrix
	// manipulation. 
	//
	// Matrix multiplication is quite complex so I
	// recommend you watch a few videos explaining
	// how it works first.
	public Matrix4f multiply(Matrix4f matrix){
		Matrix4f result = new Matrix4f();
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				float sum = 0.0f;
				for(int e = 0; e < 4; e++){
					sum += this.elements[x + e * 4] * matrix.elements[e + y * 4];
				}
				result.elements[x + y * 4] = sum;
			}
		}
		return result;
	}
	
	public FloatBuffer toFloatBuffer(){
		return Utilities.createFloatBuffer(elements);
	}
	
	
}
