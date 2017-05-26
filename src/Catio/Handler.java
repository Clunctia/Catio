package Catio;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import entity.Entity;
import entity.mob.Goomba;
import entity.mob.Player;
import entity.powerup.Mushroom;
import tile.Tile;
import tile.Wall;

public class Handler {
	public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	
	public void render(Graphics g){
		for(Entity en : entity){
			en.render(g);
		}
		for(Tile ti : tile){
			ti.render(g);
		}
	}
	
	public void tick(){
		for(Entity en : entity){
			en.tick();
		}
		for(Tile ti : tile){
			ti.tick();
		}
	}
	
	public void addEntity(Entity en){
		entity.add(en);
	}
	
	public void remoceEntity(Entity en){
		entity.remove(en);
	}
	
	public void addTile(Tile ti){
		tile.add(ti);
	}
	
	public void removeTile(Tile ti){
		tile.remove(ti);
	}
	
	public void createLevel(BufferedImage level){
		int width = level.getWidth();
		int height = level.getHeight();
		
		for(int y = 0 ; y < height ; y++){
			for(int x = 0 ; x<width ; x++){
				int pixel = level.getRGB(x, y);
				
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue ==0) addTile(new Wall(x*64, y*64, 64, 64, true, Id.wall, this));
				if(red == 0 && green == 0 && blue ==255) addEntity(new Player(x*64, y*64, 64, 64, false, Id.player, this));
				if(red == 255 && green == 0 && blue ==0) addEntity(new Mushroom(x*64,y*64,64,64,true,Id.mushroom,this));
				if(red == 0 && green == 255 && blue == 255) addEntity(new Goomba(x*64,y*64,64,64,true,Id.goomba,this));
			}
		}
	}
}
