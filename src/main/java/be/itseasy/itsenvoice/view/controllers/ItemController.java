package be.itseasy.itsenvoice.view.controllers;

import be.itseasy.itsenvoice.model.entities.Item;
import be.itseasy.itsenvoice.viewmodel.services.ItemService;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import io.micrometer.common.util.StringUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

@Component
public class ItemController extends CRUDController implements Initializable {
    HashMap<Property, Property> bindings = new HashMap<>();

    Locale locale = Locale.getDefault();
    ResourceBundle bundle = ResourceBundle.getBundle("itsenvoice", locale);
    JFXTextField nameJtextfield = new JFXTextField();
    JFXTextField descriptionTextfield = new JFXTextField();
    JFXTextField priceTextfield = new JFXTextField();
    JFXTextField vatRateTextfield = new JFXTextField();

    @Autowired
    private ItemService itemService;
    private TableView<Item> itemTableView;
    private ObservableList<Item> itemObservableList;
    private ObjectProperty<Item> item;

    private ChangeListener changeListener = (observable, oldValue, newValue) -> {
        openSaveDrawer();
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        item = new SimpleObjectProperty<>();
        mainForm.autosize();
        super.initialize(event -> {
            itemService.saveItem(itemTableView.getSelectionModel().getSelectedItem());
            closeSaveDrawer();
            itemObservableList.clear();
            itemObservableList.addAll(itemService.findItems(searchStringProperty().get()));
            closeEditDrawer();
        }, event -> {
            closeSaveDrawer();
            closeEditDrawer();
            if(itemTableView.getSelectionModel().getSelectedItem().getIteid()== null){

            }
            itemTableView.refresh();
        }, event -> {
            Item item = new Item();
            item.addChangeListener(changeListener);
            createBindings(item);
            itemObservableList.add(item);
            itemTableView.getSelectionModel().select(item);
            itemTableView.refresh();
            openEditDrawer();
        }, (observable, oldValue, newValue) -> {
            itemObservableList.clear();
            itemObservableList.addAll(itemService.findItems(newValue));
        }, true);


        itemTableView = new TableView<>();
        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<Item, BigDecimal> priceColumn = new TableColumn<>("Price");
        TableColumn<Item, BigDecimal> vatRateColumn = new TableColumn<>("VAT Rate");

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        vatRateColumn.setCellValueFactory(cellData -> cellData.getValue().vatRateProperty());
        nameColumn.prefWidthProperty().bind(itemTableView.widthProperty().multiply(0.25));
        descriptionColumn.prefWidthProperty().bind(itemTableView.widthProperty().multiply(0.55));
        priceColumn.prefWidthProperty().bind(itemTableView.widthProperty().multiply(0.05));
        vatRateColumn.prefWidthProperty().bind(itemTableView.widthProperty().multiply(0.10));
        itemTableView.getColumns().addAll(nameColumn, descriptionColumn, priceColumn, vatRateColumn);

        // Load items and add them to the TableView
        itemObservableList = FXCollections.observableArrayList();
        itemObservableList.addAll(itemService.listAllItem());
        itemTableView.setItems(itemObservableList);
        VBox.setVgrow(itemTableView, Priority.ALWAYS);

        // Add a listener for the selection change
        itemTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                if (oldSelection != null) unbind(oldSelection);
                createBindings(newSelection);
                newSelection.addChangeListener(changeListener);
                openEditDrawer();
            } else {
                unbind(oldSelection);
                clearFields();
                closeEditDrawer();
            }
        });

        // Add the TableView to the layout
        mainForm.getChildren().add(itemTableView);


        TitledPane formPart1 = initializeFormPart("crud.item.itemDetails", event -> {
            item.set(itemService.getItem(1));
            closeSaveDrawer();
            closeEditDrawer();
        });
        editBox.getChildren().add(formPart1);
        VBox formPart1VBox = (VBox) formPart1.getContent();
        formPart1VBox.setSpacing(25);

        nameJtextfield.setPromptText(bundle.getString("crud.item.name"));
        nameJtextfield.setLabelFloat(true);
        nameJtextfield.setMinWidth(350.0);
        descriptionTextfield.setPromptText(bundle.getString("crud.item.description"));
        descriptionTextfield.setLabelFloat(true);
        descriptionTextfield.setMinWidth(350.0);
        priceTextfield.setPromptText(bundle.getString("crud.item.price"));
        priceTextfield.setLabelFloat(true);
        priceTextfield.setMinWidth(350.0);
        vatRateTextfield.setPromptText(bundle.getString("crud.item.vatRate"));
        vatRateTextfield.setLabelFloat(true);
        vatRateTextfield.setMinWidth(350.0);

        formPart1VBox.getChildren().addAll(nameJtextfield, descriptionTextfield, priceTextfield, vatRateTextfield);
    }


    private void createBindings(Item oldval) {
        for(Property property : oldval.getProperties()) {
            if (bindings.containsKey(property)) {
                property.unbindBidirectional(bindings.get(property));
            }
            nameJtextfield.textProperty().bindBidirectional(oldval.nameProperty());

            bindings.put(property, property);
        }
        nameJtextfield.textProperty().bindBidirectional(oldval.nameProperty());
        descriptionTextfield.textProperty().bindBidirectional(oldval.descriptionProperty());
        priceTextfield.textProperty().bindBidirectional(oldval.priceProperty(), new BigDecimalStringConverter());
        vatRateTextfield.textProperty().bindBidirectional(oldval.vatRateProperty(), new BigDecimalStringConverter());
    }

    private void unbind(Item oldval) {
        nameJtextfield.textProperty().unbindBidirectional(oldval.nameProperty());
        descriptionTextfield.textProperty().unbindBidirectional(oldval.descriptionProperty());
        priceTextfield.textProperty().unbindBidirectional(oldval.priceProperty());
        vatRateTextfield.textProperty().unbindBidirectional(oldval.vatRateProperty());

    }

    private void clearFields() {
        nameJtextfield.clear();
        descriptionTextfield.clear();
        priceTextfield.clear();
        vatRateTextfield.clear();
    }


    private class BigDecimalStringConverter extends StringConverter<BigDecimal> {
        @Override
        public String toString(BigDecimal value) {
            return value != null ? value.toString() : null;
        }

        @Override
        public BigDecimal fromString(String string) {
            try {
                System.out.println(string);
                return new BigDecimal(string);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return BigDecimal.ZERO;
            }
        }
    }
}
