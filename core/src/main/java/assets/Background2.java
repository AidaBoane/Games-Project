
package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Background2 {
    private Texture corredor;
    private List<Decoracao> decoracoes;
    private List<Fire> fires;

    private int textureWidth;
    private int textureHeight;
    private int numeroDeRepeticoes = 30;

    public Background2() {
        corredor = new Texture("background2.png");

        textureWidth = corredor.getWidth();
        textureHeight = corredor.getHeight();

        decoracoes = new ArrayList<>();
        Texture skeletonTexture = new Texture("skeletonpendurado.png");


        for (int i = 0; i < 10; i++) {
            float x = 900 + i * 1700;
            float y = 600;

            decoracoes.add(new Decoracao(skeletonTexture, x, y));
        }

        //  Tochas animadas ao longo do corredor
        fires = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            float x = -670 + i * 1300;  // espaçamento entre tochas
            float y = 300;             // altura
            fires.add(new Fire(new Vector2(x, y)));

        }
    }

    public void render(SpriteBatch batch, float cameraX, float delta) {
        float parallaxFactor = 0.5f;
        float parallaxX = cameraX * parallaxFactor;

        // Repetição do fundo com paralaxe
        for (int x = 0; x < numeroDeRepeticoes; x++) {
            float drawX = x * textureWidth - (parallaxX % textureWidth);
            batch.draw(corredor, drawX, -15);
        }

        for (Decoracao decor : decoracoes) {
            decor.render(batch, parallaxX);
        }

        //tochas
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
        return textureWidth * numeroDeRepeticoes; // ajuste se estiver usando tiles repetidos
    }

    public float getWorldHeight() {
        return textureHeight; // altura do fundo (normalmente fixa)
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
