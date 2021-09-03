package Game;

import Game.Display.Display;

public class Launcher {
    public static void main(String[] args){
        Joc game = Joc.GetInstance("Title",1920,1080);
        game.start();
    }
}
