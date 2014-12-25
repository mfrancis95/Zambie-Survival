package com.amf.engine;

public class Zambie extends Entity {
    
    @Override
    public void performAction(Action action) {
        switch (action) {
            case ATTACK:
                break;
            case MOVE:
                break;
            case USE_ITEM:
            case DO_NOTHING:
            default:
                break;
        }
    }
    
}
