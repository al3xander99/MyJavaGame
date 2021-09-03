package states;

import Game.Handler;
import Game.Joc;
import Gfx.Assets;
import entities.creatures.Player;
import entities.statics.Coin;
import entities.statics.flag;
import tiles.Tile;
import worlds.World;

import java.awt.*;

public class GameState extends State {
    private World world;

    public GameState(Handler handler){
        super(handler);
        world=new World(handler,"world1.txt");
        handler.setWorld(world);
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,13*64,64));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,7*64,6*64));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,28*64,5*64));
        handler.getWorld().getEntityManager().addEntity(new flag(handler,27*64,13*64));
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
    public int getLevel(){return 1;}
}
