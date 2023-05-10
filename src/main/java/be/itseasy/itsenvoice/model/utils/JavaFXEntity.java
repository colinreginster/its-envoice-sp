package be.itseasy.itsenvoice.model.utils;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;

import java.util.ArrayList;
import java.util.List;

public class JavaFXEntity {
    private final List<Property> properties = new ArrayList<>();
    public void addProperty(Property property) {
        properties.add(property);
    }
    public void addChangeListener(ChangeListener listener) {
        properties.forEach(property -> property.addListener(listener));
    }

    public void removeChangeListener(ChangeListener listener) {
        properties.forEach(property -> property.removeListener(listener));
    }

    public List<Property> getProperties() {
        return properties;
    }
}

