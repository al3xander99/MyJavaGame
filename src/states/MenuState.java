package states;

import Game.Handler;
import Game.Joc;
import Gfx.Assets;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;
    public MenuState(Handler handler){
        super(handler);
        uiManager=new UIManager(handler);

        uiManager.addObject(new UIImageButton(700,400,256,128, Assets.start,new ClickListener(){
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
        handler.getMouseManager().setUIManager(uiManager);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
