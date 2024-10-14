package com.example.synthesizer;

import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class WhiteNoiseWidget extends AudioComponentWidgetBase{

    public WhiteNoiseWidget(Pane ap, String type, SynthesizeApplication app){
        super(ap, type, app);
        Label whiteNoiseLabel = new Label("White Noise");
        titleBox.getChildren().add(whiteNoiseLabel);
        ap.getChildren().add(widget);
    }

    @Override
    public AudioComponent getComponent(){
        return new WhiteNoise();
    }

}
