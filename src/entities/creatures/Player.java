package entities.creatures;

import Game.Handler;
import Game.Joc;
import Gfx.Animation;
import Gfx.Assets;
import tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends creature{

    private Animation running,runningLeft;
    public boolean canDoubleJump=false;
    public int coinCounter;
    private boolean facingleft,facingright;


    public Player(Handler handler, float x, float y) {
        super(handler,x, y,creature.DEFAULT_CREATURE_WIDTH,creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x=14;
        bounds.y=20;
        bounds.width=34;
        bounds.height=40;
        coinCounter=0;
        facingleft=false;
        facingright=true;

        running = new Animation (50,Assets.runnin);
        runningLeft=new Animation(50,Assets.runninlef);
    }

    @Override
    public void tick() {
        running.tick();
        getInput();
        move();
        checkEntityCollisions(0,0);
        //System.out.println(doublejump);
    }

    private void getInput(){
        xMove=0;
        yMove=0;
        /*if(handler.getKeyManager().up)
            yMove = -speed;*/
        /*if(handler.getKeyManager().down)
            yMove = speed;*/
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
        if(handler.getKeyManager().space){
            if(!jumping && isGrounded((int)x,(int)y))
                jumping=true;
        }
        if(handler.getKeyManager().f){
            if((jumping || falling) && !doublejump && doublejumpToken && canDoubleJump){
                jumping = false;
                doublejump=true;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),(int)x,(int)y,width,height,null);
        //g.setColor(Color.red);
        //g.fillRect((int)(x+bounds.x),(int)(y+bounds.y),bounds.width,bounds.height);
    }

    @Override
    public int getId() {
        return 3;
    }

    private BufferedImage getCurrentAnimationFrame(){
            if(yMove>0 && facingright){return Assets.jmp;}
            else if (yMove>0 && facingleft){return Assets.fleft;}
            else if(yMove<0 && facingright){return Assets.fall;}
            else if(yMove<0 && facingleft){return Assets.jleft;}
            else if(xMove>0){
                facingright=true;
                facingleft=false;
                return running.getCurrentFrame();}
            else if(xMove<0){
                facingleft=true;
                facingright=false;
                return runningLeft.getCurrentFrame();}
            else if(facingright)
                return Assets.player;
           else
               return Assets.playerLeft;
    }
}
