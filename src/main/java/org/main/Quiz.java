package org.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private List<Question> questionList = new ArrayList<>();

    public Quiz(URL apiURL) {
        questionList = QuizAPIParser.getQuestionsFromAPI(apiURL);
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i).getQuestion());
        }
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
