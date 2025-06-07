package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.IRenderableComponent;
import java.util.ArrayList;
import java.util.List;

public class Plataforma implements IRenderableComponent {

    private Texture texture;
    private float x, y;

    public Plataforma(String path, float x, float y) {
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

    public static List<Plataforma> gerarPlataformas() {
        List<Plataforma> plataformas = new ArrayList<>();


        plataformas.add(new Plataforma("plataforma_media.png", 400, 170));
        plataformas.add(new Plataforma("plataforma_media.png", 625, 170));
        plataformas.add(new Plataforma("plataforma_media.png", 850, 170));
        plataformas.add(new Plataforma("plataforma_media.png", 1075, 170));
        plataformas.add(new Plataforma("plataforma_media.png", 1075, 350));
        plataformas.add(new Plataforma("plataforma_longa.png", 1300, 170));
        plataformas.add(new Plataforma("plataforma_media.png", 1705, 170));
        plataformas.add(new Plataforma("plataforma_media.png", 1929, 170));
        plataformas.add(new Plataforma("plataforma_longa.png", 3000, 170));
        plataformas.add(new Plataforma("plataforma_media.png", 3400, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 3700, 240));
        plataformas.add(new Plataforma("plataforma_longa.png", 4100, 130));

        return plataformas;
    }
}
