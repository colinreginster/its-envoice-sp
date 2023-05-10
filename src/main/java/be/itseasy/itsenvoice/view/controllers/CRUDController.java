package be.itseasy.itsenvoice.view.controllers;


import atlantafx.base.theme.Styles;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import static atlantafx.base.theme.Styles.BUTTON_ICON;
import static javafx.animation.Interpolator.EASE_BOTH;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Locale;
import java.util.ResourceBundle;

public class CRUDController extends DefaultController {


    protected MenuButton actions_menu = new MenuButton("Actions");
    protected FontIcon addSign = new FontIcon(Feather.PLUS);
    protected FontIcon editSign = new FontIcon(Feather.EDIT);
    protected FontIcon deleteSign = new FontIcon(Feather.DELETE);
    protected FontIcon floppySign = new FontIcon(Feather.SAVE);
    protected FontIcon cancelSign = new FontIcon(Feather.MINUS_CIRCLE);
    protected FontIcon checkSign = new FontIcon(Feather.CHECK_CIRCLE);
    protected MenuItem addAction = new MenuItem("Ajouter");
    protected MenuItem editAction = new MenuItem("Modifier");
    protected MenuItem deleteAction = new MenuItem("Supprimer");
    protected VBox mainForm = new VBox();
    protected ScrollPane formScrollPane = new ScrollPane();

    protected HBox buttonContainer = new HBox();
    @FXML
    protected AnchorPane mainPane;
    protected JFXDrawersStack root;
    protected JFXDrawer editDrawer;
    protected JFXDrawer saveDrawer;
    protected StackPane stackPane =new StackPane();
    protected VBox editBox;

    Locale locale = Locale.getDefault();
    ResourceBundle bundle = ResourceBundle.getBundle("itsenvoice", locale);

    public void initialize(EventHandler<ActionEvent> saveButtonHandler, EventHandler<ActionEvent> cancelButtonHandler,EventHandler<ActionEvent> addButtonHandler, ChangeListener<String> searchStringListener,Boolean needsEditDrawer){
        root = new JFXDrawersStack();

        saveDrawer = createSaveDrawer(saveButtonHandler, cancelButtonHandler);

        root.addDrawer(saveDrawer);
        mainPane.getChildren().add(root);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        JFXDepthManager.setDepth(stackPane, 1);
        root.setContent(stackPane);
        stackPane.getChildren().add(formScrollPane);
        StackPane.setAlignment(formScrollPane, Pos.TOP_LEFT);
        formScrollPane.setPadding(new Insets(5));
        formScrollPane.setFitToHeight(true);
        formScrollPane.setFitToWidth(true);
        formScrollPane.setContent(mainForm);
        mainForm.autosize();

        mainForm.setSpacing(5);
        if(needsEditDrawer) {
            editDrawer = createEditDrawer();
            root.addDrawer(editDrawer);

            JFXButton button = new JFXButton("",addSign);
            button.setMinSize(50 * 1.25, 50 * 1.25);
            button.setMaxSize(50 * 1.25, 50 * 1.25);
            button.setShape(new Circle(50));
            button.getStyleClass().addAll(Styles.ACCENT,Styles.BUTTON_CIRCLE);
            StackPane.setMargin(button, new Insets(0, 25, 25, 0));
            StackPane.setAlignment(button, Pos.BOTTOM_RIGHT);
            stackPane.getChildren().addAll(button);
            button.setOnAction(addButtonHandler);

        }

        if(searchStringListener != null) searchStringProperty().addListener(searchStringListener);
    }
    protected TitledPane initializeFormPart(String title,EventHandler<ActionEvent> exitButtonHandler) {

        TitledPane formPart = new TitledPane(bundle.getString(title), new VBox());
        if(exitButtonHandler!=null){
            var basicButton= new Button("",new FontIcon(Feather.X));
            basicButton.getStyleClass().addAll(BUTTON_ICON);
            basicButton.setOnAction(exitButtonHandler);
            formPart.setGraphic(basicButton);
            formPart.setContentDisplay(ContentDisplay.RIGHT);

            final double graphicMarginRight = 10; //change it, if needed

            basicButton.translateXProperty().bind(Bindings.createDoubleBinding(
                    () -> formPart.getWidth() - basicButton.getLayoutX() - basicButton.getWidth() - graphicMarginRight,
                    formPart.widthProperty())
            );
        }
        Styles.toggleStyleClass(formPart, Styles.DENSE);
        formPart.setAnimated(true);
        formPart.setCollapsible(true);
        formPart.setExpanded(true);
        formPart.setPadding(new Insets(0, 0, 10, 0));
        ((VBox) formPart.getContent()).setPadding(new Insets(10, 0, 5, 0));
        ((VBox) formPart.getContent()).setSpacing(25.0);
        return formPart;
    }
    private JFXDrawer createEditDrawer() {
        JFXDrawer drawer = new JFXDrawer();
        editBox = new VBox();


        editBox.setAlignment(Pos.TOP_CENTER);
        editBox.autosize();

        drawer.setDirection(JFXDrawer.DrawerDirection.RIGHT);
        drawer.setId("RIGHT");
        drawer.setAlignment(Pos.TOP_CENTER);

        editBox.setStyle("-fx-background-color: -color-bg-overlay;");
        drawer.setStyle("-fx-background-color: -color-bg-overlay;");

        drawer.setDefaultDrawerSize(350);
        drawer.setSidePane(editBox);
        drawer.setOverLayVisible(false);
        drawer.setResizableOnDrag(true);
        return drawer;
    }
    protected void toggleEditDrawer() {
        root.toggle(editDrawer);
    }
    protected void openEditDrawer() {
        root.toggle(editDrawer,true);
    }
    protected void closeEditDrawer() {
        root.toggle(editDrawer,false);
    }
    private JFXDrawer createSaveDrawer(EventHandler<ActionEvent> saveButtonHandler, EventHandler<ActionEvent> cancelButtonHandler) {
        JFXDrawer drawer = new JFXDrawer();
        VBox editBox = new VBox();
        drawer.setDirection(JFXDrawer.DrawerDirection.TOP);
        drawer.setId("TOP");
        drawer.setStyle("-fx-background-color: -color-bg-overlay;");
        drawer.setAlignment(Pos.TOP_CENTER);
        drawer.setOverLayVisible(true);

        editBox.setStyle("-fx-background-color: -color-bg-overlay;");
        buttonContainer.setPadding(new Insets(5, 10, 5, 5));
        buttonContainer.setSpacing(5);
        buttonContainer.setAlignment(Pos.TOP_RIGHT);
        buttonContainer.setStyle("-fx-background-color: -color-bg-overlay;");


        Button saveButton = new Button(bundle.getString("crud.actions.save"), floppySign);
        saveButton.setOnAction(saveButtonHandler);
        Button cancelButton = new Button(bundle.getString("crud.actions.cancel"), cancelSign);
        cancelButton.setOnAction(cancelButtonHandler);
        buttonContainer.getChildren().add(saveButton);
        buttonContainer.getChildren().add(cancelButton);
        editBox.getChildren().add(buttonContainer);
        editBox.autosize();
        drawer.setDefaultDrawerSize(40);
        drawer.setSidePane(editBox);
        drawer.setOverLayVisible(false);
        drawer.setResizableOnDrag(false);
        return drawer;
    }
    protected void openSaveDrawer() {
        root.toggle(saveDrawer,true);
    }
    protected void closeSaveDrawer() {
        root.toggle(saveDrawer,false);
    }
}
