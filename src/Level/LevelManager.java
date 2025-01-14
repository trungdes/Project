package Level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class LevelManager {
	private Game game;
	private BufferedImage[] LevelSprite;
	private Level levelOne;
	public LevelManager(Game game) {
		this.game = game;
//		LevelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		importOutsideSrpite();
		levelOne = new Level(LoadSave.GetLevelData());
	}
	private void importOutsideSrpite() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		LevelSprite = new BufferedImage[48];
		for (int j = 0; j < 4; j++)	
			for (int i = 0; i < 12 ; i++) {
				int index = j*12 + i;
				LevelSprite[index] = img.getSubimage(i* 32, j*32, 32, 32);
			}
		
	}
	public void draw(Graphics g) {
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
				int index = levelOne.getSpriteindex(i, j);
				g.drawImage(LevelSprite[index],Game.TILES_SIZE * i ,Game.TILES_SIZE * j  ,Game.TILES_SIZE,Game.TILES_SIZE, null);
			}
		
	}
	public void update() {
		
	}
	public Level getCurrentlevel() {
		return levelOne;
	}
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
