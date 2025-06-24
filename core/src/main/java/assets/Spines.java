package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import entity.CollidableComponent;
import entity.CollisionCategory;
import entity.IRenderableComponent;

import java.util.ArrayList;
import java.util.List;

public class Spines implements IRenderableComponent, CollidableComponent {

    private Texture texture;
    private float x, y;

    public Spines(String path, float x, float y) {
        this.texture = new Texture(path);
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    public static List<Spines> gerarEspinhos() {
        List<Spines> espinhos = new ArrayList<>();

        espinhos.add(new Spines("espinhos.png", 1120, 155));
        espinhos.add(new Spines("espinhos.png", 10000, 155));
        espinhos.add(new Spines("espinhos.png", 9905, 155));
        espinhos.add(new Spines("espinhos.png", 10400, 155));
        espinhos.add(new Spines("espinhos.png", 10495, 155));
        espinhos.add(new Spines("espinhos.png", 3535, 155));


        return espinhos;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public CollisionCategory getCategory() {
        return CollisionCategory.ESPINHO;
    }
}
