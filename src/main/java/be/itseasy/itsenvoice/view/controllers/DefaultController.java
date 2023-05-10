package be.itseasy.itsenvoice.view.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class DefaultController {
    Stage mainStage;
    SimpleStringProperty searchString= new SimpleStringProperty();

    protected Stage getMainStage() {
        return mainStage;
    }

    protected void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    protected String getSearchString() {
        return searchString.get();
    }
    protected SimpleStringProperty searchStringProperty() {
        return searchString;
    }

    protected void setSearchString(String searchString) {
        this.searchString.set(searchString);
    }
}
