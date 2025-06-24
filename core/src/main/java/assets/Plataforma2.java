package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.IRenderableComponent;
import java.util.ArrayList;
import java.util.List;

public class Plataforma2 implements IRenderableComponent {

    private Texture texture;
    private float x, y;

    public Plataforma2(String path, float x, float y) {
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

// 247

        plataformas.add(new Plataforma("plataforma_longa2.png", 155, 100));
        plataformas.add(new Plataforma("plataforma_longa2.png", 565, 100));
        plataformas.add(new Plataforma("plataforma_longa2.png", 975, 100));
        plataformas.add(new Plataforma("plataforma_longa2.png", 1385, 100));
        plataformas.add(new Plataforma("plataforma_longa2.png", 1795, 100));
        plataformas.add(new Plataforma("plataforma_media2.png", 1075, 270));
        plataformas.add(new Plataforma("plataforma_longa2.png", 2205, 100));
        plataformas.add(new Plataforma("plataforma_longa2.png", 2615, 100));
        plataformas.add(new Plataforma("plataforma_longa2.png", 1869, 100));
        plataformas.add(new Plataforma("plataforma_longa2.png", 2115, 100));
        plataformas.add(new Plataforma("plataforma_longa2.png", 2361, 100));
        plataformas.add(new Plataforma("plataforma_longa2.png", 2606, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 2852, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 3100, 230));
        plataformas.add(new Plataforma("plataforma_media.png", 3310, 350));
        plataformas.add(new Plataforma("plataforma_media.png", 3520, 470));
        plataformas.add(new Plataforma("plataforma_media.png", 3520, 100));
        plataformas.add(new Plataforma("plataforma_media.png",3766 , 100));
        plataformas.add(new Plataforma("plataforma_media.png", 4012, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 3680, 350));
        plataformas.add(new Plataforma("plataforma_media.png", 3869, 230));
        plataformas.add(new Plataforma("plataforma_media.png", 4258, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 4505, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 4751, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 5000, 230));
        plataformas.add(new Plataforma("plataforma_media.png", 5250, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 5400, 310));
        plataformas.add(new Plataforma("plataforma_media.png", 5497, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 5743, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 5991, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 6238, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 6486, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 6700, 230));
        plataformas.add(new Plataforma("plataforma_media.png", 6970, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 7217, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 7464, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 7711, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 7958, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 8205, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 8452, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 8700, 230));
        plataformas.add(new Plataforma("plataforma_media.png", 8917, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 9164, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 9411, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 9658, 230));
        plataformas.add(new Plataforma("plataforma_media.png", 9905, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 10152, 230));
        plataformas.add(new Plataforma("plataforma_media.png", 10399, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 10646, 230));
        plataformas.add(new Plataforma("plataforma_media.png", 10893, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 11140, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 11387, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 11634, 100));
        plataformas.add(new Plataforma("plataforma_media.png", 11881, 100));



        return plataformas;
    }
}
