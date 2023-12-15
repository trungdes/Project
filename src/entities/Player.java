package entities;


import static utilz.constant.playerConstants.*;
import static utilz.HelpMethods.CanMoveHere;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity {

	private BufferedImage[][] animations;
	private	int aniTick,aniDex,aniSpeed = 25;
	private int playerAction = IDLE;
	private boolean left, up ,right, down;
	private boolean moving = false, attacking = false;
	private float playerSpeed = 1.0f;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimation();
		initHitBox(y, y, 20 * Game.SCALE, 28 * Game.SCALE);
	}
	
	public void update() {
		
		updatePos();
		
		
		
		setAnimation();
		
		updateAnimationTick();	
		
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniDex],(int)(hitBox.x - xDrawOffset), (int)(hitBox.y - yDrawOffset), width, height, null);
		drawHitbox(g);
	}
	
	
	
	private void setAnimation() {
		
		int startAni = playerAction;
		if(moving) {
			playerAction = RUNNING;
		}
		else {
			playerAction = IDLE;
		}
		if (attacking) {
			playerAction = ATTACK_1;
		}
		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniDex = 0;
		
	}

	private void updateAnimationTick() {
		aniTick++;
		if( aniTick >= aniSpeed) {
			aniTick = 0;
			aniDex++;
			if (aniDex >= GetSpriteAmount(playerAction)) {
				aniDex = 0;
				attacking = false;
			}
		}
	}

	

	private void updatePos() {
		moving = false;
		if(!left && !right && !up && !down)
			return;
		
		float xSpeed = 0, ySpeed = 0;
			
		if ( left && !right) {
			xSpeed = -playerSpeed;
		}else if(right && !left){
			xSpeed = playerSpeed;
		}
		
		
		if (up && !down) {
			ySpeed = -playerSpeed;
		}else if(down && !up) {
			ySpeed = playerSpeed;
			
		}
//		if (CanMoveHere(x + xSpeed,y + ySpeed,width,height,lvlData)) {
//			this.x += xSpeed;
//			this.y += ySpeed;
//			moving = true;
//		}
		
		if (CanMoveHere(hitBox.x + xSpeed,hitBox.y + ySpeed,hitBox.width,hitBox.height,lvlData)) {
			hitBox.x += xSpeed;
			hitBox.y += ySpeed;
			moving = true;
		}
	}
	
	private void loadAnimation() {
		

		
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PlAYER_ATLAS);
			
			animations = new BufferedImage[9][6];
			for (int j= 0; j < animations.length;j++)
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
			}
		
		
	}
	public void loadLvlData(int[][] lvlData ) {
		this.lvlData = lvlData;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
		
	}
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
}
