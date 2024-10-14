package com.example.synthesizer;

import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import javafx.scene.control.Label;

public class SquareWaveWidget extends AudioComponentWidgetBase{

    public Slider widgetSlider;

    public SquareWaveWidget(Pane ap, String type, SynthesizeApplication app){
        super(ap, type, app);
        Label squareLabel = new Label("Square wave");
        titleBox.getChildren().add(squareLabel);
        this.widgetSlider = new Slider(20,20000,440);
        sliderBox.getChildren().add(this.widgetSlider);
        ap.getChildren().add(widget);
    }

    @Override
    public AudioComponent getComponent(){
        System.out.println("from square wave widget"+widgetSlider.getValue());
        return new SquareWave(widgetSlider.getValue());
    }

}
