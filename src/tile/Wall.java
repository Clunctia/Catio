package tile;

import java.awt.Color;
import java.awt.Graphics;

import Catio.Game;
import Catio.Handler;
import Catio.Id;

public class Wall extends Tile{

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.grass.getBufferedImage(),x,y,width,height,null);
	}

	@Override
	public void tick() {	}

}
