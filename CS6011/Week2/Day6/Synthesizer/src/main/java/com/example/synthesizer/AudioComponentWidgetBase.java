package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import javax.sound.sampled.LineUnavailableException;

public class AudioComponentWidgetBase {

    protected HBox titleBox;
    protected HBox widget;
    protected HBox sliderBox;
    protected AudioComponent component;
    protected SynthesizeApplication app;
    private double initialX;
    private double initialY;
    private double mouseX;
    private double mouseY;
    protected boolean isConnected = false;
    protected boolean isVolumeAdjuster = false;
    protected Line connectedLine;
    protected Button connectButton;
    protected Circle outputCircle;
    private boolean isDraggingOutputCircle = false;

    public AudioComponentWidgetBase(Pane ap, String type, SynthesizeApplication app, boolean hasInput){
        this.app = app;
        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #abacf7");
        hBox.setPadding(new Insets(5));

        VBox vBox1 = new VBox();
        vBox1.setStyle("-fx-border-color: black");
        vBox1.setPadding(new Insets(5));
        vBox1.setSpacing(5);
        HBox hBox1_1 = new HBox();
        HBox hBox1_2 = new HBox();

        this.titleBox = hBox1_1;
        this.sliderBox = hBox1_2;
        //add input circle if needed,
        if(hasInput){
            Button inputButton = new Button();
            inputButton.setShape(new Circle(10));
            inputButton.setStyle("-fx-background-color: GREEN");
            this.sliderBox.getChildren().add(inputButton);
        }

        vBox1.getChildren().add(hBox1_1);
        vBox1.getChildren().add(hBox1_2);

        VBox vBox2 = new VBox();
        vBox2.setPadding(new Insets(5));
        vBox2.setSpacing(5);
        HBox hBox2_1 = new HBox();
        Button closeButton = new Button("X");
//        closeButton.setOnAction(e->app.removeWidget(this));
        closeButton.setOnAction(e->closeWidget());
        hBox2_1.getChildren().add(closeButton);
        HBox hBox2_2 = new HBox();
        //use circle as input and output button
        //on mouse released, get seceneX and Y
        //for each widget input circle, in .contains method, check if (widget circle.sceneToLocal(getsceneX, getsceneY))
        //then get rid of temp line
        //connection line class - stoires input widget, output widget, line itself, point2D putput and input coordinates
        //updateline() sets outputcoordinates = out.put.getcircle.localtoscene(output circle.getcenterx, getgenter y)
        //'' sets input coordinates the same way
        //line setstart x -outputcoordinates.getX(), setstartY,
        //

        //setup circle for output
        this.outputCircle = new Circle(10);
        hBox2_2.getChildren().add(this.outputCircle);
        //when mouse clicks on output circle, get mouse x,y and print if circle contains mouse x,y
        // Initialize line
        this.connectedLine = new Line();
        this.connectedLine.setStroke(Color.RED); // Set the line color
        ap.getChildren().add(this.connectedLine); // Add the line to the Pane

        this.outputCircle.setOnMousePressed(e -> {
            this.isDraggingOutputCircle = true;
            // Set the start point of the line to the center of the output circle
            double circleCenterX = this.outputCircle.localToScene(this.outputCircle.getCenterX(), this.outputCircle.getCenterY()).getX();
            double circleCenterY = this.outputCircle.localToScene(this.outputCircle.getCenterX(), this.outputCircle.getCenterY()).getY();

            System.out.println(circleCenterX);
            System.out.println(e.getSceneX());
//            connectedLine.setStartX(circleCenterX);
//            connectedLine.setStartY(circleCenterY);
//            connectedLine.setEndX(circleCenterX); // Initial end point is the same as the start point
//            connectedLine.setEndY(circleCenterY); // Initial end point is the same as the start point
        });

        this.outputCircle.setOnMouseDragged(e -> {
            // Update the end point of the line to follow the mouse
//            connectedLine.setEndX(e.getSceneX());
//            connectedLine.setEndY(e.getSceneY());
        });

        this.outputCircle.setOnMouseReleased(e-> {
            for(AudioComponentWidgetBase otherWidget: app.widgets){
                double circleCenterX = otherWidget.outputCircle.localToScene(otherWidget.outputCircle.getCenterX(), otherWidget.outputCircle.getCenterY()).getX();
                System.out.println(circleCenterX);
                System.out.println(e.getSceneX());

            }
        });
//        connectButton = new Button();
//        connectButton.setShape(new Circle(10));
//        connectButton.setStyle("-fx-background-color: BLACK");
//        connectButton.setOnAction(e -> {
//            this.isConnected = !this.isConnected;
//            if(this.isConnected){
//                connectButton.setStyle("-fx-background-color: RED");
//            } else {
//                connectButton.setStyle("-fx-background-color: BLACK");
//            }
//        });
//        connectButton.setOnMousePressed(e-> {
//
//            if(this.connectedLine != null){
//                ap.getChildren().remove(this.connectedLine);
//            }
//            this.connectedLine = new Line(
//                    widget.getLayoutX() + widget.getWidth()/1.1,
//                    widget.getLayoutY() + widget.getHeight()/1.1,
//                    e.getSceneX(), e.getSceneY()
//            );
//            ap.getChildren().add(this.connectedLine);
//        });
//
//        connectButton.setOnMouseDragged(e->{
//            if(this.connectedLine != null){
//                this.connectedLine.setEndX(e.getSceneX());
//                this.connectedLine.setEndY(e.getSceneY());
//            }
//        });
//
//        connectButton.setOnMouseReleased(e-> {
//
//            //check for connection with speaker
//            boolean connectedToSpeaker = e.getSceneX() >= (ap.getWidth() -60) &&
//                    e.getSceneY() >= (ap.getHeight() - 60);
//            if(connectedToSpeaker){
//                this.isConnected = true;
//                app.speakerOn = true;
//            } else {
//                for(AudioComponentWidgetBase otherWidget: app.widgets){
//                    System.out.println(e.getSceneX());
//                    System.out.println(e.getSceneY());
//                    System.out.println(otherWidget.getConnectButtonCenterX());
//                    System.out.println(otherWidget.getConnectButtonCenterY());
//                    if(otherWidget != this){
//                        double distance = calculateDistance(
//                                this.connectedLine.getEndX(),
//                                this.connectedLine.getEndY(),
//                                otherWidget.getConnectButtonCenterX(),
//                                otherWidget.getConnectButtonCenterY()
//                        );
//                        if (distance < 20) {
//                            System.out.println("connected");
//                            this.isConnected = true;
//                            otherWidget.isConnected = true;
//                            Line line = new Line(
//                                    getConnectButtonCenterX(),
//                                    getConnectButtonCenterY(),
//                                    otherWidget.getConnectButtonCenterX(),
//                                    otherWidget.getConnectButtonCenterY()
//                            );
//                            line.setStroke(Color.RED); // Set line color
//                            ap.getChildren().add(line); // Keep the line visible
//                            break;
//
//                        }
//
//                    }
//                }
//            }
//            // Clean up connection line
//            if (this.connectedLine != null) {
//                ap.getChildren().remove(this.connectedLine);
//                this.connectedLine = null; // Reset connection line
//            }
//        });
//
//
//        hBox2_2.getChildren().add(connectButton);
        vBox2.getChildren().add(hBox2_1);
        vBox2.getChildren().add(hBox2_2);

        hBox.getChildren().add(vBox1);
        hBox.getChildren().add(vBox2);

//        AudioComponentWidgetBase widget = new SineWaveWidget(ap, "Sine wave", hBox);
        this.widget = hBox;
        setupDragEvents(hBox);
//        ap.getChildren().add(widget.hBox);
    }

    public void setupDragEvents(HBox widget){
        widget.setOnMousePressed(e->{
           initialX = widget.getLayoutX();
           initialY = widget.getLayoutY();
           mouseX = e.getSceneX();
           mouseY = e.getSceneY();
        });

        widget.setOnMouseDragged(e->{
            if(isDraggingOutputCircle){
                return;
            } else {
            double offsetX = e.getSceneX() - mouseX;
            double offsetY = e.getSceneY() - mouseY;
            widget.setLayoutX(initialX + offsetX);
            widget.setLayoutY(initialY + offsetY);
            }
        });
    }

    public AudioComponent getComponent() {
        return component;
    }

    private double getConnectButtonCenterX(){
        return widget.getLayoutX() + connectButton.getLayoutX() + connectButton.getWidth() /2;
    }

    private double getConnectButtonCenterY(){
        return widget.getLayoutY() + connectButton.getLayoutY() + connectButton.getHeight() /2;
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void closeWidget(){
        app.removeWidget(this);
    }


}