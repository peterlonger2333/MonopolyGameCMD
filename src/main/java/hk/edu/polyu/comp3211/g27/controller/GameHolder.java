package hk.edu.polyu.comp3211.g27.controller;

import hk.edu.polyu.comp3211.g27.model.Game;

public class GameHolder extends ThreadLocal<Game> {
    @Override
    protected Game initialValue() {
        return super.initialValue();
    }

    public GameHolder() {
        super();
    }

    @Override
    public Game get() {
        return super.get();
    }

    @Override
    public void set(Game value) {
        super.set(value);
    }

    @Override
    public void remove() {
        super.remove();
    }
}
