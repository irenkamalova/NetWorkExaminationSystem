/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ipcenter.kamalova.jsf;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ru.ipcenter.kamalova.jpa.entities.Questions;
import ru.ipcenter.kamalova.jpa.session.QuestionsFacade;

/**
 *
 * @author днс
 */
@ManagedBean(name = "quizeController")
@SessionScoped
public class QuizeController implements Serializable {

    private String question;
    private String answer = "7";
    private int score;
    private QuestionsFacade qf;
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String newValue) {
        response = newValue;
    }

    public List<Questions> getQuestionsList() {
        return qf.findAll();
    }

    public String getAnswer() {
        return answer;
    }

    public int getScore() {
        return score;
    }

    public boolean isCorrect(String response) {
        return response.trim().equalsIgnoreCase(answer);
    }
    
    public boolean isCorrect() {
        return response.trim().equalsIgnoreCase(answer);
        
    }
    
    public String answerAction() {
        score = 7;
        if(this.isCorrect())
            score++;
        return "resultpage";
    }
    
}

    // Add business logic below. (Right-click in editor and choose
// "Insert Code > Add Business Method")

