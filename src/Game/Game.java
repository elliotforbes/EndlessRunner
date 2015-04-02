package Game;

import Math.Matrix4f;
import Math.Vector3f;

public class Game {
	
	public Camera camera = new Camera();
	
	public Tile tile;
	public Coin[] coins = new Coin[5];
	public Player player1;
	
	public Game(){
		init();
	}
	
	public void init(){
		for(int i = 0; i<5; i++){
			coins[i] = new Coin();
			coins[i].translate(new Vector3f(i * 1.7f, 0.0f ,0.0f));
		}
		
		tile = new Tile();
		player1 = new Player();
		player1.translate(new Vector3f(-8.0f, -3.6f, 0.0f));
//		camera.setPosition();
	}
	
	public boolean collision(){
		// this loops round every coin that is 
		// currently on the screen and checks to see 
		// if there is a collision between that coin and 
		// the player. 
		for(int i = 0; i<5; i++){
			// This checks to see if the coin in the coins
			// array is alive. If it isn't then we shouldn't
			// waste time detecting if it's colliding with 
			// anything!
			if(coins[i].alive){
				// Gets our players box coordinates
				// If you want to make collisions tighter
				// or looser then play around with the float
				// values being added to playerW and playerH
				float playerX = player1.position.x;
				float playerY = player1.position.y;
				float playerW = player1.position.x + 1.0f;
				float playerH = player1.position.y + 1.0f;
				
				// Gets our coins box coordinates
				// we'll be comparing these 2 boxes
				// against each other every frame
				// to see if there is a collision or not
				float coinX = coins[i].position.x;
				float coinY = coins[i].position.y;
				float coinW = coins[i].position.x + 1.5f;
				float coinH = coins[i].position.y + 1.5f;
				
				// These are the checks to see if any parts
				// of our 2 collision boxes are touching each other
				// if they are then we print out that we've collided
				// and we set this coins alive variable to false.
				// We then return true that there has been a collision
				if(playerX < coinW && 
				playerW > coinX &&
				playerY < coinH &&
				playerH > coinY
				){
					coins[i].alive = false;
					return true;
				}
			}
			
		}
		// if there hasn't been a collision then we return false 
		// and the game carries on as normal.
		return false;
	}
	
	public void update(){
		if(collision()){
			System.out.println("COLLISION");
		}
		
		tile.update();
		player1.update();
		camera.setPosition(player1.position);
		
		for(int i = 0; i<coins.length; i++)
			coins[i].update();
	}
	
	public void render(){

		camera.render();
		tile.render();
		player1.render();
		for(int i = 0; i<coins.length; i++){
			if(coins[i].alive)
				coins[i].render();
		}
	}

}
