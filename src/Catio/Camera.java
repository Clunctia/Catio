package Catio;

import entity.Entity;

public class Camera {
	public int x,y;
	public Game game;
	
	public Camera(Game game){
		this.game = game;
	}
	public void tick(Entity player){
		
		setX(-player.getX() + game.WIDTH*2);
		setY(-player.getY() + game.HEIGHT*2);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
