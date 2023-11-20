package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CocktailResponse {
    private String idDrink;
    private String strDrink;
    private String strCategory;
    private String strAlcoholic;
    private String idIngredient;
    private String strIngredient;
    private String strType;
    private String strAlcohol;

}