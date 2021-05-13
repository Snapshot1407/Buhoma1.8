package com.mygdx.buhoma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class QuestScreen extends BaseScreen {

    Stage stage;

    final Buhoma game;
    OrthographicCamera camera;

    Texture imgWindow;
    int n_increase = 0;

    //

    Word word = new Word();


    //отступы от нижнего края и левого края
    static final float const_Width_S = 0.14f;
    static final float const_Height_S = 0.15f;

    //константы прямоугольник размеры его
    static final float const_Width_P = 0.71875f;
    static final float const_Height_P = 0.69444f;

    Vector3 touchPos;

    private MyFontButton skin;



    private TextButton play;
    private TextButton exit;

    int k[];

    //переменные для таймера спавна
    long timeGame= TimeUtils.millis(),timeSpawn=3000,timeLastSpawn=TimeUtils.millis()-4000;
    long timeIncrease = 3000;
    long timeDelete = 0;
    long timeExit = 2000;
    long timeMarkerExit;

    //переменная для количества букв в слове

    //переменная для подсчета сколько букв удалили

    //проверяет все ли буквы вылетели
    boolean bolExited=false;
    //для создания списка букв
    //масив буквы рамдомной
    Array<Integer> arrayLetter = new Array<>();//должны быть на экране
    Array <Letter> letter = new Array<>(); // есть на экране

    String stringK = new String();

    int marker = -1;
    long timetouch;

    Label label_Letter;

    //

    public QuestScreen(Buhoma game) {
        super(game);
        this.game = game;

        stage = new Stage();

        // создает камеру
        camera = new OrthographicCamera(Buhoma.width, Buhoma.height);
        camera.setToOrtho(false, Buhoma.width, Buhoma.height );
        // этим методом мы центруем камеру на половину высоты и половину ширины

        touchPos = new Vector3();






    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        word.setSize(Buhoma.width* const_Width_P,Buhoma.height*const_Height_P);
        word.setPosition(Buhoma.width * const_Width_S,Buhoma.height* const_Height_S);
        stage.addActor(word);


        for (int i =0;i<k.length;i++)arrayLetter.add(k[i]);
        // кожа кнопки
        skin = new MyFontButton();

        //создание кнопок
        play = new TextButton("Играть",skin.textButtonStyle_play);
        exit = new TextButton("Выход",skin.textButtonStyle_exit);


        //Setsize button
        play.setSize(200,100);
        exit.setSize(200,100);

        //setPosition button
        play.setPosition(Buhoma.width - play.getWidth(),Buhoma.height-play.getHeight());
        exit.setPosition(Buhoma.width - exit.getWidth(),0);

        //повесили слушателей
        play.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                n_increase = 0;
                touchPos.set(-20,-20,0);
                bolExited = false;
                letter = new Array<>();
                stringK = "";
                word.playSnd = true;
                label_Letter.remove();
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

        stage.addActor(play);

        stage.addActor(exit);
        //кейс для превращения индекса буквы в букву
        for (int i=0 ;i<k.length;i++){
        switch (k[i]) {
            case 0:
                stringK=stringK+"А";
                break;
            case 1:
                stringK=stringK+"Б";
                break;
            case 2:
                stringK=stringK+"В";
                break;
            case 3:
                stringK=stringK+"Г";
                break;
            case 4:
                stringK=stringK+"Д";
                break;
            case 5:
                stringK=stringK+"Е";
                break;
            case 6:
                stringK=stringK+"Ё";
                break;
            case 7:
                stringK=stringK+"Ж";
                break;
            case 8:
                stringK=stringK+"З";
                break;
            case 9:
                stringK=stringK+"И";
                break;
            case 10:
                stringK=stringK+"Й";
                break;
            case 11:
                stringK=stringK+"К";
                break;
            case 12:
                stringK=stringK+"Л";
                break;
            case 13:
                stringK=stringK+"М";
                break;
            case 14:
                stringK=stringK+"Н";
                break;
            case 15:
                stringK=stringK+"О";
                break;
            case 16:
                stringK=stringK+"П";
                break;
            case 17:
                stringK=stringK+"Р";
                break;
            case 18:
                stringK=stringK+"С";
                break;
            case 19:
                stringK=stringK+"Т";
                break;
            case 20:
                stringK=stringK+"У";
                break;
            case 21:
                stringK=stringK+"Ф";
                break;
            case 22:
                stringK=stringK+"Х";
                break;
            case 23:
                stringK=stringK+"Ц";
                break;
            case 24:
                stringK=stringK+"Ч";
                break;
            case 25:
                stringK=stringK+"Ш";
                break;
            case 26:
                stringK=stringK+"Щ";
                break;
            case 27:
                stringK=stringK+"Ъ";
                break;
            case 28:
                stringK=stringK+"Ы";
                break;
            case 29:
                stringK=stringK+"Ь";
                break;
            case 30:
                stringK=stringK+"Э";
                break;
            case 31:
                stringK=stringK+"Ю";
                break;
            case 32:
                stringK=stringK+"Я";
                break;
            default:
                break;
        }
            label_Letter = new Label("", new Label.LabelStyle(skin.getFont(), new Color(1, 1, 1, 1)));
            label_Letter.setPosition(100,50);
            label_Letter.setText(stringK);

    }
        play.setDisabled(true);
        play.setVisible(false);
    }

    public void massive(int []k){
        this.k = k;
    }
    public void cntWord(int r){word.setType(r);}


    @Override
    public void render(float delta) {




        camera.update();
        //game.batch.setProjectionMatrix(camera.combined);



        // для обнуления подсчета мертых


        if(Gdx.input.justTouched()){
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);
        }


        if (arrayLetter.isEmpty() && letter.isEmpty()){
            bolExited = true;
            word.setLabel(true);

        }
        //переключение на игровое окно

        timeGame=TimeUtils.millis();

        if ( marker >= 0  && timeGame - timetouch > timeDelete){

            for (int i = 0; i < letter.size; i++) {

                if (letter.get(i).isAlive()){
                    letter.get(i).resume();
                }

            }
            letter.get(marker).remove();
            letter.removeIndex(marker);

            marker = -1;
            timeDelete = 0;
            timeMarkerExit = TimeUtils.millis();
        }

        if (0 < arrayLetter.size && (timeGame - timeLastSpawn - (timeIncrease * n_increase)>timeSpawn)){
            int num = MathUtils.random(0,arrayLetter.size-1);

            Letter letter1 = new Letter();

            letter1.setPosition(400,400);
            letter1.setType(arrayLetter.get(num));
            letter1.setStringScreen("Quest");

            letter.add(letter1);

            n_increase = 0;
            stage.addActor(letter1);
            arrayLetter.removeIndex(num);
            timeLastSpawn=TimeUtils.millis();


        }
        for (int n = 0;letter.size > n;n++) {
            if (letter.get(n).isTouchable())
                letter.get(n).hit(touchPos.x,touchPos.y,true);


            if(!letter.get(n).isAlive()){
                long time = TimeUtils.millis();
                timetouch = time;

                letter.get(n).setAlive(true);



                while (letter.get(n).increase){
                    for (int i = 0; i < letter.size; i++) {
                        if (i != marker && letter.get(i).isAlive()){
                            letter.get(i).stop();
                            }
                    }
                    letter.get(n).increase(Buhoma.height*const_Height_P);

                    letter.get(n).setPosition((Buhoma.width * const_Width_S)+((Buhoma.width* const_Width_P)/2)-(letter.get(n).width/2),
                            (Buhoma.height* const_Height_S)+((Buhoma.height*const_Height_P)/2)-(letter.get(n).height/2));

                    if (time+timeIncrease >= TimeUtils.millis() ||
                            (letter.get(n).width >= (Buhoma.width * const_Width_S) &&
                                    letter.get(n).height >= Buhoma.height* const_Height_S )){
                        letter.get(n).setIncrease(false);
                        letter.get(n).setAlive(false);

                        n_increase = 1;
                        break;
                    }
                }

                if (word.isLabel()){
                    word.playSnd = false;
                    word.playSnd();
                    stage.addActor(label_Letter);
                }

            }
        }

        //операция которая отвечает за воспроизведения звука и показа слова
        if (bolExited && word.playSnd && word.isLabel()){
            System.out.println(word.type+"type");
            word.playSnd = false;
            word.playSnd();
            stage.addActor(label_Letter);
        }
        //операция которая отвечает за выход из квестового окна в игровое
        //операция которая отвечает за выход из игры

        /*
        if (touchPos.x<Buhoma.width &&
                touchPos.x>Buhoma.width-play.getWidth() &&
                touchPos.y>0 &&
                touchPos.y<play.getHeight()){
            Gdx.app.exit();
        }*/



        //метод отвечающий за движения букв


        stage.act(delta);
        stage.draw();
        touchPos.set(-10,-10,0);

        for (int i = 0; i < letter.size; i++) {
            if (!letter.get(i).isAlive() && !letter.get(i).isIncrease()){
                letter.get(i).setMarker(true);
                marker = i;
                timeDelete = 2000;
            }


        }





        if (bolExited){
            play.setDisabled(false);
            play.setVisible(true);


        }
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
    public void dispose() {
        stage.dispose();
        imgWindow.dispose();


    }
}
