package assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import entity.IRenderableComponent;
import entity.CollidableComponent;
import entity.CollisionCategory;
import java.util.ArrayList;
import java.util.List;

public class Plataforma implements IRenderableComponent, CollidableComponent {

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
        plataformas.add(new Plataforma("plataforma_media.png", -92, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 155, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 400, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 645, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 890, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 1135, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 1075, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 1379, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 1625, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 1869, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 2115, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 2361, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 2606, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 2852, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 3100, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 3310, 350));
        plataformas.add(new Plataforma("plataforma_media.png", 3520, 470));
        plataformas.add(new Plataforma("plataforma_media.png", 3520, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 3766, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 4012, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 3680, 350));
        plataformas.add(new Plataforma("plataforma_media.png", 3869, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 4258, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 4505, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 4751, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 5000, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 5250, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 5400, 310));
        plataformas.add(new Plataforma("plataforma_media.png", 5497, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 5743, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 5991, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 6238, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 6486, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 6700, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 6970, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 7217, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 7464, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 7711, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 7958, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 8205, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 8452, 320));
        plataformas.add(new Plataforma("plataforma_media.png", 8700, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 8917, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 9164, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 9411, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 9658, 210));
        plataformas.add(new Plataforma("plataforma_media.png", 9905, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 10152, 210));
        plataformas.add(new Plataforma("plataforma_media.png", 10399, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 10646, 210));
        plataformas.add(new Plataforma("plataforma_media.png", 10893, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 11140, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 11387, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 11634, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 11881, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 12350, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 12597, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 13066, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 13313, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 13782, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 14029, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 14523, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 14770, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 15264, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 15758, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 16005, 80));
        plataformas.add(new Plataforma("plataforma_media.png", 16252, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 16499, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 16996, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 17489,220));
        plataformas.add(new Plataforma("plataforma_media.png", 17735, 220));
        plataformas.add(new Plataforma("plataforma_media.png", 17981, 220));
        return plataformas;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return texture.getWidth();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public CollisionCategory getCategory() {
        return CollisionCategory.PLATAFORMA;
    }
}
