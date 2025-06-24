package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Kunai {
    private static Texture sharedTexture;

    static {
        sharedTexture = new Texture("player/kunai.png");
    }

    private Vector2 position;
    private Vector2 velocity;

    public Kunai(Vector2 position, Vector2 velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void update(float delta) {
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
    }

    public void render(SpriteBatch batch) {
        if (velocity.x < 0) {

            batch.draw(sharedTexture, position.x + sharedTexture.getWidth(), position.y,
                -sharedTexture.getWidth(), sharedTexture.getHeight());
        } else {

            batch.draw(sharedTexture, position.x, position.y);
        }
    }
    private boolean ativo = true;

    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, sharedTexture.getWidth(), sharedTexture.getHeight());
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

        public static void disposeShared() {
        if (sharedTexture != null) {
            sharedTexture.dispose();
            sharedTexture = null;
        }
    }

    public Vector2 getPosition() {
        return position;
    }

}
