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

import javax.sound.sampled.*;
//import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SynthesizeApplication extends Application {
    private Clip c;
    private AnchorPane ap;
    ArrayList<AudioComponentWidgetBase> widgets = new ArrayList<>();
    private double currentY= 10;
    public boolean speakerOn = false;
    public Circle speakerButton;

    @Override
    public void start(Stage stage) throws IOException {
        try {
             c = new PlaySounds().getClip();

        BorderPane bp = new BorderPane();
        bp.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        ap = new AnchorPane();
        ap.setStyle("-fx-background-color: #D3D3D3");

        this.speakerButton = new Circle(30);
        this.speakerButton.setFill(Color.RED);
        this.speakerButton.setStroke(Color.BLACK);
        AnchorPane.setRightAnchor(speakerButton, 20.0);
        AnchorPane.setBottomAnchor(speakerButton, 20.0);
        this.speakerButton.setOnMouseClicked(e-> {
//            this.speakerOn = true;
//            this.speakerButton.setFill(Color.LIGHTGREEN);
            this.toggleSpeaker();
        });
        ap.getChildren().add(speakerButton);

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
        btn1.setOnAction(e-> createWidget(btn1.getText()));
        Button btn2 = new Button("Square wave");
        btn2.setOnAction(e-> createWidget(btn2.getText()));
        Button btn3 = new Button("White noise");
        btn3.setOnAction(e-> createWidget(btn3.getText()));
        Button btn4 = new Button("Linear ramp");
        btn4.setOnAction(e-> createWidget(btn4.getText()));
        Button btn5 = new Button("Volume adjuster");
        btn5.setOnAction(e-> createWidget(btn5.getText()));

        rightMenu.getChildren().add(btn1);
        rightMenu.getChildren().add(btn2);
        rightMenu.getChildren().add(btn3);
        rightMenu.getChildren().add(btn4);
        rightMenu.getChildren().add(btn5);


        Button playButton = new Button("Play");
        playButton.setOnAction(e-> {
            try {
                playMixer();
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });

        bottomBox.getChildren().add(playButton);
//        bp.setTop(titleBox);
        bp.setRight(rightMenu);
//        bp.setLeft(leftMenu);
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

    public void toggleSpeaker(){
        if(this.speakerOn){
            this.speakerOn = false;
            this.speakerButton.setFill(Color.RED);
        } else {
            this.speakerOn = true;
            this.speakerButton.setFill(Color.LIGHTGREEN);
        }
    }

    public void createWidget(String buttonText){
        if(buttonText.equals("Sine wave")){
            AudioComponentWidgetBase widget = new SineWaveWidget(ap, buttonText, this, false);
            widgets.add(widget);
            widget.widget.setLayoutX(10);
            widget.widget.setLayoutY(currentY);
            currentY += 8 * (widget.widget.getHeight() + 10);
        }
        else if(buttonText.equals("Square wave")){
            AudioComponentWidgetBase widget = new SquareWaveWidget(ap, buttonText, this, false);
            widgets.add(widget);
            widget.widget.setLayoutX(10);
            widget.widget.setLayoutY(currentY);
            currentY += 8 * (widget.widget.getHeight() + 10);
        }
        else if(buttonText.equals("White noise")){
            AudioComponentWidgetBase widget = new WhiteNoiseWidget(ap, buttonText, this, false);
            widgets.add(widget);
            widget.widget.setLayoutX(10);
            widget.widget.setLayoutY(currentY);
            currentY += 8 * (widget.widget.getHeight() + 10);
        }
        else if(buttonText.equals("Linear ramp")){
            System.out.println("Linear ramp button");
            AudioComponentWidgetBase widget = new LinearRampWidget(ap, buttonText, this, false);
            widgets.add(widget);
            widget.widget.setLayoutX(10);
            widget.widget.setLayoutY(currentY);
            currentY += 8 * (widget.widget.getHeight() + 10);
        }
        else if(buttonText.equals("Volume adjuster")){
            System.out.println("Vol adjuster button");
            AudioComponentWidgetBase widget = new VolumeAdjusterWidget(ap, buttonText, this, true);
            widgets.add(widget);
            widget.widget.setLayoutX(10);
            widget.widget.setLayoutY(currentY);
            currentY += 8 * (widget.widget.getHeight() + 10);
        }

    }

    public void playMixer() throws LineUnavailableException {
        System.out.println("play button clicked");
        boolean volumeExists = false;
        int volumeWidgetIndex=0;
        AudioComponent mixer = new Mixers();

        if(!widgets.isEmpty() && this.speakerOn) {
//            for (int i=0; i<widgets.size(); i++) {
//
//                if(widgets.get(i).isConnected && widgets.get(i).isVolumeAdjuster){
//                    volumeExists = true;
//                    volumeWidgetIndex = i;
//                }
//
//                if(widgets.get(i).isConnected && !widgets.get(i).isVolumeAdjuster){
//                    mixer.connectInput(widgets.get(i).getComponent());
//                }
//            }
//            //if volume controller exists, pass mixer through it, else get clip from mixer
//            AudioClip clip;
//            if(volumeExists){
//                AudioComponent volumeAdjuster = widgets.get(volumeWidgetIndex).getComponent();
//                volumeAdjuster.connectInput(mixer);
//                clip = volumeAdjuster.getClip();
//            } else {
//                clip = mixer.getClip();
//            }
            AudioComponent volumeAdjuster = null;

            // Check for connected widgets and separate the volume adjuster
            for (AudioComponentWidgetBase widget : widgets) {
                if (widget.isConnected) {
                    if (widget.isVolumeAdjuster) {
                        volumeAdjuster = widget.getComponent(); // Save the volume adjuster
                    } else {
                        mixer.connectInput(widget.getComponent()); // Connect other widgets
                    }
                }
            }

            // If a volume adjuster exists, connect the mixer to it
            if (volumeAdjuster != null) {
                volumeAdjuster.connectInput(mixer);
            }
            AudioClip clip = (volumeAdjuster != null) ? volumeAdjuster.getClip() : mixer.getClip();

            c.open(c.getFormat(), clip.getData(), 0, clip.getData().length);
            System.out.println("About to play...");

            c.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if(event.getType() == LineEvent.Type.STOP){
                        System.out.println("Playback finished.");
                        c.close();
                    }
                }
            });
            c.start();

        }
    }

    public void removeWidget(AudioComponentWidgetBase widget){
        System.out.println("close widget");
        widgets.remove(widget);
        Node widgetNode = widget.widget;
        ap.getChildren().remove(widgetNode);
        if(widgets.isEmpty()){
            this.toggleSpeaker();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}