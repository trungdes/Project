package Level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class LevelManager {
	private Game game;
	private BufferedImage[] LevelSprite;
	
	public LevelManager(Game game) {
		this.game = game;
//		LevelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		importOutsideSrpite();
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
		g.drawImage(LevelSprite[2], 0, 0, null);
	}
	public void update() {
		
	}
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
