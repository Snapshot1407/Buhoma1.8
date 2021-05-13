package com.mygdx.buhoma;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenuScreen extends BaseScreen {



    OrthographicCamera camera;



    private static float START_VERT_POSITION_FACTOR = 2.7f; // задаём позицию конпки start
    private static float EXIT_VERT_POSITION_FACTOR = 4.2f; // задаём позицию кнопки exit



///главное меню(1-экран)


    public final static float const_button_width = 0.15625f;
    public final static float const_button_height = 0.13889f;


    private MyFontButton skin;


    private TextButton play;
    private TextButton exit;


    public MainMenuScreen( final Buhoma game) {
        super(game);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(game.stage);
        Gdx.input.setCatchBackKey(true);

        // устанавливаем переменные высоты и ширины в качестве области просмотра нашей игры
        camera = new OrthographicCamera(Buhoma.width, Buhoma.height);

        // этим методом мы центруем камеру на половину высоты и половину ширины
        camera.setToOrtho(false,Buhoma.width,Buhoma.height);




        // кожа кнопки
        skin = new MyFontButton();

        //локатор русского языка


        // функция отвечающая за инициилизацию русских букв

        //отрисовка кнопок и их надписей



        //создание кнопок
        play = new TextButton("Играть",skin.textButtonStyle_play);
        exit = new TextButton("Выход",skin.textButtonStyle_exit);


        //Setsize button
        play.setSize(Buhoma.width*const_button_width,Buhoma.height*const_button_height);
        exit.setSize(Buhoma.width*const_button_height,Buhoma.height*const_button_height);

        //setPosition button
        play.setPosition((Buhoma.width/2f - play.getWidth()/2) ,Buhoma.height - (Buhoma.height/4) - (play.getHeight()/2));
        exit.setPosition((Buhoma.width/2f - exit.getWidth()/2), (Buhoma.height/4) - (play.getHeight()/2));


        //повесили слушателей
        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.stage.dispose();
                game.setScreen(game.gameScreen);

            }
        });


        exit.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });


        // добавление объектов на сцеену

        game.stage.addActor(play);

        game.stage.addActor(exit);


    }

    @Override
    public void render(float delta) {

        //рендер





        camera.update();
        //game.batch.setProjectionMatrix(camera.combined);



        // запуск метода act актеров, которые были добавлены в сцену
        game.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        // прорисовка сцены
        game.stage.draw();

    }

    @Override
    public void resize(float width, float height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {//не забывай!!!
        game.stage.dispose();

    }
}
