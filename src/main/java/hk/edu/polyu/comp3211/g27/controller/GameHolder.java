package hk.edu.polyu.comp3211.g27.controller;

import hk.edu.polyu.comp3211.g27.model.Game;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Holds a {@link Game} for each thread. Note that the game is itself a single-thread implementation.
 */
public class GameHolder {
    private static final ThreadLocal<Game> threadLocalGame = new ThreadLocal<>();

    public static void set(@NotNull Game game) {
        threadLocalGame.set(game);
    }

    public static @Nullable Game get() {
        return threadLocalGame.get();
    }
}
