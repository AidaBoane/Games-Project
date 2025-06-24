package systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import entity.PlayerState;
import player.Player;

public class InputHandler {
    private final Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    public void handleInput() {
        boolean moving = false;


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.moveX(200 * Gdx.graphics.getDeltaTime());
            player.setFacingRight(true);
            moving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.moveX(-200 * Gdx.graphics.getDeltaTime());
            player.setFacingRight(false);
            moving = true;
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.jump();
            moving = true;
        }


        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.startSlide();
            moving = true;
        } else {
            player.stopSlide();
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!player.isOnGround()) {
                player.setState(PlayerState.JUMPTHROW);
            } else {
                player.setState(PlayerState.THROW);
            }
            player.throwKunai();
            moving = true;
        }


        if (player.getState() != PlayerState.DEAD) {
            if (!moving && player.isOnGround() && !player.isSliding()
                && player.getState() != PlayerState.THROW && player.getState() != PlayerState.JUMPTHROW) {
                player.setState(PlayerState.IDLE);
            } else if (moving && player.isOnGround() && !player.isSliding()
                && player.getState() != PlayerState.THROW && player.getState() != PlayerState.JUMPTHROW) {
                player.setState(PlayerState.RUN);
            }
        }

    }
}
