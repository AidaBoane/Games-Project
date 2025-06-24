package entity;

import com.badlogic.gdx.math.Rectangle;

public interface CollidableComponent {

    /**
     * Retorna a hitbox da entidade para verificar colisão.
     */
    Rectangle getBounds();

    /**
     * Retorna a categoria da entidade colidível (ex: PLATAFORMA, INIMIGO, ESPINHO).
     */
    CollisionCategory getCategory();
}
