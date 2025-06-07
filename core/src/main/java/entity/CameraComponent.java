package entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraComponent {
    public OrthographicCamera camera;
    public Vector2 target;
    public float lerp = 0.1f; // mais suave

    public CameraComponent(float viewportWidth, float viewportHeight) {
        camera = new OrthographicCamera(viewportWidth * 1.3f, viewportHeight * 1.3f); // Zoom out aplicado
        float startX = viewportWidth / 1f;
        float startY = viewportHeight / 1.5f;

        camera.position.set(startX, startY, 0);
        target = new Vector2(startX, startY); // Inicializa o target igual à posição
        camera.update();
    }

    public void follow(Vector2 playerPosition) {
        target.set(playerPosition);
    }

    public void update() {

        camera.position.lerp(new Vector3(target.x, target.y, 0), lerp);
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
