package Game;

import Game.Joc;
import input.KeyManager;
import input.MouseManager;
import worlds.World;

public class Handler {
    private Joc game;
    private World world;


    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public Joc getGame() {
        return game;
    }

    public void setGame(Joc game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Handler(Joc game){
        this.game=game;
    }
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
}
