package main;

import java.awt.Graphics;

import Level.LevelManager;
import entities.Player;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private Player player;
	private LevelManager levelManager;
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	public Game() {

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		initClasses();
		startGameLoop();
		
	}

	private void initClasses() {
		levelManager = new LevelManager(this);
		player = new Player(200, 200, (int)( 64 * SCALE), (int) (40 * SCALE));
		 player.loadLvlData(levelManager.getCurrentlevel().getLevelData());
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void update() {
		player.update();
		levelManager.update();
	}
	
	public void render(Graphics g) {
		
		levelManager.draw(g);
		player.render(g);
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
		
		long previous = System.nanoTime();

 		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;
		
		while (true) {

			
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previous) / timePerUpdate;
			deltaF += (currentTime - previous) / timePerFrame;
			previous = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			if(deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
			

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + "|UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

	}
	public void windowFocusLost() {
		player.resetDirBooleans();
		
	}
	public Player GetPlayer() {
		return player;
	}

	

}