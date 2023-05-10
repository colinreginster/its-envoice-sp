package be.itseasy.itsenvoice.view.controllers.utils;

import be.itseasy.itsenvoice.view.controllers.CRUDController;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

public class ImagePreviewField extends VBox {
    Button editButton = new Button("",new FontIcon(Feather.EDIT));
    StackPane imagePane = new StackPane();
    ImageView imageView = new ImageView();


    private ObjectProperty<Image> image=imageView.imageProperty();


    public ImagePreviewField(String labelText) {
        super();
        Label label = new Label(labelText);
        imagePane.getChildren().add(imageView);
        editButton.setStyle("-fx-opacity: 0.5;");
        imagePane.getChildren().add(editButton);
        this.getChildren().addAll(label, imagePane);
    }

    public void setImageSize(double imageSize) {
        super.setMaxWidth(imageSize);
        imageView.setFitWidth(imageSize);
        imageView.setFitHeight(imageSize);
        imagePane.setMaxWidth(imageSize);
        imagePane.setMaxHeight(imageSize);
        editButton.setShape(new Circle(imageSize / 2));
        editButton.setMinSize((imageSize / 2) * 1.25, (imageSize / 2) * 1.25);
        editButton.setMaxSize((imageSize / 2) * 1.25, (imageSize / 2) * 1.25);
    }

    public void setOnAction(EventHandler<ActionEvent> value){
        this.editButton.setOnAction(value);
    }


    public Image getImage() {
        return image.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }

    public void setImage(Image image) {
        this.image.set(image);
    }


}
