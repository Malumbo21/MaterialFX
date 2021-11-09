package io.github.palexdev.materialfx.controls;

import javafx.scene.control.Label;
import javafx.scene.control.Labeled;

public class LabeledControlWrapper extends Label {

    public LabeledControlWrapper(Labeled labeled) {
        super();

        // Init
        setText(labeled.getText());
        setFont(labeled.getFont());
        setTextFill(labeled.getTextFill());
        setWrapText(labeled.isWrapText());
        setTextAlignment(labeled.getTextAlignment());
        setTextOverrun(labeled.getTextOverrun());
        setEllipsisString(labeled.getEllipsisString());
        setUnderline(labeled.isUnderline());
        setLineSpacing(labeled.getLineSpacing());
        setGraphicTextGap(labeled.getGraphicTextGap());
        setContentDisplay(labeled.getContentDisplay());
        setGraphic(labeled.getGraphic());

        // Bindings
        textProperty().bind(labeled.textProperty());
        fontProperty().bind(labeled.fontProperty());
        textFillProperty().bind(labeled.textFillProperty());
        wrapTextProperty().bind(labeled.wrapTextProperty());
        textAlignmentProperty().bind(labeled.textAlignmentProperty());
        textOverrunProperty().bind(labeled.textOverrunProperty());
        ellipsisStringProperty().bind(labeled.ellipsisStringProperty());
        underlineProperty().bind(labeled.underlineProperty());
        lineSpacingProperty().bind(labeled.lineSpacingProperty());
        graphicTextGapProperty().bind(labeled.graphicTextGapProperty());
        contentDisplayProperty().bind(labeled.contentDisplayProperty());
        graphicProperty().bind(labeled.graphicProperty());
    }
}
