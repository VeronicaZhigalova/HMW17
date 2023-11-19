package org.example;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.net.http.HttpClient;
import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HttpServiceTest {


    @RegisterExtension
    public static final WireMockExtension wireMockExtension = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    private HttpService httpService;

    @BeforeEach
    void setUp() {
        httpService = new HttpService(HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build(),
                wireMockExtension.baseUrl());
    }


    @Test
    void testGetById() {
        String id = "margarita";

        wireMockExtension.stubFor(WireMock.get(HttpService.BASE_PATH + "/search.php?s=" + id)
                .willReturn(aResponse().withStatus(200).withBody(responseBody())));

        try {
            CocktailResponse response = httpService.getCocktailById(id);
            assertEquals("Margarita", response.getStrDrink());
            assertEquals("Alcoholic", response.getStrAlcoholic());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetCocktailById2() {
        String id = "a";

        wireMockExtension.stubFor(WireMock.get(HttpService.BASE_PATH + "/search.php?f=" + id)
                .willReturn(aResponse().withStatus(200).withBody(responseBody2())));

        try {
            CocktailResponse response2 = httpService.getCocktailById(id);
            assertEquals("A1", response2.getStrDrink());
            assertEquals("Alcoholic", response2.getStrAlcoholic());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetCocktailById3() {
        String id = "vodka";

        wireMockExtension.stubFor(WireMock.get(HttpService.BASE_PATH + "/search.php?i=" + id)
                .willReturn(aResponse().withStatus(200).withBody(responseBody3())));

        try {
            CocktailResponse response3 = httpService.getCocktailById(id);
            assertEquals("1", response3.getIdIngredient());
            assertEquals("Alcoholic", response3.getStrAlcoholic());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String responseBody() {
        return "{" +
                "\"drinks\": [" +
                "{" +
                "\"idDrink\": \"11007\"," +
                "\"strDrink\": \"Margarita\"," +
                "\"strDrinkAlternate\": null," +
                "\"strTags\": \"IBA,ContemporaryClassic\"," +
                "\"strVideo\": null," +
                "\"strCategory\": \"Ordinary Drink\"," +
                "\"strIBA\": \"Contemporary Classics\"," +
                "\"strAlcoholic\": \"Alcoholic\"," +
                "\"strGlass\": \"Cocktail glass\"," +
                "\"strInstructions\": \"Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass.\"," +
                "\"strInstructionsES\": null," +
                "\"strInstructionsDE\": \"Reiben Sie den Rand des Glases mit der Limettenscheibe, damit das Salz daran haftet. Achten Sie darauf, dass nur der äußere Rand angefeuchtet wird und streuen Sie das Salz darauf. Das Salz sollte sich auf den Lippen des Genießers befinden und niemals in den Cocktail einmischen. Die anderen Zutaten mit Eis schütteln und vorsichtig in das Glas geben.\"," +
                "\"strInstructionsFR\": null," +
                "\"strInstructionsIT\": \"Strofina il bordo del bicchiere con la fetta di lime per far aderire il sale.\\r\\nAvere cura di inumidire solo il bordo esterno e cospargere di sale.\\r\\nIl sale dovrebbe presentarsi alle labbra del bevitore e non mescolarsi mai al cocktail.\\r\\nShakerare gli altri ingredienti con ghiaccio, quindi versarli delicatamente nel bicchiere.\"," +
                "\"strInstructionsZH-HANS\": null," +
                "\"strInstructionsZH-HANT\": null," +
                "\"strDrinkThumb\": \"https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg\"," +
                "\"strIngredient1\": \"Tequila\"," +
                "\"strIngredient2\": \"Triple sec\"," +
                "\"strIngredient3\": \"Lime juice\"," +
                "\"strIngredient4\": \"Salt\"," +
                "\"strIngredient5\": null," +
                "\"strIngredient6\": null," +
                "\"strIngredient7\": null," +
                "\"strIngredient8\": null," +
                "\"strIngredient9\": null," +
                "\"strIngredient10\": null," +
                "\"strIngredient11\": null," +
                "\"strIngredient12\": null," +
                "\"strIngredient13\": null," +
                "\"strIngredient14\": null," +
                "\"strIngredient15\": null," +
                "\"strMeasure1\": \"1 1/2 oz \"," +
                "\"strMeasure2\": \"1/2 oz \"," +
                "\"strMeasure3\": \"1 oz \"," +
                "\"strMeasure4\": null," +
                "\"strMeasure5\": null," +
                "\"strMeasure6\": null," +
                "\"strMeasure7\": null," +
                "\"strMeasure8\": null," +
                "\"strMeasure9\": null," +
                "\"strMeasure10\": null," +
                "\"strMeasure11\": null," +
                "\"strMeasure12\": null," +
                "\"strMeasure13\": null," +
                "\"strMeasure14\": null," +
                "\"strMeasure15\": null," +
                "\"strImageSource\": \"https://commons.wikimedia.org/wiki/File:Klassiche_Margarita.jpg\"," +
                "\"strImageAttribution\": \"Cocktailmarler\"," +
                "\"strCreativeCommonsConfirmed\": \"Yes\"," +
                "\"dateModified\": \"2015-08-18 14:42:59\"" +
                "}" +
                "]" +
                "}";
    }

    private static String responseBody2() {
        return "{" +
                "\"drinks\": [" +
                "{" +
                "\"idDrink\": \"17222\"," +
                "\"strDrink\": \"A1\"," +
                "\"strDrinkAlternate\": null," +
                "\"strTags\": null," +
                "\"strVideo\": null," +
                "\"strCategory\": \"Cocktail\"," +
                "\"strIBA\": null," +
                "\"strAlcoholic\": \"Alcoholic\"," +
                "\"strGlass\": \"Cocktail glass\"," +
                "\"strInstructions\": \"Pour all ingredients into a cocktail shaker, mix and serve over ice into a chilled glass.\"," +
                "\"strInstructionsES\": \"Vierta todos los ingredientes en una coctelera, mezcle y sirva con hielo en un vaso frío.\"," +
                "\"strInstructionsDE\": \"Alle Zutaten in einen Cocktailshaker geben, mischen und über Eis in ein gekühltes Glas servieren.\"," +
                "\"strInstructionsFR\": null," +
                "\"strInstructionsIT\": \"Versare tutti gli ingredienti in uno shaker, mescolare e servire con ghiaccio in un bicchiere freddo.\"," +
                "\"strInstructionsZH-HANS\": null," +
                "\"strInstructionsZH-HANT\": null," +
                "\"strDrinkThumb\": \"https://www.thecocktaildb.com/images/media/drink/2x8thr1504816928.jpg\"," +
                "\"strIngredient1\": \"Gin\"," +
                "\"strIngredient2\": \"Grand Marnier\"," +
                "\"strIngredient3\": \"Lemon Juice\"," +
                "\"strIngredient4\": \"Grenadine\"," +
                "\"strIngredient5\": null," +
                "\"strIngredient6\": null," +
                "\"strIngredient7\": null," +
                "\"strIngredient8\": null," +
                "\"strIngredient9\": null," +
                "\"strIngredient10\": null," +
                "\"strIngredient11\": null," +
                "\"strIngredient12\": null," +
                "\"strIngredient13\": null," +
                "\"strIngredient14\": null," +
                "\"strIngredient15\": null," +
                "\"strMeasure1\": \"1 3/4 shot\"," +
                "\"strMeasure2\": \"1 Shot\"," +
                "\"strMeasure3\": \"1/4 Shot\"," +
                "\"strMeasure4\": \"1/8 Shot\"," +
                "\"strMeasure5\": null," +
                "\"strMeasure6\": null," +
                "\"strMeasure7\": null," +
                "\"strMeasure8\": null," +
                "\"strMeasure9\": null," +
                "\"strMeasure10\": null," +
                "\"strMeasure11\": null," +
                "\"strMeasure12\": null," +
                "\"strMeasure13\": null," +
                "\"strMeasure14\": null," +
                "\"strMeasure15\": null," +
                "\"strImageSource\": null," +
                "\"strImageAttribution\": null," +
                "\"strCreativeCommonsConfirmed\": \"No\"," +
                "\"dateModified\": \"2017-09-07 21:42:09\"" +
                "}" +
                "]" +
                "}";
    }

    private static String responseBody3() {
        return "{" +
                "\"ingredients\": [" +
                "{" +
                "\"idIngredient\": \"1\"," +
                "\"strIngredient\": \"Vodka\"," +
                "\"strDescription\": \"Vodka is a distilled beverage composed primarily of water and ethanol, sometimes with traces of impurities and flavorings. Traditionally, vodka is made by the distillation of fermented cereal grains or potatoes, though some modern brands use other substances, such as fruits or sugar.\r\n\r\nSince the 1890s, the standard Polish, Russian, Belarusian, Ukrainian, Estonian, Latvian, Lithuanian and Czech vodkas are 40% alcohol by volume ABV (80 US proof), a percentage that is widely misattributed to Dmitri Mendeleev. The European Union has established a minimum of 37.5% ABV for any \"European vodka\" to be named as such. Products sold as \"vodka\" in the United States must have a minimum alcohol content of 40%. Even with these loose restrictions, most vodka sold contains 40% ABV. For homemade vodkas and distilled beverages referred to as \"moonshine\", see moonshine by country.\r\n\r\nVodka is traditionally drunk neat (not mixed with any water, ice, or other mixer), though it is often served chilled in the vodka belt countries (Belarus, Estonia, Finland, Iceland, Latvia, Lithuania, Norway, Poland, Russia, Sweden, Ukraine). It is also commonly used in cocktails and mixed drinks, such as the vodka martini, Cosmopolitan, vodka tonic, Screwdriver, Greyhound, Black or White Russian, Moscow Mule, and Bloody Mary.\r\n\r\nScholars debate the beginnings of vodka. It is a contentious issue because very little historical material is available. For many centuries, beverages differed significantly compared to the vodka of today, as the spirit at that time had a different flavor, color and smell, and was originally used as medicine. It contained little alcohol, an estimated maximum of about 14%, as only this amount can be attained by natural fermentation. The still, allowing for distillation (\"burning of wine\"), increased purity, and increased alcohol content, was invented in the 8th century.\r\n\r\nA common property of the vodkas produced in the United States and Europe is the extensive use of filtration prior to any additional processing including the addition of flavorants. Filtering is sometimes done in the still during distillation, as well as afterwards, where the distilled vodka is filtered through activated charcoal and other media to absorb trace amounts of substances that alter or impart off-flavors to the vodka. However, this is not the case in the traditional vodka-producing nations, so many distillers from these countries prefer to use very accurate distillation but minimal filtering, thus preserving the unique flavors and characteristics of their products.\r\n\r\nThe master distiller is in charge of distilling the vodka and directing its filtration, which includes the removal of the \"fore-shots\", \"heads\" and \"tails\". These components of the distillate contain flavor compounds such as ethyl acetate and ethyl lactate (heads) as well as the fusel oils (tails) that impact the usually desired clean taste of vodka. Through numerous rounds of distillation, or the use of a fractioning still, the taste is modified and clarity is increased. In contrast, distillery process for liquors such as whiskey, rum, and baijiu allow portions of the \"heads\" and \"tails\" to remain, giving them their unique flavors.\r\n\r\nRepeated distillation of vodka will make its ethanol level much higher than is acceptable to most end users, whether legislation determines strength limits or not. Depending on the distillation method and the technique of the stillmaster, the final filtered and distilled vodka may have as much as 95–96% ethanol. As such, most vodka is diluted with water prior to bottling.\r\n\r\nPolish distilleries make a very pure (96%, 192 proof, formerly also 98%) rectified spirit (Polish language: spirytus rektyfikowany). Technically a form of vodka, it is sold in liquor stores rather than pharmacies. Similarly, the German market often carries German, Hungarian, Polish, and Ukrainian-made varieties of vodka of 90 to 95% ABV. A Bulgarian vodka, Balkan 176°, has an 88% alcohol content. Everclear, an American brand, is also sold at 95% ABV.\"," +
                "\"strType\": \"Vodka\"," +
                "\"strAlcohol\": \"Yes\"," +
                "\"strABV\": \"40\"" +
                "}" +
                "]" +
                "}";
    }
}