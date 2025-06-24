package systems;

public class AudioController {
    private static boolean musicaAtivada = true;

    public static boolean isMusicaAtivada() {
        return musicaAtivada;
    }

    public static void setMusicaAtivada(boolean ativada) {
        musicaAtivada = ativada;
    }

    public static void toggleMusica() {
        musicaAtivada = !musicaAtivada;
    }
}
