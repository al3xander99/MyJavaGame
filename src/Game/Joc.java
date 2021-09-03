package Game;

import Game.Display.Display;
import Gfx.Assets;
import Gfx.ImageLoader;
import Gfx.SpriteSheet;
import input.KeyManager;
import input.MouseManager;
import states.GameState;
import states.MenuState;
import states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class Joc implements Runnable {

    private static Joc instance = null;
    private Display display;

    public int width, height;
    public String title;

    private boolean running=false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    public State gameState;
    public State menuState;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    private Handler handler;

    private Joc(String title,int width,int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager=new KeyManager();
        mouseManager=new MouseManager();
    }

    public static Joc GetInstance(String title,int width,int height){
        if(instance==null){
            instance=new Joc(title,width,height);
        }
        return instance;
    }

    private void init() {
        display=new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);//aici pun mouse-ul si pe jframe si pe canvas asa incat oricare e activa merge mouse-ul
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler=new Handler(this);

        gameState=new GameState(handler);
        menuState=new MenuState(handler);
        State.setState(menuState);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private void tick(){
        keyManager.tick();
        if(State.getState()!=null)
            State.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (null == bs) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0,0,width,height);

        if(State.getState()!=null)
            State.getState().render(g);

        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        int fps=60;
        double timePerTick=1000000000/fps;
        double delta=0;
        long now;
        long lastTime=System.nanoTime();
        long timer=0;
        long ticks=0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer+=now-lastTime;
            lastTime = now;
            if (delta>=1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer>=1000000000){
               // System.out.println("Ticks and Frames: "+ ticks);
                ticks=0;
                timer=0;
            }
        }
        stop();

    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public synchronized void start(){
        if(running)
            return;
        running=true;
        thread=new Thread(this);
        thread.start();
    }
    public synchronized void stop() {
        if(!running)
            return;
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
