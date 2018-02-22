package fr.imt.fa20.kangourou;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {

    /** Screen width */
    private static final int WIDTH = 400;
    /** Screen height */
    private static final int HEIGHT = 300;

    private GameContainer container;
    private TiledMap map;

    //Character
    private float x = 30, y = 110;
    private int direction = 0;
    private Animation[] animations = new Animation[2];
    private boolean moving;

    public Game() {
        super("Slick Kangourou");
    }

    public void render(GameContainer container, Graphics g) throws SlickException {

        this.map.render(0, 0, 0);
        this.map.render(0, 0, 1);
        this.map.render(0, 0, 2);
        g.drawAnimation(animations[direction], x, y);

        g.drawString("Hello, Messire!", 50, 50);

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map = new TiledMap("map/map.tmx");
        SpriteSheet spriteSheet = new SpriteSheet("sprites/perso.png", 16, 16, 1);
        Animation animation = new Animation();
        animation.addFrame(spriteSheet.getSprite(0, 0), 250);
        animation.addFrame(spriteSheet.getSprite(2, 0), 260);
        animation.addFrame(spriteSheet.getSprite(1, 0), 230);
        animation.addFrame(spriteSheet.getSprite(2, 0), 260);
        animation.addFrame(spriteSheet.getSprite(1, 0), 230);
        animation.addFrame(spriteSheet.getSprite(2, 0), 260);
        animations[0] = animation;

        Animation animation2 = new Animation();
        animation2.addFrame(spriteSheet.getSprite(0, 1), 250);
        animation2.addFrame(spriteSheet.getSprite(2, 1), 260);
        animation2.addFrame(spriteSheet.getSprite(1, 1), 230);
        animation2.addFrame(spriteSheet.getSprite(2, 1), 260);
        animation2.addFrame(spriteSheet.getSprite(1, 1), 230);
        animation2.addFrame(spriteSheet.getSprite(2, 1), 260);
        animations[1] = animation2;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        //Game logic
        float futurex=this.x;
        if (this.moving) {
            switch (this.direction) {
                case 0: futurex += .1f * delta; break;
                case 1: futurex -= .1f * delta; break;
            }

            if (isCollision(futurex, this.y+16)) { //+16 pour hauteur du sprite
                this.moving = false;
            } else {
                this.x = futurex;
                //this.y = this.y;
            }
        }

    }

    @Override
    public void keyReleased(int key, char c){
        if(Input.KEY_ESCAPE == key){
            System.out.println("Bye bye!");
            container.exit();
        }

        this.moving = false;
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_RIGHT:    this.direction = 0; this.moving = true; break;
            case Input.KEY_LEFT:  this.direction = 1; this.moving = true; break;
        }
    }

    private boolean isCollision(float x, float y) {
        int tileW = this.map.getTileWidth();
        int tileH = this.map.getTileHeight();
        int logicLayer = this.map.getLayerIndex("Logic");
        Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
        boolean collision = tile != null;
        if (collision) {
            Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }

    public static void main(String[] args) throws SlickException {
        NativeLoader loader = new NativeLoader();
        loader.loadLibrary("lwjgl64");

        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.start();
    }

}
