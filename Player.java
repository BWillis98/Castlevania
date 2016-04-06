package castlevania;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {

	private static final long serialVersionUID = -5471880502989465049L;
	private static int counter = 0;
	private int xSprite = 0, ySprite = 0;
	private int x, velx;
	private int y, vely, velyInit = 3, accel = -4, t = 0;
	private final int SPRITEROWS = 4, SPRITECOLS = 6, WIDTH = 128,
			HEIGHT = 128;
	private SpriteSheet sheet;
	private int health;
	private ArrayList<Item> items;
	public boolean isRunning = false, isAttacking = false, isStanding = true,
			isJumping = false;

	public Player(int x, int y) {
		super();
		sheet = new SpriteSheet("spritesheets/belmont_sprite_sheet.jpg", WIDTH,
				HEIGHT, SPRITEROWS, SPRITECOLS);
		this.x = x;
		this.y = y;
		health = 10;
		items = new ArrayList<Item>(); // for items gathered in game
		setFocusable(true);
	}

	public void resetTime() {
		this.t = 0;
	}

	public BufferedImage changeImages() // CHANGE THIS
										// METHOD!-----------------------------------------------------------
	{
		counter++;
		if (counter > 5) {
			if (isJumping)
			{
				xSprite = 0;
				ySprite = 3;
			}
			else if (isRunning) // I'm going to have images be returned differently
							// depending on what the player is doing. This part
							// is if it's running.
			{
				xSprite = 0;
				if (ySprite != SPRITECOLS - 1 && velx != 0) {
					ySprite++;
				} else {
					ySprite = 0;
				}
				counter = 0;
			} else if (isAttacking) // This sprite is for when he's attacking.
			{
				xSprite = 1; // Both of the attacks are in the same row.
				if (isStanding) {
					for (int i = 0; i < 2; i++) { // Also modified this to
													// appropriately change
													// xSprite
													// And ySprite instead of
													// returning
													// The image
						if (ySprite == 0) {
							ySprite = 1;
						} else {
							ySprite = 0;
						}
					}
				} else {
					for (int i = 2; i < 4; i++) {
						return sheet.getImage(1, i);
					}
				}
			}
		}
		this.x += velx;
		this.y += vely;
		System.out.println("Velocity: " + vely + " Pos: " + this.y);
		return sheet.getImage(xSprite, ySprite);
	}

	public boolean clearBelow() // Modify later to make it check below for
								// platforms.
	{
		return true;
	}

	public void jump() { // Needs to get fixed.
		
		
		
		/*
		 * I have no idea how I'm going make this paint every time
		 * it changes the y-velocity. This may be where threading is extremely important.
		 * I should talk to Josh Crotts about this tomorrow and see what he thinks.
		 * I know he's busy right now.
		 * 
		 * I need to sleep on this tonight because I just can't get it. A good
		 * amount of work done today, however.
		 */
		
		
		// v = vi + at   ---- Physics ----
		isJumping = true;
		t++;
		if (t % 4 == 0)
		{
			vely = (int) ((velyInit + accel * (t / 30)) * -1);
			System.out.println(vely);
		}
		if (isClear(vely))
		{
			changeImages();
			jump();
		}else{
			vely = 0;
			isJumping = false;
		}
	}
	
	public boolean isClear(int v)
	{
		if (v > velyInit)
		{
			return false;
		}
		return true;
	}

	public boolean onGround() {
		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public void setHealth(int x) {
		this.health = x;
	}

	public int getHealth() {
		return health;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Player.counter = counter;
	}

	public int getxSprite() {
		return xSprite;
	}

	public void setxSprite(int xSprite) {
		this.xSprite = xSprite;
	}

	public int getySprite() {
		return ySprite;
	}

	public void setySprite(int ySprite) {
		this.ySprite = ySprite;
	}

	public int getSpriteRows() {
		return SPRITEROWS;
	}

	public int getSpriteCols() {
		return SPRITECOLS;
	}

	@Override
	public SpriteSheet getSheet() {
		return sheet;
	}

	@Override
	public void setSheet(SpriteSheet sheet) {
		this.sheet = sheet;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVelx(int x) {
		this.velx = x;
	}

	public int getVelx() {
		return this.velx;
	}
}