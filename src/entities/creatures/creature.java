package entities.creatures;

import Game.Handler;
import Game.Joc;
import entities.Entity;
import states.GameState;
import states.State;
import tiles.Tile;


public abstract class creature extends Entity {

    public final static int DEFAULT_HEALTH=10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,DEFAULT_CREATURE_HEIGHT=64;

    protected boolean doublejump=false;
    protected boolean doublejumpToken=true;
    protected float speed;
    protected int health;
    protected float xMove,yMove;
    protected double jumpSpeed = 5;
    protected double currentJumpSpeed = jumpSpeed;
    protected double DoublejumpSpeed=5;
    protected double currentDoubleJumpSpeed=DoublejumpSpeed;
    protected double maxFallSpeed =8;
    protected double currentFallSpeed = 0.1;

    protected boolean jumping = false,falling=true;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public creature(Handler handler, float x, float y, int width, int height) {
        super(handler,x, y,width,height);
        health=DEFAULT_HEALTH;
        speed=DEFAULT_SPEED;
        xMove=0;
        yMove=0;
    }

    public void move(){
        if(isGrounded((int)x,(int)y))
        {
            falling=false;
            doublejumpToken=true;
        }
        else if(!jumping && !doublejump)
            falling=true;
        /*else if(collisionWithTile((int)((x+32)/Tile.TILEDIM),(int)((y-3)/Tile.TILEDIM))){
            jumping=false;
            falling=true;
        }*/

        moveX();
        moveY();
        if(y>=1020)
            State.setState(new GameState(handler));
        //System.out.println(canDoubleJump);
    }

    public void moveX(){
        if(xMove>0){
            int tx=(int)(x+xMove + bounds.x + bounds.width)/ Tile.TILEDIM;
            if(!collisionWithTile(tx,(int)(y+bounds.y)/Tile.TILEDIM) && !collisionWithTile(tx,(int)(y+bounds.y + bounds.height)/Tile.TILEDIM)){
                x += xMove;
            }else{
                x = tx * Tile.TILEDIM - bounds.x - bounds.width-1;
            }
        }else if(xMove < 0){
            int tx=(int)(x+xMove+bounds.x)/Tile.TILEDIM;
            if(!collisionWithTile(tx,(int)(y+bounds.y)/Tile.TILEDIM)&&
            !collisionWithTile(tx,(int)(y+bounds.y+bounds.height)/Tile.TILEDIM)){
                x+=xMove;
            }else{
                x=tx*Tile.TILEDIM + Tile.TILEDIM - bounds.x;
            }
        }
    }

    public void moveY(){
        if(jumping)
        {
            doublejump=false;
            yMove -= currentJumpSpeed;
            currentJumpSpeed -= 0.1;
            if(currentJumpSpeed <=0){
                currentJumpSpeed=jumpSpeed;
                jumping = false;
                falling = true;
            }
        }

        if (doublejump) {
            currentJumpSpeed=jumpSpeed;
                falling = false;
                yMove -= currentDoubleJumpSpeed;
                currentDoubleJumpSpeed -= 0.1;
                if (currentDoubleJumpSpeed <= 0) {
                    currentDoubleJumpSpeed = DoublejumpSpeed;
                    doublejumpToken=false;
                    doublejump = false;
                    falling = true;
                }
            }

        if(falling){
            yMove+=currentFallSpeed;
            if(currentFallSpeed < maxFallSpeed) {
                currentFallSpeed += 0.1;
            }
        }
        if(!falling){
            currentFallSpeed = 0;
        }
        if(yMove < 0 ){
            int ty = (int) (y + yMove + bounds.y)/Tile.TILEDIM;

            if(!collisionWithTile((int) (x+bounds.x)/Tile.TILEDIM,ty)&&
                    (!collisionWithTile((int) (x+bounds.x+bounds.width)/Tile.TILEDIM,ty))){
                y+=yMove;
            }else{
                y = ty*Tile.TILEDIM + Tile.TILEDIM - bounds.y;
            }
        }else if(yMove>0){
            int ty = (int) (y + yMove + bounds.y + bounds.height)/Tile.TILEDIM;

            if(!collisionWithTile((int) (x+bounds.x)/Tile.TILEDIM,ty)&&
                    (!collisionWithTile((int) (x+bounds.x+bounds.width)/Tile.TILEDIM,ty))){
                y+=yMove;
            }else{
                y=ty*Tile.TILEDIM - bounds.y - bounds.height -1;
            }

        }
    }
    protected boolean collisionWithTile(int x,int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }
    protected boolean isGrounded(int x,int y){
        return collisionWithTile((int)((x+32)/Tile.TILEDIM),(int)((y+67)/Tile.TILEDIM));
    }
}
