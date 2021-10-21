package hk.edu.polyu.comp3211.g27.controller;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import hk.edu.polyu.comp3211.g27.model.Game;

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
