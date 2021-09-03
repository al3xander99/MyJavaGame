package Gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width=64,height=64;

    public static BufferedImage empty,surf_left_corner,surf_inner1,surf_inner2,surf_inner3,surf_inner4,surf_right_corner,med_left_outer,med_inner
            ,med_right_outer,bot_left_corner,bot_inner,bot_right_corner,corner1,corner2,corner3,corner4,platform,player,coin;
    public static BufferedImage[] runnin;
    public static BufferedImage[] runninlef;
    public static BufferedImage jmp,fall,jleft,fleft,start,flag,playerLeft,pwp;
    public static BufferedImage bg1= ImageLoader.loadImage("/textures/bg1.png");
    public static BufferedImage bg2= ImageLoader.loadImage("/textures/bg2.png");
    public static BufferedImage bg3= ImageLoader.loadImage("/textures/bg3.png");
    public static BufferedImage bg4= ImageLoader.loadImage("/textures/bg4.png");

    public static void init(){
        int min=2;
        int max=6;
        SpriteSheet sheet=new SpriteSheet(ImageLoader.loadImage("/textures/cave_tileset.png"));
        empty=sheet.crop(0,0,width,height);
        surf_left_corner=sheet.crop(64,0,width,height);
        surf_inner1=sheet.crop(2*64,0,width,height);
        surf_inner2=sheet.crop(3*64,0,width,height);
        surf_inner3=sheet.crop(4*64,0,width,height);
        surf_inner4=sheet.crop(5*64,0,width,height);
        surf_right_corner=sheet.crop(6*64,0,width,height);
        med_left_outer=sheet.crop(64,64,width,height);
        med_inner=sheet.crop(2*64,64,width,height);
        med_right_outer=sheet.crop(6*64,64,width,height);
        bot_left_corner=sheet.crop(64,2*64,width,height);
        bot_inner=sheet.crop(2*64,2*64,width,height);
        bot_right_corner=sheet.crop(6*64,2*64,width,height);
        corner1=sheet.crop(0,64,width,height);
        corner2=sheet.crop(0,2*64,width,height);
        corner3=sheet.crop(0,3*64,width,height);
        corner4=sheet.crop(0,4*64,width,height);
        platform=sheet.crop(3*64,3*64,width,height);
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        player=playerSheet.crop(64,0,width,height);
        runnin=new BufferedImage[3];
        runnin[0]=playerSheet.crop(2*64,0,width,height);
        runnin[1]=playerSheet.crop(0,64,width,height);
        runnin[2]=playerSheet.crop(64,64,width,height);
        runninlef=new BufferedImage[3];
        runninlef[0]=playerSheet.crop(0,3*64,width,height);
        runninlef[1]=playerSheet.crop(2*64,4*64,width,height);
        runninlef[2]=playerSheet.crop(64,4*64,width,height);
        fall=playerSheet.crop(0,2*64,width,height);//jump
        jmp=playerSheet.crop(2*64,64,width,height);//fall
        jleft=playerSheet.crop(2*64,5*64,width,height);
        fleft=playerSheet.crop(0,4*64,width,height);
        coin=playerSheet.crop(64,2*64,width,height);
        flag=playerSheet.crop(2*64,2*64,width,height);
        playerLeft=playerSheet.crop(64,3*64,width,height);
        pwp=playerSheet.crop(64,5*64,width,height);
        SpriteSheet menuSheet=new SpriteSheet(ImageLoader.loadImage("/textures/menubutton.png"));
        start=menuSheet.crop(0,0,4*64,2*64);
    }
}
