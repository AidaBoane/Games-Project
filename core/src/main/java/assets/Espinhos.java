package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.IRenderableComponent;
import java.util.ArrayList;
import java.util.List;

public class Espinhos implements IRenderableComponent {

    private Texture texture;
    private float x, y;

    public Espinhos(String path, float x, float y) {
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

    public static List<Espinhos> gerarEspinhos() {
        List<Espinhos> espinhos = new ArrayList<>();

        espinhos.add(new Espinhos("espinhos.png", 1120, 250));


        return espinhos;
    }
}
