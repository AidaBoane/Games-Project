package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import entity.IRenderableComponent;
import entity.IUpdateComponent;
import entity.CollidableComponent;
import entity.CollisionCategory;

import java.util.ArrayList;
import java.util.List;

public class Inimigo implements IRenderableComponent, IUpdateComponent, CollidableComponent {

    private Texture texture;
    private Vector2 position;
    private float velocidade = 1f;
    private float limiteEsquerda, limiteDireita;
    private boolean indoDireita = true;
    private boolean vivo = true;

    public Inimigo(String path, float x, float y) {
        this.texture = new Texture(path);
        this.position = new Vector2(x, y);
        this.limiteEsquerda = x - 30;
        this.limiteDireita = x + 30;
    }

    @Override
    public void update(float delta) {
        if (!vivo) return;

        if (indoDireita) {
            position.x += velocidade;
            if (position.x > limiteDireita) indoDireita = false;
        } else {
            position.x -= velocidade;
            if (position.x < limiteEsquerda) indoDireita = true;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if (vivo) {
            batch.draw(texture, position.x, position.y);
        }
    }

    public static List<Inimigo> gerarInimigos() {
        List<Inimigo> inimigos = new ArrayList<>();
        inimigos.add(new Inimigo("monster1.png", 1970, 140));
        inimigos.add(new Inimigo("monster1.png", 5991, 140));
        inimigos.add(new Inimigo("monster1.png", 8205, 370));
        inimigos.add(new Inimigo("monster1.png", 12450, 140));
        inimigos.add(new Inimigo("monster1.png", 17735, 260));
        inimigos.add(new Inimigo("monster1.png", 14050, 140));



        return inimigos;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public CollisionCategory getCategory() {
        return CollisionCategory.INIMIGO;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void morrer() {
        vivo = false;
    }

    public void dispose() {
        texture.dispose();
    }
}
