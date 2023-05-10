package be.itseasy.itsenvoice.view.controllers;

import be.itseasy.itsenvoice.model.entities.City;
import be.itseasy.itsenvoice.model.entities.Company;
import be.itseasy.itsenvoice.view.controllers.utils.ImagePreviewField;
import be.itseasy.itsenvoice.view.utils.CitySearchTypeEnum;
import be.itseasy.itsenvoice.viewmodel.services.CityService;
import be.itseasy.itsenvoice.viewmodel.services.CompanyService;
import be.itseasy.itsenvoice.viewmodel.services.CountryService;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.client.request.NominatimSearchRequest;
import fr.dudie.nominatim.model.Address;
import fr.dudie.nominatim.model.Element;
import jakarta.annotation.Resource;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Component
public class CompanyController extends CRUDController implements Initializable {
    Locale locale = Locale.getDefault();
    ResourceBundle bundle = ResourceBundle.getBundle("itsenvoice", locale);
    JFXCheckBox hasVATCheckBox = new JFXCheckBox(bundle.getString("crud.company.companyHasVat"));
    ImagePreviewField imagePreviewField = new ImagePreviewField(bundle.getString("crud.company.companyLogo"));
    JFXTextField nameJtextfield = new JFXTextField();
    JFXTextField sloganTextfield = new JFXTextField();
    JFXTextField phoneTextfield = new JFXTextField();
    JFXTextField mobileTextfield = new JFXTextField();
    JFXTextField emailTextfield = new JFXTextField();
    JFXTextField streetTextfield = new JFXTextField();
    JFXTextField postBoxTextfield = new JFXTextField();
    JFXTextField numberTextfield = new JFXTextField();
    JFXTextField cityTextfield = new JFXTextField();
    JFXTextField companyVATTextfield = new JFXTextField();
    JFXTextField zipCodeTextfield = new JFXTextField();
    JFXTextField addressLookupTextfield = new JFXTextField();
    JFXButton addressLookupValidateAddressButton = new JFXButton();
    HBox buttonContainer = new HBox();
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    @Resource
    private JsonNominatimClient nominatimClient;
    private ObjectProperty<Company> company;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        company = new SimpleObjectProperty<>(companyService.getCompany(1));
        createBindings(null);
        company.addListener((obs, oldval, newval) -> createBindings(oldval));
        initialize(event -> {
            companyService.saveCompany(company.get());
            company.set(companyService.getCompany(1));
            closeSaveDrawer();
        },event -> {
            company.set(companyService.getCompany(1));
            closeSaveDrawer();
        },null, null, false);




