package entities;

import Game.Handler;
import Game.Joc;
import states.GameState2;
import states.State;
import worlds.World;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected float x,y;
    protected int width, height;
    protected Rectangle bounds;

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Entity(Handler handler,float x, float y, int width, int height) {
        this.handler=handler;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;

        bounds= new Rectangle(0,0,width,height);
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if (e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset)))
            {
                if(e.getId()==2){
                    handler.getWorld().getEntityManager().removeEntity(e);
                    handler.getWorld().getEntityManager().getPlayer().coinCounter++;
                    return true;
                }
                else if(e.getId()==1 && handler.getWorld().getEntityManager().getPlayer().coinCounter==3){
                    System.out.println("Nivel Terminat!");
                    State.setState(new GameState2(handler));
                    return true;
                }
                if(e.getId()==4){
                    handler.getWorld().getEntityManager().getPlayer().canDoubleJump=true;
                    handler.getWorld().getEntityManager().removeEntity(e);
                    return true;
                }
                return true;
            }

        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x+bounds.x + xOffset), (int)(y+bounds.y + yOffset),bounds.width,bounds.height);
    }
    public float getX(){
    return x;
    }



    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract int getId();
}
