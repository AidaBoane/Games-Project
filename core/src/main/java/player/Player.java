package player;

import assets.Kunai;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import entity.PlayerState;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

public class Player {

    private PlayerState currentState = PlayerState.IDLE;
    private Vector2 position;
    private float stateTime;
    private boolean facingRight = true;
    private Vector2 velocity = new Vector2(0, 0);
    private final float gravity = -900f;
    private final float jumpForce = 550f;
    private boolean onGround = true;
    private boolean sliding = false;
    private boolean teleporting = false;
    private float alpha = 1f;

    private Rectangle bounds;
    private final EnumMap<PlayerState, Animation<TextureRegion>> animations = new EnumMap<>(PlayerState.class);
    private final List<Kunai> kunais = new ArrayList<>();

    public Player(Vector2 position) {
        this.position = position;
        this.stateTime = 0f;
        loadAnimations();
        updateBounds();
    }

    private void loadAnimations() {
        animations.put(PlayerState.IDLE,       loadAnimation("Idle_", 10, Animation.PlayMode.LOOP));
        animations.put(PlayerState.JUMP,       loadAnimation("Jump_", 10, Animation.PlayMode.LOOP));
        animations.put(PlayerState.THROW,      loadAnimation("Throw_", 10, Animation.PlayMode.NORMAL));
        animations.put(PlayerState.JUMPTHROW,  loadAnimation("Jump_Throw_", 10, Animation.PlayMode.NORMAL));
        animations.put(PlayerState.RUN,        loadAnimation("Run_", 10, Animation.PlayMode.LOOP));
        animations.put(PlayerState.SLIDE,      loadAnimation("Slide_", 10, Animation.PlayMode.LOOP));
        animations.put(PlayerState.DEAD,       loadAnimation("Dead_", 10, Animation.PlayMode.NORMAL));
    }

    private Animation<TextureRegion> loadAnimation(String basePath, int frameCount, Animation.PlayMode playMode) {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < frameCount; i++) {
            String file = basePath + String.format("%03d.png", i);
            FileHandle handle = Gdx.files.internal("player/" + file);
            if (!handle.exists()) continue;
            Texture texture = new Texture(handle);
            frames.add(new TextureRegion(texture));
        }

        if (frames.size == 0) {
            Texture fallback = new Texture(1, 1, Pixmap.Format.RGBA8888);
            frames.add(new TextureRegion(fallback));
        }