        TitledPane formPart1 = initializeFormPart("crud.company.branding",null);
        mainForm.getChildren().add(formPart1);
        VBox formPart1VBox = (VBox) formPart1.getContent();
        formPart1VBox.setSpacing(25);
        imagePreviewField.setImageSize(150.0);
        imagePreviewField.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File imageFile = fileChooser.showOpenDialog(mainPane.getScene().getWindow());
            if (imageFile != null) {
                FileInputStream inputstream;
                try {
                    inputstream = new FileInputStream(imageFile);
                    company.get().setLogo(inputstream.readAllBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        nameJtextfield.setPromptText(bundle.getString("crud.company.companyName"));
        nameJtextfield.setLabelFloat(true);
        nameJtextfield.setMinWidth(350.0);
        sloganTextfield.setPromptText(bundle.getString("crud.company.companySlogan"));
        sloganTextfield.setLabelFloat(true);
        sloganTextfield.setMinWidth(350.0);
        formPart1VBox.getChildren().addAll(imagePreviewField, nameJtextfield, sloganTextfield);
        TitledPane formPart2 = initializeFormPart("crud.company.contactDetails",null);
        mainForm.getChildren().add(formPart2);
        VBox formPart2VBox = (VBox) formPart2.getContent();
        phoneTextfield.setPromptText(bundle.getString("crud.company.phone"));
        phoneTextfield.setLabelFloat(true);
        phoneTextfield.setMinWidth(350.0);
        mobileTextfield.setPromptText(bundle.getString("crud.company.mobile"));
        mobileTextfield.setLabelFloat(true);
        mobileTextfield.setMinWidth(350.0);
        emailTextfield.setPromptText(bundle.getString("crud.company.email"));
        emailTextfield.setLabelFloat(true);
        emailTextfield.setMinWidth(350.0);
        formPart2VBox.getChildren().addAll(phoneTextfield, mobileTextfield, emailTextfield);


        TitledPane formPart3 = initializeFormPart("crud.address.entity",null);
        mainForm.getChildren().add(formPart3);
        VBox formPart3VBox = (VBox) formPart3.getContent();
        HBox addressLookupBox = new HBox();
        addressLookupTextfield.setPromptText("Search for Address");
        addressLookupTextfield.setLabelFloat(true);
        addressLookupTextfield.setMinWidth(350.0);
        JFXAutoCompletePopup<fr.dudie.nominatim.model.Address> addressAutoCompletePopup = new JFXAutoCompletePopup<>();
        Callback<ListView<fr.dudie.nominatim.model.Address>, ListCell<fr.dudie.nominatim.model.Address>> listViewListCellCallback = param -> new ListCell<fr.dudie.nominatim.model.Address>() {
            @Override
            protected void updateItem(fr.dudie.nominatim.model.Address item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getDisplayName() == null) {
                    setText(null);
                } else {
                    Element[] elements = item.getAddressElements();
                    Element streetNumber = Arrays.stream(elements).filter(element -> element.getKey().contains("house_number")).findAny().orElse(null);
                    Element street = Arrays.stream(elements).filter(element -> element.getKey().contains("road")).findAny().orElse(null);
                    Element zipCode = Arrays.stream(elements).filter(element -> element.getKey().contains("postcode")).findAny().orElse(null);
                    Element city = Arrays.stream(elements).filter(element -> element.getKey().contains("village")).findAny().orElse(null);
                    Element country_code = Arrays.stream(elements).filter(element -> element.getKey().contains("country_code")).findAny().orElse(null);
                    setText(String.format("%s %s,  %s %s", street != null ? street.getValue() : "", streetNumber != null ? streetNumber.getValue() : "", zipCode != null ? zipCode.getValue() : "", city != null ? city.getValue() : ""));
                }
            }
        };
        addressAutoCompletePopup.setSuggestionsCellFactory(listViewListCellCallback);

        // SelectionHandler sets the value of the comboBox
        addressAutoCompletePopup.setSelectionHandler(event -> {

            fr.dudie.nominatim.model.Address address = event.getObject();
            if (!"NOTFOUND".equals(address.getDisplayName())) {
                Element[] elements = address.getAddressElements();
                Element streetNumber = Arrays.stream(elements).filter(element -> element.getKey().contains("house_number")).findAny().orElse(null);
                Element street = Arrays.stream(elements).filter(element -> element.getKey().contains("road")).findAny().orElse(null);
                Element zipCode = Arrays.stream(elements).filter(element -> element.getKey().contains("postcode")).findAny().orElse(null);
                Element city = Arrays.stream(elements).filter(element -> element.getKey().contains("village")).findAny().orElse(null);
                Element country_code = Arrays.stream(elements).filter(element -> element.getKey().contains("country_code")).findAny().orElse(null);
                addressLookupTextfield.setText(String.format("%s %s,  %s %s", street != null ? street.getValue() : "", streetNumber != null ? streetNumber.getValue() : "", zipCode != null ? zipCode.getValue() : "", city != null ? city.getValue() : ""));

                numberTextfield.textProperty().set(streetNumber != null ? streetNumber.getValue() : "");
                streetTextfield.textProperty().set(street != null ? street.getValue() : "");
                zipCodeTextfield.textProperty().set(zipCode != null ? zipCode.getValue() : "");
                cityTextfield.textProperty().set(city != null ? city.getValue() : "");
            }
        });
        addressLookupValidateAddressButton.setGraphic(checkSign);
        addressLookupValidateAddressButton.setOnAction(event -> {
            try {

                NominatimSearchRequest searchRequest = new NominatimSearchRequest();
                searchRequest.setAddress(true);
                searchRequest.setQuery(addressLookupTextfield.getText());
                List<fr.dudie.nominatim.model.Address> results = nominatimClient.search(searchRequest);
                addressAutoCompletePopup.getSuggestions().clear();

                for (fr.dudie.nominatim.model.Address prediction : results) {
                    addressAutoCompletePopup.getSuggestions().add(prediction);
                }
                // Hide the autocomplete popup if the filtered suggestions is empty or when the box's
                // original popup is open
                if (addressAutoCompletePopup.getFilteredSuggestions().isEmpty()) {
                    Address notFound = new Address();
                    notFound.setDisplayName("NOTFOUND");
                    Element notFoundElement = new Element();
                    notFoundElement.setKey("road");
                    notFoundElement.setValue(bundle.getString("crud.address.notFound"));
                    notFound.setAddressElements(new Element[]{notFoundElement});
                    addressAutoCompletePopup.getSuggestions().add(notFound);
                    addressAutoCompletePopup.show(addressLookupTextfield);
                } else {
                    addressAutoCompletePopup.show(addressLookupTextfield);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        addressLookupBox.getChildren().addAll(addressLookupTextfield, addressLookupValidateAddressButton);
        streetTextfield.setPromptText(bundle.getString("crud.address.street"));
        streetTextfield.setLabelFloat(true);
        streetTextfield.setMinWidth(350.0);
        HBox hBox = new HBox();
        hBox.setSpacing(10.0);
        numberTextfield.setPromptText(bundle.getString("crud.address.streetNumber"));
        numberTextfield.setLabelFloat(true);

        numberTextfield.setMinWidth(170.0);
        postBoxTextfield.setPromptText(bundle.getString("crud.address.postBox"));
        postBoxTextfield.setLabelFloat(true);
        postBoxTextfield.setMinWidth(170.0);

        hBox.getChildren().addAll(numberTextfield, postBoxTextfield);
        HBox cityBox = new HBox();
        cityBox.setSpacing(10.0);
        cityTextfield.setPromptText(bundle.getString("crud.city.entity"));
        cityTextfield.setLabelFloat(true);
        cityTextfield.setMinWidth(170.0);
        JFXAutoCompletePopup<City> cityAutoCompletePopup = new JFXAutoCompletePopup<>();

        Callback<ListView<City>, ListCell<City>> listCityViewListCellCallback = param -> new ListCell<City>() {
            @Override
            protected void updateItem(City item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getZipCode() + " " + item.getName());
                }
            }
        };
        cityAutoCompletePopup.setSuggestionsCellFactory(listCityViewListCellCallback);

        // SelectionHandler sets the value of the comboBox
        cityAutoCompletePopup.setSelectionHandler(event -> {
            City city = event.getObject();
            if (city == null) {
                city = new City();
                city.setName(company.get().getAddress().getCity().nameProperty().get());
                city.setZipCode(company.get().getAddress().getCity().zipCodeProperty().get());
                city.setCountry(countryService.getCountry(22));
            } else {
                cityTextfield.textProperty().unbindBidirectional(company.get().getAddress().getCity().nameProperty());
                zipCodeTextfield.textProperty().unbindBidirectional(company.get().getAddress().getCity().zipCodeProperty());
                company.get().getAddress().setCity(city);
                cityTextfield.textProperty().bindBidirectional(city.nameProperty());
                zipCodeTextfield.textProperty().bindBidirectional(city.zipCodeProperty());
            }
        });
        cityPopupActivation(cityAutoCompletePopup, cityTextfield, CitySearchTypeEnum.CITY);
        zipCodeTextfield.setPromptText(bundle.getString("crud.city.zipCode"));
        zipCodeTextfield.setLabelFloat(true);
        zipCodeTextfield.setMinWidth(170.0);
        cityPopupActivation(cityAutoCompletePopup, zipCodeTextfield, CitySearchTypeEnum.ZIP_CODE);

        cityBox.getChildren().addAll(zipCodeTextfield, cityTextfield);
        formPart3VBox.getChildren().addAll(addressLookupBox, streetTextfield, hBox, cityBox);

        TitledPane formPart4 = initializeFormPart("crud.company.fiscalDetails",null);
        mainForm.getChildren().add(formPart4);
        VBox formPart4VBox = (VBox) formPart4.getContent();


        HBox vatBox = new HBox();
        hasVATCheckBox.setMinWidth(170.0);
        companyVATTextfield.setPromptText(bundle.getString("crud.company.vATNumber"));
        companyVATTextfield.setLabelFloat(true);
        companyVATTextfield.setMinWidth(170.0);
        vatBox.getChildren().addAll(hasVATCheckBox, companyVATTextfield);
        formPart4VBox.getChildren().addAll(vatBox);


    }

    private void cityPopupActivation(JFXAutoCompletePopup<City> cityAutoCompletePopup, JFXTextField textfield, CitySearchTypeEnum type) {
        textfield.setOnKeyTyped(event -> {

            cityAutoCompletePopup.getSuggestions().clear();
            cityAutoCompletePopup.getFilteredSuggestions().clear();
            List<City> cities = new ArrayList<>();
            if (type == CitySearchTypeEnum.ZIP_CODE) {
                cities = cityService.findCities(company.get().getAddress().getCity().zipCodeProperty().get(), "");
            } else if (type == CitySearchTypeEnum.CITY) {
                cities = cityService.findCities(company.get().getAddress().getCity().zipCodeProperty().get(), company.get().getAddress().getCity().nameProperty().get());

            }
            for (City city : cities) {
                cityAutoCompletePopup.getSuggestions().add(city);
            }
            // Hide the autocomplete popup if the filtered suggestions is empty or when the box's
            // original popup is open
            if (cityAutoCompletePopup.getFilteredSuggestions().isEmpty()) {
                cityAutoCompletePopup.hide();
            } else {
                cityAutoCompletePopup.show(textfield);
            }
        });
    }



    private void createBindings(Company oldCompany) {
        // Unbind if there is an older service
        if (oldCompany != null) {
            imagePreviewField.imageProperty().unbindBidirectional(oldCompany.logoProperty());
            nameJtextfield.textProperty().unbindBidirectional(oldCompany.nameProperty());
            sloganTextfield.textProperty().unbindBidirectional(oldCompany.sloganProperty());
            phoneTextfield.textProperty().unbindBidirectional(oldCompany.phoneProperty());
            mobileTextfield.textProperty().unbindBidirectional(oldCompany.mobileProperty());
            emailTextfield.textProperty().unbindBidirectional(oldCompany.emailProperty());
            streetTextfield.textProperty().unbindBidirectional(oldCompany.getAddress().streetAddressProperty());
            postBoxTextfield.textProperty().unbindBidirectional(oldCompany.getAddress().postBoxProperty());
            cityTextfield.textProperty().unbindBidirectional(oldCompany.getAddress().streetNumberProperty());
            zipCodeTextfield.textProperty().unbindBidirectional(oldCompany.getAddress().getCity().zipCodeProperty());
            hasVATCheckBox.selectedProperty().unbindBidirectional(oldCompany.vatRegisteredProperty());
            companyVATTextfield.textProperty().unbindBidirectional(oldCompany.vatNumberProperty());
            companyVATTextfield.disableProperty().unbindBidirectional(oldCompany.vatRegisteredProperty());

        }
        company.get().addChangeListener((observable, oldValue, newValue) -> {
            openSaveDrawer();
        });
        imagePreviewField.imageProperty().bindBidirectional(company.get().logoProperty());
        nameJtextfield.textProperty().bindBidirectional(company.get().nameProperty());
        sloganTextfield.textProperty().bindBidirectional(company.get().sloganProperty());
        phoneTextfield.textProperty().bindBidirectional(company.get().phoneProperty());
        mobileTextfield.textProperty().bindBidirectional(company.get().mobileProperty());
        emailTextfield.textProperty().bindBidirectional(company.get().emailProperty());
        streetTextfield.textProperty().bindBidirectional(company.get().getAddress().streetAddressProperty());
        postBoxTextfield.textProperty().bindBidirectional(company.get().getAddress().postBoxProperty());
        numberTextfield.textProperty().bindBidirectional(company.get().getAddress().streetNumberProperty(), new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return object.toString();
            }

            @Override
            public Number fromString(String string) {
                return null;
            }
        });
        cityTextfield.textProperty().bindBidirectional(company.get().getAddress().getCity().nameProperty());
        zipCodeTextfield.textProperty().bindBidirectional(company.get().getAddress().getCity().zipCodeProperty());
        ChangeListener<String> cityListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                City city = cityService.findCity(company.get().getAddress().getCity().zipCodeProperty().get(), company.get().getAddress().getCity().nameProperty().get());
                if (city == null) {
                    city = new City();
                    city.setName(company.get().getAddress().getCity().nameProperty().get());
                    city.setZipCode(company.get().getAddress().getCity().zipCodeProperty().get());
                    city.setCountry(countryService.getCountry(22));
                } else {
                    company.get().getAddress().setCity(city);
                }
            }

        };
//        company.get().getAddress().getCity().zipCodeProperty().addListener(cityListener);
//        company.get().getAddress().getCity().nameProperty().addListener(cityListener);
        hasVATCheckBox.selectedProperty().bindBidirectional(company.get().vatRegisteredProperty());
        companyVATTextfield.textProperty().bindBidirectional(company.get().vatNumberProperty());
        companyVATTextfield.disableProperty().bind(Bindings.not(company.get().vatRegisteredProperty()));

    }
}
