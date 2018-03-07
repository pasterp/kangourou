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

    private Entity character;
    

    public Game() throws SlickException {
        super("Slick Kangourou");

        character = new Entity();
    }

    public void render(GameContainer container, Graphics g) throws SlickException {

        this.map.render(0, 0, 0);
        this.map.render(0, 0, 1);
        this.map.render(0, 0, 2);
        

        g.drawString("Hello, Messire!", 50, 50);

        this.character.draw(g);

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map = new TiledMap("map/map.tmx");
        this.character.loadSprite();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        //Game logic
        float futurex=character.x;
        if (character.moving) {
            switch (character.direction) {
                case 0: futurex += .1f * delta; break;
                case 1: futurex -= .1f * delta; break;
            }

            if (isCollision(futurex, character.y+16)) { //+16 pour hauteur du sprite
                character.moving = false;
            } else {
                character.x = futurex;
                //this.y = this.y;
            }
        }

    }

    @Override
    public void keyReleased(int key, char c){
        switch(key){
            case Input.KEY_ESCAPE:
            case Input.KEY_Q:
                System.out.println("Bye bye!");
                container.exit();
                break;
            case Input.KEY_RIGHT: 
                character.moving = false; break;
            case Input.KEY_LEFT: 
                character.moving = false; break;
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_RIGHT:    
                character.direction = 0; 
                character.moving = true; 
                break;
            case Input.KEY_LEFT:  
                character.direction = 1; 
                character.moving = true; 
                break;
            case Input.KEY_UP: break;
            case Input.KEY_DOWN: break;
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
