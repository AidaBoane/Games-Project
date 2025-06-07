package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.IRenderableComponent;
import entity.IUpdateComponent;
import java.util.ArrayList;
import java.util.List;

public class Inimigo implements IRenderableComponent, IUpdateComponent {
    private Texture texture;
    private float x, y;
    private float velocidade = 1f;
    private float limiteEsquerda, limiteDireita;
    private boolean indoDireita = true;

    public Inimigo(String path, float x, float y) {
        this.texture = new Texture(path);
        this.x = x;
        this.y = y;
        this.limiteEsquerda = x - 30;
        this.limiteDireita = x + 30;
    }

    @Override
    public void update(float delta) {
        if (indoDireita) {
            x += velocidade;
            if (x > limiteDireita) indoDireita = false;
        } else {
            x -= velocidade;
            if (x < limiteEsquerda) indoDireita = true;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    public static List<Inimigo> gerarInimigos() {
        List<Inimigo> inimigos = new ArrayList<>();

        inimigos.add(new Inimigo("monster1.png", 1970, 250));
      //  inimigos.add(new Inimigo("monster1.png", 1970, 250));
      //  inimigos.add(new Inimigo("monster1.png", 1970, 250));
      //  inimigos.add(new Inimigo("monster1.png", 1970, 250));
      //  inimigos.add(new Inimigo("monster1.png", 1970, 250));
      //  inimigos.add(new Inimigo("monster1.png", 1970, 250));
      //  inimigos.add(new Inimigo("monster1.png", 1970, 250));
      //  inimigos.add(new Inimigo("monster1.png", 1970, 250));

        return inimigos;
    }

}
