package entities.statics;

import Game.Handler;
import Gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class Coin extends StaticEntity{
    public Coin(Handler handler, float x, float y){
        super(handler,x,y, Tile.TILEDIM,Tile.TILEDIM);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.coin,(int)x,(int)y,width,height,null);
        //g.setColor(Color.red);
        //g.fillRect((int)(x+bounds.x),(int)(y+bounds.y),bounds.width,bounds.height);
    }

    @Override
    public int getId() {
        return 2;
    }
}