        Animation<TextureRegion> animation = new Animation<>(0.11f, frames);
        animation.setPlayMode(playMode);
        return animation;
    }

    public void update(float delta) {
        stateTime += delta;

        if (currentState == PlayerState.DEAD) {
            updateBounds();
            return;
        }

        if (!onGround) velocity.y += gravity * delta;
        position.add(velocity.cpy().scl(delta));

        if ((currentState == PlayerState.THROW || currentState == PlayerState.JUMPTHROW)
            && animations.get(currentState).isAnimationFinished(stateTime)) {
            if (onGround) setState(PlayerState.IDLE);
            else setState(PlayerState.JUMP);
        }

        if (position.y < 0) {
            position.y = 0;
            velocity.y = 0;
            onGround = true;
        }

        Iterator<Kunai> iterator = kunais.iterator();
        while (iterator.hasNext()) {
            Kunai k = iterator.next();
            k.update(delta);
            if (!k.isAtivo() || k.getPosition().x < -1000 || k.getPosition().x > 20000) {
                iterator.remove();
            }
        }

        velocity.x = 0;
        updateBounds();
    }

    private void updateBounds() {
        if (bounds == null) {
            bounds = new Rectangle(position.x, position.y, getFrameWidth(), getFrameHeight());
        } else {
            bounds.setPosition(position.x, position.y);
            bounds.setSize(getFrameWidth(), getFrameHeight());
        }
    }

    public void render(SpriteBatch batch) {
        TextureRegion frame = animations.get(currentState).getKeyFrame(stateTime);
        if (!facingRight && !frame.isFlipX()) frame.flip(true, false);
        if (facingRight && frame.isFlipX()) frame.flip(true, false);

        if (teleporting) {
            alpha -= 0.01f;
            if (alpha < 0f) alpha = 0f;
            batch.setColor(1, 1, 1, alpha);
        }

        batch.draw(frame, position.x, position.y);

        if (teleporting) {
            batch.setColor(1, 1, 1, 1);
        }

        for (Kunai k : kunais) k.render(batch);
    }

    public void jump() {
        if (onGround) {
            velocity.y = jumpForce;
            setState(PlayerState.JUMP);
            onGround = false;
        }
    }

    public void moveX(float dx) {
        position.x += dx;
        velocity.x = dx / Gdx.graphics.getDeltaTime();
    }

    public void throwKunai() {
        Vector2 kunaiPos = new Vector2(position.x + (facingRight ? 50 : -10), position.y + 30);
        Vector2 velocity = new Vector2(facingRight ? 400 : -400, 0);
        kunais.add(new Kunai(kunaiPos, velocity));
    }

    public void setState(PlayerState newState) {
        if (newState == PlayerState.DEAD) {
            this.currentState = PlayerState.DEAD;
            this.stateTime = 0;
            return;
        }

        // BLOQUEIO DE MUDANÇA para outras animações enquanto THROW/JUMPTHROW está em execução
        if ((currentState == PlayerState.THROW || currentState == PlayerState.JUMPTHROW)
            && !animations.get(currentState).isAnimationFinished(stateTime)) {
            return;
        }

        if (this.currentState != newState) {
            this.currentState = newState;
            this.stateTime = 0;
        }
    }

    public void checkPlataformaColisao(List<Rectangle> plataformas) {
        onGround = false;
        Rectangle playerHitbox = new Rectangle(position.x, position.y, getFrameWidth(), getFrameHeight());
        Rectangle feetBox = new Rectangle(playerHitbox.x, playerHitbox.y - 2, playerHitbox.width, 10);

        for (Rectangle plataforma : plataformas) {
            if (feetBox.overlaps(plataforma) && velocity.y <= 0) {
                position.y = plataforma.y + plataforma.height;
                velocity.y = 0;
                onGround = true;
                return;
            }
        }

        if (velocity.y == 0 && position.y <= plataformas.get(0).y + plataformas.get(0).height + 5) {
            onGround = true;
        }
    }

    public PlayerState getState() { return currentState; }
    public void startSlide() { sliding = true; setState(PlayerState.SLIDE); }
    public void stopSlide() { sliding = false; if (onGround) setState(PlayerState.IDLE); }
    public Rectangle getBounds() { return bounds; }
    public boolean isIdle() { return currentState == PlayerState.IDLE && !isMovingKeyPressed(); }

    private boolean isMovingKeyPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    public boolean isTeleporting() { return teleporting; }
    public void setTeleporting(boolean teleporting) { this.teleporting = teleporting; }
    public Rectangle getBottomBounds() { return new Rectangle(position.x, position.y, getFrameWidth(), 5); }

    public float getFrameWidth() {
        return animations.get(currentState).getKeyFrame(stateTime).getRegionWidth();
    }

    public float getFrameHeight() {
        return animations.get(currentState).getKeyFrame(stateTime).getRegionHeight();
    }

    public boolean isRunning() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    public List<Kunai> getKunais() { return kunais; }

    public boolean isDeadAnimationFinished() {
        Animation<TextureRegion> deadAnim = animations.get(PlayerState.DEAD);
        return currentState == PlayerState.DEAD && deadAnim.isAnimationFinished(stateTime);
    }

    public float getStateTime() { return stateTime; }

    public void setFacingRight(boolean facingRight) { this.facingRight = facingRight; }
    public boolean isFacingRight() { return facingRight; }
    public boolean isOnGround() { return onGround; }
    public boolean isSliding() { return sliding; }
    public Vector2 getPosition() { return position; }
    public Vector2 getVelocity() { return velocity; }
    public void setOnGround(boolean value) { onGround = value; }

    public void dispose() {
        for (Kunai k : kunais) Kunai.disposeShared();
    }
}
