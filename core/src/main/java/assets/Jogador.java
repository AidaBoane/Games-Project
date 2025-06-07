package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Jogador {
    public float x = 100, y = 100;
    public Texture texture;
    public Rectangle bounds;

    public Jogador() {
        texture = new Texture("player_idle.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update(float delta) {
        float speed = 200;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= speed * delta;
        bounds.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
