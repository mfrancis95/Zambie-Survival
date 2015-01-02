package main.java.org.zambiesurvival.game;

import main.java.org.zambiesurvival.engine.state.TitleState;
import main.java.org.zambiesurvival.engine.Game;
import main.java.org.zambiesurvival.engine.GameContainer;
import main.java.org.zambiesurvival.engine.state.WorldState;

public class ZambieSurvival {
    
    public static void main(String[] args) {
        Game game = new Game();
        game.addState("Title", new TitleState());
        game.addState("World", new WorldState(32));
        game.enterState("Title", true);
        new GameContainer(game, "Zambie Survival", 720, 480).start();
    }
    
}