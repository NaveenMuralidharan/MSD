package com.example.synthesizer;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;


public class LinearRampWidget extends AudioComponentWidgetBase{

    public TextField start;
    public TextField stop;

    public LinearRampWidget(Pane ap, String type, SynthesizeApplication app){
        super(ap, type, app);
        Label whiteNoiseLabel = new Label("Linear Ramp");
        titleBox.getChildren().add(whiteNoiseLabel);
        ap.getChildren().add(widget);
        this.start = new TextField();
        this.start.setPromptText("start");
        this.stop = new TextField();
        this.stop.setPromptText("stop");
        sliderBox.getChildren().add(start);
        sliderBox.getChildren().add(stop);
    }

    @Override
    public AudioComponent getComponent() {
        int startInt = Integer.parseInt(this.start.getText());
        int stopInt = Integer.parseInt(this.stop.getText());
//        return new LinearRamp(startInt, stopInt);
        return new LinearRamp(20,200);
    }
}
