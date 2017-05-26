package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Catio.Game;
import entity.Entity;

public class KeyInput implements KeyListener{
	
	//Will change to arrow keys.
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(Entity en:Game.handler.entity){
			switch(key){
			case KeyEvent.VK_W:
				if(!en.jumping) {
					en.jumping = true;
					en.gravity = 10.0;
				}
				break;
			case KeyEvent.VK_A:
				en.setVelX(-2);
				break;
			case KeyEvent.VK_D:
				en.setVelX(2);
				break;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(Entity en:Game.handler.entity){
			switch(key){
			case KeyEvent.VK_W:
				en.setVelY(0);
				break;
			case KeyEvent.VK_S:
				en.setVelY(0);
				break;
			case KeyEvent.VK_A:
				en.setVelX(0);
				en.facing = 0;
				break;
			case KeyEvent.VK_D:
				en.setVelX(0);
				en.facing = 1;
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
