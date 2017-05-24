import java.awt.Canvas;
import java.awt.Dimension;

public class Game extends Canvas{
	
	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH/14*10;
	public static final int SCALE = 4;
	
	public Game(){
		Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}
}
	
