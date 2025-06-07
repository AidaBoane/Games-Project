package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Background1 {
    private Texture corredor;
    private List<Decoracao> decoracoes;

    public Background1() {
        corredor = new Texture("background1.png");
        decoracoes = new ArrayList<>();

        decoracoes.add(new Decoracao(new Texture("skeletonpendurado.png"),100,600));

        // Loop para múltiplos esqueletos na parede
        Texture skeletonTexture = new Texture("skeletonpendurado.png");
        for (int i = 0; i < 10; i++) {
            float x = 900 + i * 1700;  // espaço entre os esqueletos
            float y = 600;            // altura fixa
            decoracoes.add(new Decoracao(skeletonTexture, x, y));
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(corredor, 0, 0);

        for (Decoracao decor : decoracoes) {
            decor.render(batch);
        }
    }

    public void dispose() {
        corredor.dispose();
        for (Decoracao decor : decoracoes) {
            decor.dispose();
        }
    }

    // Classe interna para decorar ou adicionar obstáculos
    private static class Decoracao {
        Texture texture;
        float x, y;

        public Decoracao(Texture texture, float x, float y) {
            this.texture = texture;
            this.x = x;
            this.y = y;
        }

        public void render(SpriteBatch batch) {
            batch.draw(texture, x, y);
        }

        public void dispose() {
            texture.dispose();
        }
    }
}
