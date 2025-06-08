package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogicgames.MascOnX.MainX;

/** First screen of the application. Displayed after the application is created. */
public class MenuScreen implements Screen {
    private final MainX game;


    private SpriteBatch spriteBatch;
    private Texture background;
    private Texture axe;
    private  Texture logoiadeTexture;
    private Texture titleTexture;
    private BitmapFont font;
    private Music music;
    private FitViewport viewport;
    private Texture buttonTexture;
    private TextureRegion[] buttonRegions;
    private Rectangle[] buttonBounds;


    public MenuScreen(MainX mainX) {
        this.game = mainX;
    }

    @Override
    public void show() {

        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(19, 10.2f);

        background = new Texture("menubackground.png"); // substitua pela sua imagem
        axe = new Texture("axe.png"); // imagem do machado
        logoiadeTexture = new Texture("IADE.png");
        titleTexture = new Texture("mascon.png");
        font = new BitmapFont(); // Fonte padrão, sem dependência externa


        music = Gdx.audio.newMusic(Gdx.files.internal("menumusic.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        buttonTexture = new Texture("menu_buttons.png");

        TextureRegion[][] tmp = TextureRegion.split(buttonTexture,
            buttonTexture.getWidth(), buttonTexture.getHeight() / 3);

        buttonRegions = new TextureRegion[3];
        buttonBounds = new Rectangle[3];

        for (int i = 0; i < 3; i++) {
            buttonRegions[i] = tmp[i][0];
        }

      // posição dos botoes na tela
        float btnWidth = 4f;
        float btnHeight = 1.5f;
        float startY = 4f;


        for (int i = 0; i < 3; i++) {
            float x = (viewport.getWorldWidth() - btnWidth) / 2f;
            float y = startY - i * 2f;

            buttonBounds[i] = new Rectangle(x, y, btnWidth, btnHeight);
        }
    }
            @Override
            public void render(float delta) {
                // Draw your screen here. "delta" is the time since last render in seconds.
                input();
                logic();
                draw();

                ScreenUtils.clear(Color.BLACK);
                viewport.apply();
                spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
                spriteBatch.begin();

                float worldWidth = viewport.getWorldWidth();
                float worldHeight = viewport.getWorldHeight();

                // fundo
                spriteBatch.draw(background, 0, 0, worldWidth, worldHeight);

                // IADE logo
                spriteBatch.draw(logoiadeTexture, 0, 9.55f, 4f, 0.7f);

                //Tamanho e posição do títilo"mascon" a frente do fundo
                float logoWidth = 7f;
                float logoHeight = 5f;
                float logoX = (worldWidth - logoWidth) / 2f;
                float logoY = worldHeight - logoHeight - 0.5f;

                spriteBatch.draw(titleTexture, logoX, logoY, logoWidth, logoHeight);

                for (int i = 0; i < 3; i++) {
                    Rectangle bounds = buttonBounds[i];

                    // sombra quando o mouse está sobre o botão
                    Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                    viewport.unproject(mouse);

                    boolean hovered = bounds.contains(mouse.x, mouse.y);
                    Color color = hovered ? Color.LIGHT_GRAY : Color.WHITE;
                    spriteBatch.setColor(color);

                    spriteBatch.draw(buttonRegions[i], bounds.x, bounds.y, bounds.width, bounds.height);

                    // Clique
                    if (hovered && Gdx.input.justTouched()) {
                        handleButtonClick(i);
                    }
                }

                spriteBatch.setColor(Color.WHITE); // reset


                spriteBatch.end();


            }
      // clique nos botoes
    private void handleButtonClick(int index) {
        switch (index) {
            case 0: // Jogar
                System.out.println("Iniciar jogo!");
                break;
            case 1: // Opções
                System.out.println("Abrir opções.");
                break;
            case 2: // Sair
                Gdx.app.exit();
                break;
        }
    }

    @Override
            public void resize(int width, int height) {
                viewport.update(width, height, true);
                // Resize your screen here. The parameters represent the new window size.
            }

            public void draw() {
                ScreenUtils.clear(Color.BLACK); // cor base de fundo do jogo
                viewport.apply();
                spriteBatch.setProjectionMatrix(viewport.getCamera().combined);// garate as imagens no local correto
                spriteBatch.begin();

                float worldWidth = viewport.getWorldWidth();
                float worldHeight = viewport.getWorldHeight();

                // posiçao e tamanho do logo iade
                spriteBatch.draw(background, 0, 0, worldWidth, worldHeight);
                spriteBatch.draw(logoiadeTexture, 0, 9.55f, 4f, 0.7f);

                font.getData().setScale(0.1f);
                font.setColor(Color.WHITE);

                String titulo = "MascON";

                GlyphLayout layout = new GlyphLayout(font, titulo);
                float textX = (worldWidth - layout.width) / 2f;
                float textY = worldHeight - 1.0f;

                font.draw(spriteBatch, titulo, textX, textY);


                spriteBatch.end();
            }


            public void input() {
                // Invoked when your application is paused.
            }


            public void logic() {
                // Invoked when your application is paused.
            }

            @Override
            public void pause() {
                // Invoked when your application is paused.
            }

            @Override
            public void resume() {
                // Invoked when your application is resumed after pause.
            }

            @Override
            public void hide() {
                // This method is called when another screen replaces this one.
            }

            @Override
            public void dispose() {
                // Destroy screen's assets here.


                font.dispose();
                spriteBatch.dispose();
                background.dispose();
                axe.dispose();
                logoiadeTexture.dispose();
                font.dispose();
                music.dispose();
                titleTexture.dispose();
            }
        }

