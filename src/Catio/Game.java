package Catio;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Catio.graphic.Sprite;
import Catio.graphic.SpriteSheet;
import entity.Entity;
import entity.mob.Player;
import input.KeyInput;
import tile.Wall;

public class Game extends Canvas implements Runnable {
	
	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH/14*10;
	public static final int SCALE = 4;
	public static final String TITLE = "Catio";
	public static Handler handler;
	public static SpriteSheet sheet;
	public static Camera cam;
	public static Sprite grass;
	public static Sprite []player;
	public static Sprite []goomba;
	public static Sprite mushroom;
	public static String levelPath = "/level.png";

	
	private Thread thread;
	private boolean running = false;
	private BufferedImage image;
	
	public synchronized void start(){
		if(running)return;
		running = true;
		thread = new Thread(this,"Thread");
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)return;
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 100000000/60.0;
		int frames = 0;
		int ticks = 0;
		while(running){
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime = now;
			while(delta>=1){
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				System.out.println(frames + " FPS " + ticks + " UPS ");
				frames = 0;
				ticks = 0;
			}
		}stop();
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs==null){
			createBufferStrategy(3);
				return;
			}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.translate(cam.getX(), cam.getY());
		handler.render(g);
		g.dispose();
		bs.show();
	}
	
	public void tick(){
		handler.tick();
		for(Entity e:handler.entity){
			if(e.getId()==Id.player){
				cam.tick(e);
			}
		}
	}
	
	public int getFrameWidth(){
		return WIDTH*SCALE;
	}
	
	public int getFrameHeight(){
		return HEIGHT*SCALE;
	}
	
	private void init(){
		handler = new Handler();
		sheet = new SpriteSheet("/spritesheet.png");
		cam = new Camera(this);
		grass = new Sprite(sheet,1,1);
		player = new Sprite[10];
		mushroom = new Sprite(sheet,2,1);
		goomba = new Sprite[8];
		addKeyListener(new KeyInput());
		
		
		for(int i=0;i<player.length;i++){
			player[i] = new Sprite(sheet,i+1,15);
		}
		
		for(int i=0;i<goomba.length;i++){
			goomba[i] = new Sprite(sheet,i+1,16);
		}
		
		try {
			image = ImageIO.read(getClass().getResource(levelPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		handler.createLevel(image);
	}
	
	public Game(){
		Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}
	public static void main(String[] args){
		Game game = new Game();
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
	}

	
}
	
