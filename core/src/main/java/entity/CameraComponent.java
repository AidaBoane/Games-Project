package entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraComponent {

    public OrthographicCamera camera;
    private Vector2 target;
    private float worldWidth;
    private float worldHeight;
    private float lerp = 0.1f;

    public CameraComponent(float viewportWidth, float viewportHeight, float worldWidth, float worldHeight) {
        camera = new OrthographicCamera(viewportWidth * 1.2f, viewportHeight * 1.2f);
        camera.position.set(viewportWidth / 2f, viewportHeight / 2f, 0);
        camera.update();

        this.target = new Vector2(camera.position.x, camera.position.y);
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    public void follow(Vector2 newTarget) {
        this.target.set(newTarget);
    }

    public void update(Vector2 newTarget) {
        follow(newTarget);
        camera.position.lerp(new Vector3(target.x, target.y, 0), lerp);


        float halfWidth = camera.viewportWidth / 2f;
        float halfHeight = camera.viewportHeight / 2f;

        camera.position.x = Math.max(halfWidth, Math.min(camera.position.x, worldWidth - halfWidth));
        camera.position.y = Math.max(halfHeight, Math.min(camera.position.y, worldHeight - halfHeight));

        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public float getViewportWidth() {
        return camera.viewportWidth;
    }

    public float getViewportHeight() {
        return camera.viewportHeight;
    }
}
