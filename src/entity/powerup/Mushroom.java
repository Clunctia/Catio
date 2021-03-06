package entity.powerup;

import java.awt.Graphics;
import java.util.Random;

import Catio.Game;
import Catio.Handler;
import Catio.Id;
import entity.Entity;
import tile.Tile;

public class Mushroom extends Entity {
	private Random random = new Random();

	public Mushroom(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
		int dir = random.nextInt(2);
		switch(dir){
		case 0:
			setVelX(-1);
			break;
		case 1:
			setVelX(1);
			break;
		}
	}

	public void render(Graphics g) {
		g.drawImage(Game.mushroom.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			if (t.isSolid()) {
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelY(0);
					if(falling)falling = false;
				}
				else if (!falling) {
					falling = true;
					gravity = 0.8;
				}
				if (getBoundsLeft().intersects(t.getBounds())) {
					setVelX(1);
				}
				if (getBoundsRight().intersects(t.getBounds())) {
					setVelX(-1);
				}
			}
		}
		if (falling) {
			gravity += 0.1;
			setVelY((int) gravity);
		}
	}

}