package worlds;

import Game.Handler;
import Game.Joc;
import Gfx.Assets;
import Gfx.ImageLoader;
import entities.EntityManager;
import entities.creatures.Player;
import entities.statics.Coin;
import entities.statics.flag;
import entities.statics.powerup;
import states.GameState;
import states.State;
import tiles.Tile;
import utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX,spawnY;
    private int[][] tiles;
    private EntityManager entityManager;

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public World(Handler handler, String path){
        this.handler=handler;
        entityManager=new EntityManager(handler,new Player(handler,64,64));
        //entityManager.addEntity(new powerup(handler,8*64,6*64));
        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        tiles = new int[width][height];
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                tiles[x][y] = Utils.parseInt(tokens[(x+y*width)+4]);
            }
        }
    }

    public void tick(){
        entityManager.tick();
    }

    public void render(Graphics g) {
        g.drawImage(Assets.bg1,0,0,null);
        g.drawImage(Assets.bg2,0,0,null);
        g.drawImage(Assets.bg3,0,0,null);
        g.drawImage(Assets.bg4,0,0,null);
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                getTile(x,y).render(g,x*64,y*64);
            }
        }
        entityManager.render(g);
    }

    public Tile getTile(int x,int y){
        if(x<0 || y<0 || x>=width || y>=height)
            return Tile.surfi1;
        Tile t=Tile.tiles[tiles[x][y]];
        if(t==null)
            return Tile.empty;
        return t;
    }

}
