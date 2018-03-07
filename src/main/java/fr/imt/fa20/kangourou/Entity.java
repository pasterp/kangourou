package fr.imt.fa20.kangourou;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

class Entity{
    float x;
    float y;
    int direction = 0;
    private Animation[] animations = new Animation[2];
    boolean moving;

    public Entity(float x, float y) throws SlickException{
        this.x = x;
        this.y = y;
    }

    public Entity() throws SlickException{
        this(30,110);
    }

    public void loadSprite() throws SlickException {
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

    public void draw(Graphics g){
        g.drawAnimation(animations[direction], x, y);
    }
}