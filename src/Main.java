import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GLContext;

import Game.Camera;
import Game.Coin;
import Game.Game;
import Game.Player;
import Game.Tile;
import Graphics.Shader;
import Input.Input;
import Math.Matrix4f;
import Math.Vector3f;

public class Main implements Runnable{
	
	private Thread thread;
	public boolean running = true;
	
	private long window;
	
	private int width = 1200, height = 800;
	
	public Game game;

	private GLFWKeyCallback keyCallback;	
	
	public static void main(String args[]){
		
		Main game = new Main();
		game.start();
		
		
	}
	
	public void start(){
		running = true;
		thread = new Thread(this, "EndlessRunner");
		thread.start();
	}

	public void init(){
		// Initializes our window creator library - GLFW 
		// This basically means, if this glfwInit() doesn't run properlly
		// print an error to the console
		if(glfwInit() != GL_TRUE){
			// Throw an error.
			System.err.println("GLFW initialization failed!");
		}
		
		// Allows our window to be resizable
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		// Creates our window. You'll need to declare private long window at the
		// top of the class though. 
		// We pass the width and height of the game we want as well as the title for
		// the window. The last 2 NULL parameters are for more advanced uses and you
		// shouldn't worry about them right now.
		window = glfwCreateWindow(width, height, "Endless Runner", NULL, NULL);
	
		// This code performs the appropriate checks to ensure that the
		// window was successfully created. 
		// If not then it prints an error to the console
		if(window == NULL){
			// Throw an Error
			System.err.println("Could not create our Window!");
		}
		
		// creates a bytebuffer object 'vidmode' which then queries 
		// to see what the primary monitor is. 
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Sets the initial position of our game window. 
		glfwSetWindowPos(window, 2200, 100);
		
		// Sets our keycallback to equal our newly created Input class()
		glfwSetKeyCallback(window, keyCallback = new Input());
		
		// Sets the context of GLFW, this is vital for our program to work.
		glfwMakeContextCurrent(window);
		// finally shows our created window in all it's glory.
		glfwShowWindow(window);
		
		
		// In order to perform OpenGL rendering, a context must be "made current"
		// we can do this by using this line of code:
		GLContext.createFromCurrent();
		
		// Clears color buffers and gives us a nice color background.
		glClearColor(0.3f,0.7f,0.92f,1.0f);
		
		
		glActiveTexture(GL_TEXTURE1);
		
		// Enables depth testing which will be important to make sure
		// triangles are not rendering in front of each other when they
		// shouldn't be.
		glEnable(GL_DEPTH_TEST);
		
		// Prints out the current OpenGL version to the console.
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		
		Shader.loadAll();
		
		game = new Game();
		
		Shader.shader1.enable();
//		Shader.shader2.enable();
		Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
	
		Shader.shader1.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.shader1.setUniform1i("tex", 1);
		
//		Shader.shader2.disable();
		Shader.shader1.disable();
		Shader.shader2.enable();
		
		Shader.shader2.setUniformMat4f("pr_matrix", pr_matrix);
		Shader.shader2.setUniform1i("tex", 1);
		
		Shader.shader2.disable();
		
		
		
	}
	
	public void update(){
		// Polls for any window events such as the window closing etc.
		glfwPollEvents();
		
		game.update();
		// tests to see if the GLFW_KEY_SPACE key is currently pressed
		// this returns a true value for our If statement when it is pressed
		// and as a result prints out our console message.
		
	}
	
	public void render(){
		// 
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		
		game.render();
//		player2.render();
		int i = glGetError();
		if(i != GL_NO_ERROR)
			System.out.println(i);
		
		// Swaps out our buffers
		glfwSwapBuffers(window);
	}
	
	@Override
	public void run() {
		// All our initialization code
		init();
		// Our main game loop

		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			if (glfwWindowShouldClose(window) == GL_TRUE)
				running = false;
		}
		
		keyCallback.release();
		glfwDestroyWindow(window);
		glfwTerminate();
	}
	
	

}
