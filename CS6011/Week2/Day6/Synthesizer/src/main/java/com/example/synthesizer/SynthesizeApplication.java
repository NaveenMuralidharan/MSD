package com.example.synthesizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
//import java.awt.*;
import java.io.IOException;

public class SynthesizeApplication extends Application {
    private Clip c;
    private AnchorPane ap;
    @Override
    public void start(Stage stage) throws IOException {
        try {
             c = new PlaySounds().getClip();

        BorderPane bp = new BorderPane();
        bp.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        ap = new AnchorPane();
        ap.setStyle("-fx-background-color: #D3D3D3");

        VBox rightMenu = new VBox();
        rightMenu.setStyle("-fx-border-color: black");
        rightMenu.setPadding(new Insets(10));

        VBox leftMenu = new VBox();
        leftMenu.setStyle("-fx-border-color: black");
        Label lml = new Label("Left Menu");
        leftMenu.getChildren().add(lml);
        leftMenu.setPadding(new Insets(10));

        HBox titleBox = new HBox();
        titleBox.setStyle("-fx-border-color: black");
        Label topLabel = new Label("Synthesizer Application");
        titleBox.getChildren().add(topLabel);
        titleBox.setAlignment(Pos.CENTER);

        HBox bottomBox = new HBox();
        bottomBox.setStyle("-fx-border-color: black");
        bottomBox.setMinHeight(30);

        Button btn1 = new Button("Sine wave");
        btn1.setOnAction(e -> ap.getChildren().add(sineWaveWidget()));
//        btn1.setOnAction(e -> {
//            try {
//                playSound();
//            } catch (LineUnavailableException ex) {
//                ex.printStackTrace();
//            }
//        });
        rightMenu.getChildren().add(btn1);



        bp.setTop(titleBox);
        bp.setRight(rightMenu);
        bp.setLeft(leftMenu);
        bp.setBottom(bottomBox);
        bp.setCenter(ap);

        Scene scene = new Scene(bp, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Synthesizer");
        stage.show();
    } catch (LineUnavailableException e){
            System.out.println("Audio system error");
            e.printStackTrace();
    }
    }

    public HBox sineWaveWidget(){
        HBox hBox = new HBox();
//        hBox.setMinSize(10,10);
        hBox.setStyle("-fx-background-color: #abacf7");
        hBox.setPadding(new Insets(5));

        VBox vBox1 = new VBox();
        vBox1.setStyle("-fx-border-color: black");
        vBox1.setPadding(new Insets(5));
        vBox1.setSpacing(5);
        HBox hBox1_1 = new HBox();
        HBox hBox1_2 = new HBox();
        Label sineLabel = new Label("Sine Wave");
        hBox1_1.getChildren().add(sineLabel);
        Slider slider = new Slider(20,20000,440);
        hBox1_2.getChildren().add(slider);
        vBox1.getChildren().add(hBox1_1);
        vBox1.getChildren().add(hBox1_2);

        VBox vBox2 = new VBox();
        vBox2.setPadding(new Insets(5));
        vBox2.setSpacing(5);
        HBox hBox2_1 = new HBox();
        Button closeButton = new Button("X");
        closeButton.setOnAction(e -> ap.getChildren().remove(0));
        hBox2_1.getChildren().add(closeButton);
        HBox hBox2_2 = new HBox();
        Button playButton = new Button();
        playButton.setShape(new Circle(10));
        playButton.setOnAction(e-> {
            try {
                playSound(slider.getValue());
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        hBox2_2.getChildren().add(playButton);
        vBox2.getChildren().add(hBox2_1);
        vBox2.getChildren().add(hBox2_2);

        hBox.getChildren().add(vBox1);
        hBox.getChildren().add(vBox2);

        return hBox;
    }

    public void playSound(double frequency) throws LineUnavailableException {
        System.out.println("clicked");
//        Clip c = AudioSystem.getClip();
//        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

        AudioComponent gen1 = new SineWave(frequency);
        AudioClip clip = gen1.getClip();

        c.open( c.getFormat(), clip.getData(), 0, clip.getData().length );
        System.out.println( "About to play..." );
        c.start(); // Plays it.
        c.loop( 2 );

        while( c.getFramePosition() < AudioClip.sampleRate || c.isActive() || c.isRunning() ){
            // Do nothing while we wait for the note to play.
        }

        System.out.println( "Done." );
        c.close();

    }

    public static void main(String[] args) {
        launch();
    }
}