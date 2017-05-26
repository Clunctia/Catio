package entity.mob;

import java.awt.Color;
import java.awt.Graphics;

import Catio.Game;
import Catio.Handler;
import Catio.Id;
import entity.Entity;
import tile.Tile;

public class Player extends Entity {
	private int frame = 0;
	private int frameDelay = 0;
	private boolean animate = false;

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}

	@Override
	public void render(Graphics g) {
		if (facing == 0) {
			g.drawImage(Game.goomba[frame + 5].getBufferedImage(), x, y, width, height, null);

		} else if (facing == 1) {
			g.drawImage(Game.goomba[frame].getBufferedImage(), x, y, width, height, null);
		}
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			if (t.isSolid()) {
				if (getBoundsTop().intersects(t.getBounds())) {
					setVelY(0);
					if (jumping) {
						jumping = false;
						gravity = 0.8;
						falling = true;
					}
				}
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelY(0);
					if(falling)falling = false;
				}
				else if (!falling && !jumping) {
					falling = true;
					gravity = 0.8;
				}
				if (getBoundsLeft().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX()+t.width;
				}
				if (getBoundsRight().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX()-t.width;
				}
			}
		}
		
		for(int i=0;i<handler.entity.size();i++){
			Entity e = handler.entity.get(i);
			if(e.getId()==Id.mushroom){
				if(getBounds().intersects(e.getBounds())){
					int tpX = getX();
					int tpY = getY();
					width*=2;
					height*=2;
					setX(tpX-width);
					setY(tpY-height);
					
					e.die();
				}else if(e.getId()==Id.goomba){
					if(getBounds().intersects(e.getBounds())){
						die();
					}
				}
			}
		}
		
		if (jumping) {
			gravity -= 0.1;
			setVelY((int) -gravity);
			if (gravity <= 0.0) {
				jumping = false;
				falling = true;
			}
		} else if (falling) {
			gravity += 0.1;
			setVelY((int) gravity);
		}    
		if (animate) {
			frameDelay++;
			if (frameDelay >= 3) {
				frame++;
				if (frame >= 5) {
					frame = 0;
				}
			}
			frameDelay = 0;
		}

	}

}
