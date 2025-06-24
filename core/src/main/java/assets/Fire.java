package assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Fire {
    private static final int FRAME_COLS = 1;
    private static final int FRAME_ROWS = 6;
    private static final float FRAME_DURATION = 0.1f;

    private static Texture fireSheet;
    private static Animation<TextureRegion> fireAnimation;

    private final Vector2 position;
    private float stateTime = 2f;

    public Fire(Vector2 position) {
        this.position = position;

        if (fireAnimation == null) {
            loadAnimation();
        }
    }

    private void loadAnimation() {
        try {
            fireSheet = new Texture(Gdx.files.internal("fire.png")); // ✅ Certifique-se de que o nome do arquivo é simples e está em assets/
        } catch (Exception e) {
            System.err.println("Erro ao carregar fire.png: " + e.getMessage());
            return;
        }

        int frameWidth = fireSheet.getWidth() / FRAME_COLS;
        int frameHeight = fireSheet.getHeight() / FRAME_ROWS;

        TextureRegion[][] tmp = TextureRegion.split(fireSheet, frameWidth, frameHeight);

        Array<TextureRegion> frames = new Array<>();
        for (int row = 1; row < FRAME_ROWS; row++) {
            for (int col = 0; col < FRAME_COLS; col++) {
                frames.add(tmp[row][col]);
            }
        }

        System.out.println(" Animação de tocha carregada com " + frames.size + " frames.");
        fireAnimation = new Animation<>(FRAME_DURATION, frames, Animation.PlayMode.LOOP);
    }

    public void update(float delta) {
        stateTime += delta;
    }

    public void render(SpriteBatch batch, float parallaxX) {
        if (fireAnimation == null) return;
        TextureRegion currentFrame = fireAnimation.getKeyFrame(stateTime);
        batch.draw(currentFrame, position.x - parallaxX, position.y);
    }

    public static void disposeStatic() {
        if (fireSheet != null) {
            fireSheet.dispose();
        }
    }
}
