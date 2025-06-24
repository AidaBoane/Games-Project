package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Background1 {
    private Texture corredor;
    private List<Decoracao> decoracoes;
    private List<Fire> fires;

    private int textureWidth;
    private int textureHeight;
    private int numeroDeRepeticoes = 30;

    public Background1() {
        corredor = new Texture("background1.png");

        textureWidth = corredor.getWidth();
        textureHeight = corredor.getHeight();

        decoracoes = new ArrayList<>();
        Texture skeletonTexture = new Texture("skeletonpendurado.png");


        for (int i = 0; i < 10; i++) {
            float x = 900 + i * 1700;
            float y = 600;

            decoracoes.add(new Decoracao(skeletonTexture, x, y));
        }


        fires = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            float x = -800 + i * 1532;
            float y = 360;
            fires.add(new Fire(new Vector2(x, y)));

        }
    }

    public void render(SpriteBatch batch, float cameraX, float delta) {

        float parallaxFactor = 0.5f;
        float parallaxX = cameraX * parallaxFactor;


        for (int x = 0; x < numeroDeRepeticoes; x++) {
            float drawX = x * textureWidth - (parallaxX % textureWidth);
            batch.draw(corredor, drawX, -15);
        }

        for (Decoracao decor : decoracoes) {
            decor.render(batch, parallaxX);
        }

        for (Fire fire : fires) {
            fire.update(delta);
            fire.render(batch, parallaxX);
        }
    }
    public void dispose() {
        if (corredor != null) corredor.dispose();
        for (Decoracao decor : decoracoes) {
            decor.dispose();
        }
        Fire.disposeStatic();
    }
    public float getWorldWidth() {
        return textureWidth * numeroDeRepeticoes;
    }

    public float getWorldHeight() {
        return textureHeight;
    }
    private static class Decoracao {
        Texture texture;
        float x, y;


        public Decoracao(Texture texture, float x, float y) {
            this.texture = texture;
            this.x = x;
            this.y = y;
        }

        public void render(SpriteBatch batch, float parallaxX) {
            batch.draw(texture, x - parallaxX, y);
        }

        public void dispose() {
            if (texture != null) texture.dispose();
        }

    }
}
