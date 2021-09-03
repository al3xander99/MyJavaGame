package tiles;

import tiles.bottom.bot_inner;
import tiles.bottom.bot_left_corner;
import tiles.bottom.bot_right_corner;
import tiles.corners.corner1;
import tiles.corners.corner2;
import tiles.corners.corner3;
import tiles.corners.corner4;
import tiles.empty.emptyTile;
import tiles.median.med_inner;
import tiles.median.med_left_outer;
import tiles.median.med_right_outer;
import tiles.surface.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile implements TileInterface {

    public static Tile[] tiles=new Tile[256];
    public static Tile empty=new emptyTile(0);
    public static Tile surfi1=new surf_inner1(1);
    public static Tile surfi2=new surf_inner2(2);
    public static Tile surfi3=new surf_inner3(3);
    public static Tile surfi4= new surf_inner4(4);
    public static Tile surflc= new surf_left_corner(5);
    public static Tile surfrc=new surf_right_corner(6);
    public static Tile medi=new med_inner(7);
    public static Tile medlo=new med_left_outer(8);
    public static Tile medro=new med_right_outer(9);
    public static Tile boti=new bot_inner(10);
    public static Tile botlc=new bot_left_corner(11);
    public static Tile botrc=new bot_right_corner(12);
    public static Tile c1=new corner1(13);
    public static Tile c2=new corner2(14);
    public static Tile c3=new corner3(15);
    public static Tile c4=new corner4(16);
    public static Tile plat=new platform(17);

    public static final int TILEDIM=64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture,int id){
        this.texture=texture;
        this.id=id;

        tiles[id]=this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,TILEDIM,TILEDIM,null);
    }

    public boolean isSolid(){return true;}

    public int getId(){
        return id;
    }

}
