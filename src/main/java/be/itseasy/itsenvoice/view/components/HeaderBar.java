package be.itseasy.itsenvoice.view.components;


import atlantafx.base.controls.CustomTextField;
import atlantafx.base.controls.Spacer;
import be.itseasy.itsenvoice.view.controllers.WindowController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;


import java.util.function.Consumer;

import static atlantafx.base.theme.Styles.*;


public class HeaderBar extends HBox {

    private static final int HEADER_HEIGHT = 50;
    private static final Ikon ICON_CODE = Feather.CODE;
    private static final Ikon ICON_SAMPLE = Feather.LAYOUT;

    private final SimpleStringProperty title;
    private final StringProperty searchText;
    private final SingleSelectionModel<Tab> tabPaneSelectionModel;
    private Consumer<Node> quickConfigActionHandler;

    public HeaderBar(SimpleStringProperty title, StringProperty searchText, SingleSelectionModel<Tab> tabPaneSelectionModel) {
        super();
        this.title = title;
        this.searchText=searchText;
        this.tabPaneSelectionModel=tabPaneSelectionModel;
        createView();
    }

    private void createView() {
        ImageView logo = new ImageView(new Image(WindowController.class.getResourceAsStream("/be/itseasy/itsenvoice/img/logo.png")));
        logo.setFitWidth(32);
        logo.setFitHeight(32);

        var logoImageBorder = new Insets(1);
        var logoImageBox = new StackPane(logo);
        logoImageBox.getStyleClass().add("image");
        logoImageBox.setPadding(logoImageBorder);
        logoImageBox.setPrefWidth(logo.getFitWidth() + logoImageBorder.getRight() * 2);
        logoImageBox.setMaxWidth(logo.getFitHeight() + logoImageBorder.getTop() * 2);
        logoImageBox.setPrefHeight(logo.getFitWidth() + logoImageBorder.getTop() * 2);
        logoImageBox.setMaxHeight(logo.getFitHeight() + logoImageBorder.getRight() * 2);

        var logoLabel = new Label("IT's Envoice");
        logoLabel.getStyleClass().addAll(TITLE_3);

        var versionLabel = new Label(System.getProperty("app.version"));
        versionLabel.getStyleClass().addAll("version", TEXT_SMALL);

        var logoBox = new HBox(10, logoImageBox, logoLabel, versionLabel);
        logoBox.getStyleClass().add("logo");
        logoBox.setAlignment(Pos.CENTER_LEFT);
        logoBox.setMinWidth(220);
        logoBox.setPrefWidth(220);
        logoBox.setMaxWidth(220);

        var titleLabel = new Label();
        titleLabel.getStyleClass().addAll("page-title", TITLE_4);
        titleLabel.textProperty().bind(title);

        var searchField = new CustomTextField();
        searchField.setLeft(new FontIcon(Feather.SEARCH));
        searchField.setPromptText("Search");
        searchField.setMinWidth(250.0);
        searchText.bind(searchField.textProperty());

        var parameterLink = new FontIcon(Feather.SLIDERS);
        parameterLink.getStyleClass().addAll("github");
        parameterLink.setOnMouseClicked(e -> {
                tabPaneSelectionModel.select(6);
        });


        setId("header-bar");
        setMinHeight(HEADER_HEIGHT);
        setPrefHeight(HEADER_HEIGHT);
        setAlignment(Pos.CENTER_LEFT);
        getChildren().setAll(
                logoBox,
                titleLabel,
                new Spacer(),
                searchField,
                parameterLink

        );


    }

    public String getSearchText() {
        return searchText.get();
    }

    public StringProperty searchTextProperty() {
        return searchText;
    }
}

