package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ComponentScene {

    private Texture heartTexture;
    private int heartsRemaining = 3;

    // Tamanho e espaçamento dos corações
    private final int HEART_WIDTH = 35;
    private final int HEART_HEIGHT = 32;
    private final int SPACING = 10;

    public ComponentScene() {
        heartTexture = new Texture(Gdx.files.internal("heart.png"));
    }

    public void render(SpriteBatch batch) {
        int screenWidth = Gdx.graphics.getWidth();
        for (int i = 0; i < heartsRemaining; i++) {
            int x = screenWidth - (i + 1) * (HEART_WIDTH + SPACING);
            int y = Gdx.graphics.getHeight() - HEART_HEIGHT - 20;
            batch.draw(heartTexture, x, y, HEART_WIDTH, HEART_HEIGHT);
        }
    }

    public void takeDamage() {
        if (heartsRemaining > 0) {
            heartsRemaining--;
        }
    }

    public boolean isDead() {
        return heartsRemaining == 0;
    }

    public void reset() {
        heartsRemaining = 3;
    }

    public void dispose() {
        heartTexture.dispose();
    }
}
