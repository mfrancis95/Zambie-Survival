package main.java.org.zambiesurvival.game.mike;

import main.java.org.zambiesurvival.engine.Game;
import main.java.org.zambiesurvival.engine.GameContainer;
import main.java.org.zambiesurvival.engine.WorldState;

public class ZambieSurvival {
    
    public static void main(String[] args) {
        Game game = new Game();
        game.addState("Title", new TitleState());
        game.addState("World", new WorldState(32));
        game.enterState("Title", true);
        new GameContainer(game, "Zambie Survival", 640, 480).start();
    }
    
}