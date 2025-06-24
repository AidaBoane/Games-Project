package systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogicgames.MascOnX.MainX;

public class AudioManager {
    private MainX game;
    private Music currentMusic;

    public AudioManager() {}

    public void init(MainX game) {
        this.game = game;

        if (game.musica != null && game.musica.isPlaying()) {
            game.musica.stop();
        }
    }

    public void playMusic(String filePath, boolean loop) {
        if (!AudioController.isMusicaAtivada()) return;

        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }

        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        currentMusic.setLooping(loop);
        currentMusic.play();
    }

    public void stopCurrentMusic() {
        if (currentMusic != null) currentMusic.stop();
    }

    public void dispose() {
        if (currentMusic != null) currentMusic.dispose();
        if (game != null && game.musica != null) game.musica.dispose();
    }
}
