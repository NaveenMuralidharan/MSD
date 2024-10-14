package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SineWaveWidget extends AudioComponentWidgetBase{

    public Slider widgetSlider;

    public SineWaveWidget(Pane ap, String type, SynthesizeApplication app) {
        super(ap, type, app);
        Label sineLabel = new Label(type);
        titleBox.getChildren().add(sineLabel);
        this.widgetSlider = new Slider(20,20000,440);
        sliderBox.getChildren().add(widgetSlider);
        ap.getChildren().add(widget);
    }

    @Override
    public AudioComponent getComponent() {
        System.out.println("from sinewavewidget"+widgetSlider.getValue());
        return new SineWave(widgetSlider.getValue());
    }
}
