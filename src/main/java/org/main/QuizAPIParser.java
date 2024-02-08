package org.main;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizAPIParser {
    private static final String CATEGORY_API_LINK = "https://opentdb.com/api_category.php";

    public static List<String> getCategoryList() {
        List<String> categoryList = new ArrayList<>();
        try {
            URL categoryAPIUrl = new URL(CATEGORY_API_LINK);
            try (InputStream is = categoryAPIUrl.openStream(); JsonReader reader = Json.createReader(is)) {
                JsonObject jsonObject = reader.readObject();
                JsonArray categories = jsonObject.getJsonArray("trivia_categories");
                for (JsonObject result : categories.getValuesAs(JsonObject.class)) {
                    categoryList.add(result.getJsonNumber("id") + "_" + result.getString("name"));
                }
            }
        } catch (MalformedURLException e) {
            UIControl.createAlert("There was a problem fetching the Category API", true);
        } catch (IOException e) {
            UIControl.createAlert("There was a problem opening the Category API link", true);
        }
        return categoryList;
    }

    public static URL createAPIURL(String numberOfQuestions, String category, String difficulty, String type) {
        StringBuilder sb = new StringBuilder("https://opentdb.com/api.php?amount=");
        Random random = new Random();
        numberOfQuestions = (numberOfQuestions.equals("random")) ?
            String.valueOf(random.nextInt(((50 - 5) + 1) + 5)) : numberOfQuestions;
        sb.append(numberOfQuestions);

        List<String> tempList = getCategoryList()
            .stream()
            .map(s -> s.trim().toLowerCase().replace(' ', '_'))
            .toList();

        if (!(category.equals("random"))) {
            for (String tempCat : tempList) {
                if (tempCat.contains(category.trim())) {
                    sb.append("&category=" + (tempList.indexOf(tempCat) + 9));
                    break;
                }
            }
        }

        if (!difficulty.equals("random")) {
            sb.append("&difficulty=" + difficulty);
        }

        if (!type.equals("random")) {
            sb.append("&type=" + type);
        }

        URL url = null;
        try {
            url = new URL(sb.toString());
        } catch (MalformedURLException e) {
            UIControl.createAlert("There was a problem creating the API URL", true);
        }

        return url;
    }

    private boolean checkQuizParameters(String numberOfQuestions, String category, String difficulty, String type) {
        return false;
    }
}
