package org.main;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The QuizAPIParser class for parsing data from the Open Trivia Database API.
 */
public class QuizAPIParser {
    private static final String CATEGORY_API_LINK = "https://opentdb.com/api_category.php";

    /**
     * Retrieves the list of categories from the Open Trivia Database API.
     * @return The list of categories.
     */
    public static List<String> getCategoryList() {
        List<String> categoryList = new ArrayList<>();
        try {
            URL categoryAPIUrl = new URL(CATEGORY_API_LINK); // Create URL object for category API link
            try (InputStream is = categoryAPIUrl.openStream(); JsonReader reader = Json.createReader(is)) {
                JsonObject jsonObject = reader.readObject(); // Parse JSON response
                JsonArray categories = jsonObject.getJsonArray("trivia_categories");
                for (JsonObject result : categories.getValuesAs(JsonObject.class)) {
                    categoryList.add(result.getJsonNumber("id") + "_" + result.getString("name")); // Add category to list
                }
            }
        } catch (MalformedURLException e) {
            UIControl.createAlert("There was a problem fetching the Category API", true);
        } catch (IOException e) {
            UIControl.createAlert("There was a problem opening the Category API link", true);
        }
        return categoryList;
    }

    /**
     * Creates the API URL for retrieving quiz questions based on parameters.
     * @param numberOfQuestions The number of questions.
     * @param category The category of questions.
     * @param difficulty The difficulty level.
     * @param type The type of questions.
     * @return The constructed URL object.
     */
    public static URL createAPIURL(String numberOfQuestions, String category, String difficulty, String type) {
        StringBuilder sb = new StringBuilder("https://opentdb.com/api.php?amount=");
        Random random = new Random();
        numberOfQuestions = (numberOfQuestions.equals("random")) ?
            String.valueOf(random.nextInt(((15 - 5) + 1) + 5)) : numberOfQuestions; // Generate random number of questions if "random"
        sb.append(numberOfQuestions);

        List<String> tempList = getCategoryList()
            .stream()
            .map(s -> s.trim().toLowerCase().replace(' ', '_'))
            .toList();

        if (!(category.equals("random"))) { // Add category if not "random"
            for (String tempCat : tempList) {
                if (tempCat.contains(category.trim())) {
                    sb.append("&category=" + (tempList.indexOf(tempCat) + 9));
                    break;
                }
            }
        }

        if (!difficulty.equals("random")) { // Add difficulty if not "random"
            sb.append("&difficulty=" + difficulty);
        }

        if (!type.equals("random")) { // Add type if not "random"
            sb.append("&type=" + type);
        }

        URL url = null;
        try {
            url = new URL(sb.toString()); // Create URL object from StringBuilder
        } catch (MalformedURLException e) {
            UIControl.createAlert("There was a problem creating the API URL", true); // Alert user for malformed URL
        }

        return url;
    }

    /**
     * Retrieves questions from the API based on the provided URL.
     * @param apiURL The API URL.
     * @return The list of questions.
     */
    public static List<Question> getQuestionsFromAPI(URL apiURL) {
        List<Question> questionList = new ArrayList<>();
        try {
            try (InputStream is = apiURL.openStream(); JsonReader reader = Json.createReader(is)) {
                JsonObject jsonObject = reader.readObject(); // Parse JSON response
                int responseCode = jsonObject.getInt("response_code");
                if (responseCode == 0) { // Check for successful response code
                    JsonArray results = jsonObject.getJsonArray("results");
                    for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                        String type = result.getString("type");
                        String difficulty = result.getString("difficulty");
                        String category = result.getString("category");
                        String question = result.getString("question");
                        String correctAnswer = result.getString("correct_answer");
                        List<String> possibleAnswers = new ArrayList<>();
                        possibleAnswers.add(correctAnswer);
                        JsonArray answersArr = result.getJsonArray("incorrect_answers");
                        for (JsonString answer : answersArr.getValuesAs(JsonString.class)) {
                            possibleAnswers.add(answer.toString());
                        }
                        questionList.add(new Question(question, type, difficulty, category, correctAnswer, possibleAnswers)); // Add question to list
                    }
                } else {
                    UIControl.createAlert("There was a problem creating the quiz", true); // Alert user for unsuccessful response code
                }
            }
        } catch (MalformedURLException e) {
            UIControl.createAlert("There was a problem fetching the Quiz API", true); // Alert user for malformed URL
        } catch (IOException e) {
            UIControl.createAlert("There was a problem opening the Quiz API link", true); // Alert user for IO exception
        }
        return questionList;
    }
}
