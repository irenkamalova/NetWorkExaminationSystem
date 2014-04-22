/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ipcenter.kamalova.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ipcenter.kamalova.jpa.entities.Answers;
import ru.ipcenter.kamalova.jpa.entities.Questions;
import ru.ipcenter.kamalova.jpa.session.QuestionsFacade;

/**
 *
 * @author днс
 */
@ManagedBean(name = "quizeController")
@SessionScoped
public class QuizeController implements Serializable {

    public QuizeController() {
    }

    private String question;
    private ArrayList<String> answers = new ArrayList<String>();
    private static final Logger LOG = LoggerFactory.getLogger(QuizeController.class);
    private int score;
    private String response;
    @EJB
    private QuestionsFacade qf;
    private ArrayList<String> responseList = new ArrayList<String>();

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        responseList.add(response);
    }

    public ArrayList<String> getResponseList() {
        return responseList;
    }

    public void clearList() {
        responseList.clear();
    }

    public void setResponseList(ArrayList<String> responseList) {
        this.responseList = responseList;
    }

    public void setOneResponse(String response) {
        responseList.add(response);
    }

    public List<Questions> getQuestionsList() {
        return qf.findAll();
        
    }

    public List<String> getAnswerList(Short qid) {
        Collection<Answers> tmp = qf.find(qid).getAnswersCollection();
        List<String> result = new ArrayList<String>();
        for (Answers answer : tmp) {
            result.add(answer.getAnswer());
        }
        return result;
    }
    
    public void setAnswers() {
        answers.clear();
        for(int i = 0; i < getQuestionsList().size(); i++) {
            List<String> CurrentAnswers = getAnswerList(getQuestionsList().get(i).getQuestionId());
            answers.add(CurrentAnswers.get(0));
        }
    }

    public int getScore() {
        return score;
    }

    /* public boolean isCorrect() {
     return response.trim().equalsIgnoreCase(answer); //игнорировать большие,маленькие буквы
     }
    
     public void answerAction() {
     score = 0;
     if(this.isCorrect())
     score++;
     System.out.println("trololo");
     } */
    public String answerAction() {        
        score = 0;
        for (int i = 0; i < answers.size(); i++) {
            if (responseList.get(i).equalsIgnoreCase(answers.get(i))) {
                score++;
                LOG.info("Score is " + score + " ==================!!!!!");
            }
            LOG.info("In answer action function!!!!!!! " + responseList.get(i) + " " + answers.get(i));
        }
        return "resultpage";
    }
}

    // Add business logic below. (Right-click in editor and choose
// "Insert Code > Add Business Method")

