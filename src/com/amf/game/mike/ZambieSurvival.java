package com.amf.game.mike;

import com.amf.engine.GameContainer;
import com.amf.engine.Game;

public class ZambieSurvival {
    
    public static void main(String[] args) {
        Game game = new Game();
        game.addState("Title", new TitleState());
        game.addState("Main Game", new MainGameState());
        game.enterState("Title", true);
        new GameContainer(game, "Zambie Survival").start();
    }
    
}