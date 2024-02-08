package org.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a quiz containing a list of questions.
 */
public class Quiz {
    private List<Question> questionList = new ArrayList<>();

    /**
     * Constructs a quiz by retrieving questions from the provided API URL.
     * @param apiURL the URL of the API endpoint to fetch questions from.
     */
    public Quiz(URL apiURL) {
        // Retrieve questions from the API URL and initialize the questionList
        questionList = QuizAPIParser.getQuestionsFromAPI(apiURL);

        // Print each question to the console (temporary for testing purposes)
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i).getQuestion());
        }
    }

    /**
     * Gets the list of questions in the quiz.
     * @return the list of questions.
     */
    public List<Question> getQuestionList() {
        return questionList;
    }
}
