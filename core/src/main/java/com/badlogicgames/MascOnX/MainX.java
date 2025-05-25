package com.badlogicgames.MascOnX;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainX extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));

    }
}
