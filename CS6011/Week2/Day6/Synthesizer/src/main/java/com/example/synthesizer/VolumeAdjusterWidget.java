package com.example.synthesizer;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class VolumeAdjusterWidget extends AudioComponentWidgetBase{
    public Slider widgetSlider;

    public VolumeAdjusterWidget(Pane ap, String type, SynthesizeApplication app){
        super(ap,type,app);
        Label volumeLabel = new Label(type);
        titleBox.getChildren().add(volumeLabel);
        this.widgetSlider = new Slider(0,1,0.5);
        sliderBox.getChildren().add(widgetSlider);
        ap.getChildren().add(widget);
        isVolumeAdjuster = true;
    }

    @Override
    public AudioComponent getComponent() {
        return new VolumeAdjuster(widgetSlider.getValue());
    }


}
