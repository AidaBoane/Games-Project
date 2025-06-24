package systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import entity.CollidableComponent;
import entity.CollisionCategory;
import player.Player;
import entity.PlayerState;

import java.util.List;

public class CollisionHandler {
    private boolean colidiuComEspinhoOuInimigo = false;
    private boolean danoRegistrado = false;

    public void verificarColisoes(Player player, List<CollidableComponent> objetosColidiveis) {
        colidiuComEspinhoOuInimigo = false;

        Rectangle playerBounds = player.getBottomBounds();

        for (CollidableComponent obj : objetosColidiveis) {
            Rectangle objetoBounds = obj.getBounds();
            CollisionCategory categoria = obj.getCategory();

            if (playerBounds.overlaps(objetoBounds)) {

                switch (categoria) {



                    case ESPINHO:
                    case INIMIGO:
                        if (!danoRegistrado) {
                            colidiuComEspinhoOuInimigo = true;
                            danoRegistrado = true;
                        }
                        break;
                }
            }
        }


        if (!colidiuComEspinhoOuInimigo) {
            danoRegistrado = false;
        }
    }

    public boolean jogadorFoiAtingido() {
        return colidiuComEspinhoOuInimigo;
    }
}
