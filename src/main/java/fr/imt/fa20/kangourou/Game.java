package fr.imt.fa20.kangourou;

import org.newdawn.slick.*;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {

    /** Screen width */
    private static final int WIDTH = 800;
    /** Screen height */
    private static final int HEIGHT = 600;

    /** A counter... */
    private int counter;
    private GameContainer container;

    public Game() {
        super("Slick Kangourou");
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        g.drawString("Hello, " + Integer.toString(counter) + "!", 50, 50);

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        counter = 0;
        this.container = container;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        counter++;
    }

    @Override
    public void keyReleased(int key, char c){
        if(Input.KEY_ESCAPE == key){
            System.out.println("Bye bye!");
            container.exit();
        }
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
