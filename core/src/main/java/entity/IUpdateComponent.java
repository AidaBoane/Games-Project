package entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IUpdateComponent {
    void render(SpriteBatch batch);
    void update(float delta);
}
