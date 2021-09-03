package states;

import Game.Handler;
import Game.Joc;
import Gfx.Assets;
import entities.creatures.Player;
import entities.statics.Coin;
import entities.statics.flag;
import entities.statics.powerup;
import tiles.Tile;
import worlds.World;

import java.awt.*;

public class GameState2 extends State {
    private World world;

    public GameState2(Handler handler){
        super(handler);
        world=new World(handler,"world2.txt");
        handler.setWorld(world);
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,64,11*64));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,29*64,8*64));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,26*64,14*64));
        handler.getWorld().getEntityManager().addEntity(new flag(handler,0,64));
        handler.getWorld().getEntityManager().addEntity(new powerup(handler,0,14*64));
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
    public int getLevel(){return 2;}
}
