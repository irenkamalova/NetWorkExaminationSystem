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
public class QuizeController implements Serializable{
     
     private String question;
     private String answer;
     private int score;
     private int currentQuestionId;
     private QuestionsFacade qf;
     private String response;
     
     public String getResponse() { return response; }
     public void setResponse(String newValue) { response = newValue; }
     
     public List<Questions> getQuestionsList(){
         return qf.findAll();
     }
     
     public String getAnswer() {
         return answer;
     }
      public int getScore() {
          return score;
      }

 public String getQuestion() { return "wtf?"; }


 // Переопределить в целях более сложной проверки
 public boolean isCorrect(String response) {
 return response.trim().equalsIgnoreCase(answer);
 }
}


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

