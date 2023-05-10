package be.itseasy.itsenvoice.model.utils;

import be.itseasy.itsenvoice.model.entities.Item;
import jakarta.persistence.Column;
import jakarta.persistence.criteria.Predicate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.springframework.data.jpa.domain.Specification;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemSpecification {
    public static Specification<Item> textInAllColumns(String text) {
        if (text == null) {
            return Specification.where(null);
        } else {
            return (root, query, builder) -> {
                List<Predicate> predicates = new ArrayList<>();
                Field[] fields = Item.class.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getType() != Integer.class) {
                        if (field.getType() == SimpleStringProperty.class) {
                            String containsLikePattern = getContainsLikePattern(text);
                            predicates.add(builder.like(root.get(field.getName()), "%" + containsLikePattern + "%"));
                        }
                        if (field.getType() == SimpleObjectProperty.class && ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0] == BigDecimal.class && text.matches("-?\\d+(\\.\\d+)?")) {
                            predicates.add(builder.equal(root.get(field.getName()), text));
                        }
                    }
                }

                return builder.or(predicates.toArray(new Predicate[0]));
            };
        }
    }


    private static String getContainsLikePattern(String searchTerms) {
        if (searchTerms == null || searchTerms.isEmpty()) {
            return "%";
        } else {
            return "%" + searchTerms.toLowerCase() + "%";
        }
    }

    public static String snakeToCamel(String snakeCase) {
        StringBuilder sb = new StringBuilder();
        boolean nextCharUpper = false;

        // Iterate over the characters in the string
        for (char c : snakeCase.toCharArray()) {
            // If the current character is an underscore
            if (c == '_') {
                // set flag to convert next character to uppercase
                nextCharUpper = true;
            } else if (nextCharUpper) {
                // Convert the current character to uppercase and append it to the string
                sb.append(Character.toUpperCase(c));
                nextCharUpper = false;
            } else {
                // Just append the current character
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

