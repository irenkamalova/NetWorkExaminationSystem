/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ipcenter.kamalova.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ru.ipcenter.kamalova.jpa.entities.Answers;
import ru.ipcenter.kamalova.jpa.entities.Questions;
import ru.ipcenter.kamalova.jpa.entities.Tasks;
import ru.ipcenter.kamalova.jpa.session.AnswersFacade;
import ru.ipcenter.kamalova.jpa.session.QuestionsFacade;

/**
 *
 * @author днс
 */
@ManagedBean(name = "testController")
@SessionScoped
public class TestController {
    
    private String testpage;
    private Tasks taskId;
    @EJB
    private QuestionsFacade qf;
    @EJB    
    private AnswersFacade af;

    public String getTestpage() {
        return testpage;
    }

    public void setTestpage(String testpage) {
        this.testpage = testpage;
    }
    
    public void update() {
        testpage = "Test is not exist";
        //return "testpage";
    }
    
    public List<Questions> getQuestionsList() {
        return qf.findAll();        
    }
    
    public Collection<String> getAnswerList(Short qid) {
        Collection<Answers> tmp = qf.find(qid).getAnswersCollection();
        List<String> result = new ArrayList<String>();
        for(Answers answer : tmp) {
            result.add(answer.getAnswer());
        }
        return result;
    }
    
    public TestController() {
        
    }
    
}
