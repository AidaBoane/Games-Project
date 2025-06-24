package components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import entity.IRenderableComponent;
import entity.IUpdateComponent;
import player.Player;

public class Portal implements IRenderableComponent, IUpdateComponent {

    private final Texture texture;
    private final float x, y;
    private final Rectangle bounds;
    private float tempoParado = 0f;
    private boolean iniciouTransicao = false;

    private final float TEMPO_ESPERA = 2.0f; // Tempo que o player deve permanecer parado

    public Portal(float x, float y) {
        this.x = x;
        this.y = y;
        this.texture = new Texture("portal.png");
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    @Override
    public void update(float delta) {
        // vazio por agora
    }

    public void update(float delta, Player player) {
        if (player.getBounds().overlaps(bounds) && player.isIdle()) {
            tempoParado += delta;
         if (tempoParado >= TEMPO_ESPERA && !iniciouTransicao) {
          iniciouTransicao = true;
          player.setTeleporting(true); // ativa efeito de fade no player
          }
         } else {
           tempoParado = 0f;
          }
    }

    public boolean deveTransicionar() {
        return iniciouTransicao;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
