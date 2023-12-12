package main;

import java.awt.Dimension;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utilz.constant.playerConstants.*;
import static utilz.constant.Directions.*;

public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;
	
	private boolean moving = false;
	public GamePanel(Game game) {

		mouseInputs = new MouseInputs(this);
		this.game = game;
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);

	}

	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

	}

	public void updateGame() {
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
		
	}
	
	public Game GetGame() {
		return game;
	}

}