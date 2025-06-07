package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.IRenderableComponent;

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
}
