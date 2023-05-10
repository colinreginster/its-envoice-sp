package be.itseasy.itsenvoice.view.controllers;

import be.itseasy.itsenvoice.ItsEnvoiceAppConfiguration;
import be.itseasy.itsenvoice.view.components.HeaderBar;
import com.jfoenix.controls.JFXTabPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
public class WindowController implements Initializable {
    private static Stage stage;
    private final ApplicationContext applicationContext;
    public AnchorPane content;
    private FXMLLoader fXMLLoader;

    @Resource()
    private ArrayList<ItsEnvoiceAppConfiguration.TabInfo> tabsList;

    @Resource()
    private SimpleStringProperty appTitle;

    @Resource()
    private SimpleStringProperty searchString;

    @Resource()
    private JFXTabPane tabPane;

    private HeaderBar headerBar;


    public WindowController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        WindowController.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headerBar = new HeaderBar(appTitle, searchString,tabPane.getSelectionModel());
        BorderPane body= new BorderPane();
        AnchorPane.setRightAnchor(body, 0.0);
        AnchorPane.setLeftAnchor(body, 0.0);
        AnchorPane.setTopAnchor(body, 0.0);
        AnchorPane.setBottomAnchor(body, 0.0);
        content.getChildren().add(body);
        body.setTop(headerBar);
        tabPane.setSide(Side.LEFT);
        tabPane.setTabMinWidth(90);
        tabPane.setTabMaxWidth(90);
        tabPane.setTabMinHeight(90);
        tabPane.setTabMaxHeight(90);
        tabPane.getSelectionModel().selectedItemProperty().addListener(new CustomChangeListener(applicationContext));
        for (ItsEnvoiceAppConfiguration.TabInfo tabInfo : tabsList) {
            tabPane.getTabs().add(configureTab(tabInfo, new FontIcon(Feather.findByDescription(tabInfo.getGraphicDescription()))));
        }
        body.setCenter(tabPane);

    }

    private CustomTab configureTab(ItsEnvoiceAppConfiguration.TabInfo tabInfo, FontIcon icon) {
        double tabWidth = 90.0;
        double imageWidth = 40.0;
        icon.setIconSize(40);
        CustomTab tab = new CustomTab(tabInfo);
        tab.setClosable(false);
        Label label = new Label(tabInfo.getTitle());
        label.setWrapText(true);
        label.setMaxWidth(tabWidth - 20);
        label.setPadding(new Insets(5, 0, 0, 0));
        label.setStyle("-fx-text-fill: -color-fg-default; -fx-font-size: 9pt; -fx-font-weight: normal;");
        label.setTextAlignment(TextAlignment.CENTER);

        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(icon);
        tabPane.setBottom(label);

        tab.setText("");
        tab.setContent(new AnchorPane());
        tab.setGraphic(tabPane);
        return tab;
    }

    private class CustomChangeListener implements ChangeListener<Tab> {

        private final ApplicationContext applicationContext;

        private CustomChangeListener(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }


        @Override
        public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
            if (newValue != null) {
                appTitle.setValue(((CustomTab) newValue).getTabInfo().getTitle());
                newValue.setStyle("-fx-background-color: -fx-focus-color;");
                if (oldValue != null)
                    oldValue.setStyle("-fx-background-color: -fx-accent;");

                if (((AnchorPane) newValue.getContent()).getChildren().isEmpty()) {

                    try {
                        fXMLLoader = new FXMLLoader();
                        fXMLLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
                        Parent root = fXMLLoader
                                .load(getClass().getResource(((CustomTab) newValue).getTabInfo().getFxmlLocation()).openStream());
                        DefaultController defaultController = fXMLLoader.getController();
                        defaultController.setMainStage(WindowController.getStage());
                        defaultController.searchStringProperty().bindBidirectional(searchString);
                        AnchorPane.setBottomAnchor(root, 0.0);
                        AnchorPane.setTopAnchor(root, 0.0);
                        AnchorPane.setLeftAnchor(root, 0.0);
                        AnchorPane.setRightAnchor(root, 0.0);
                        ((AnchorPane) newValue.getContent()).getChildren().add(root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }

    private class CustomTab extends Tab {
        private ItsEnvoiceAppConfiguration.TabInfo tabInfo;

        public CustomTab(ItsEnvoiceAppConfiguration.TabInfo tabInfo) {
            super(tabInfo.getTitle(), null);
            this.tabInfo = tabInfo;
        }

        public ItsEnvoiceAppConfiguration.TabInfo getTabInfo() {
            return tabInfo;
        }
    }
}
