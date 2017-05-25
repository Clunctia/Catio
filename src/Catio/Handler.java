package Catio;

import java.awt.Graphics;
import java.util.LinkedList;

import entity.Entity;
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
	
	public Handler(){
		createLevel();
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
	
	public void createLevel(){
		for(int i=0 ; i < Game.WIDTH*Game.SCALE/64+1 ; i++){
			addTile(new Wall(i*64, Game.HEIGHT*Game.SCALE-64, 64, 64, true, Id.wall, this));
			if(i!=0 && i!=1 && i!= 16 && i!= 17) addTile(new Wall(i*64, 300, 64, 64, true, Id.wall, this));
		}
	}
}
