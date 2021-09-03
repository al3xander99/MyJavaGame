package entities.statics;

import Game.Handler;
import Gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class flag extends StaticEntity{
    public flag(Handler handler, float x, float y){
        super(handler,x,y, Tile.TILEDIM,Tile.TILEDIM);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.flag,(int)x,(int)y,width,height,null);
    }

    @Override
    public int getId() {
        return 1;
    }
}
