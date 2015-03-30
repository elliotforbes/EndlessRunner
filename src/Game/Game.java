package Game;

import Math.Vector3f;

public class Game {
	
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
	}
	
	public void update(){
//		player1.update();
		tile.update();
		player1.update();
		for(int i = 0; i<coins.length; i++)
			coins[i].update();
	}
	
	public void render(){
		tile.render();
		player1.render();
		for(int i = 0; i<coins.length; i++){
			coins[i].render();
		}
	}

}
