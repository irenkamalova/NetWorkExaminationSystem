
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

    private ArrayList<String> answers = new ArrayList<String>();
    private static final Logger LOG = LoggerFactory.getLogger(QuizeController.class);
    private int score;
    private String response;
    @EJB
    private QuestionsFacade qf;
    private ArrayList<String> responseList = new ArrayList<String>();
    private int TestNumber;
    private ArrayList<Questions> questions = new ArrayList<Questions>();
    private boolean flag = true;

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

    public void setTestNumber(int number) {
        TestNumber = number;
    }

    public ArrayList<Questions> getQuestionsList() {
        questions.clear();
        List<Questions> QAll = new ArrayList<Questions>();
        QAll = qf.findAll();
        LOG.info("In answer action function!!!!!!! " + QAll.size());
        LOG.info("In answer action function!!!!!!! " + QAll.get(0));
        LOG.info("In answer action function!!!!!!! " + TestNumber + " " + QAll.get(8).getTest());
        for (int i = 0; i < QAll.size(); i++) {
            LOG.info("In answer action function!!!!!!! " + QAll.get(i).getTest());
            if (QAll.get(i).getTest().equals(TestNumber)) {
                LOG.info("In if!!!!!!! " + QAll.get(i).getTest());
                questions.add(QAll.get(i));
            }
        }
        return questions;
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
        Short qid;
        LOG.info("In SETanswer action function!!!!!!! " + questions.size());
        for (int i = 0; i < questions.size(); i++) {
            LOG.info("In for in SETanswer action function!!!!!!! " + questions.size());
            qid = questions.get(i).getQuestionId();
            LOG.info("In for of SETanswer action function!!!!!!! " + qid);
            List<String> CurrentAnswers = getAnswerList(qid);
            if (CurrentAnswers.size() == 0) {
                LOG.info("! " + CurrentAnswers.size());
                flag = false;
            } else {
                flag = true;
                LOG.info("In SETanswer action function!!!!!!! " + CurrentAnswers.size());
                answers.add(CurrentAnswers.get(0));
                LOG.info("In SETanswer function!!!!!!! " + answers.get(i));
            }
        }
    }

    public int getScore() {
        return score;
    }


    public String answerAction() {
        LOG.info("! " + flag);
        if (flag) {
            score = 0;
            for (int i = 0; i < answers.size(); i++) {
                if (responseList.get(i).equalsIgnoreCase(answers.get(i))) {
                    score++;
                    LOG.info("Score is " + score + " ==================!!!!!");
                }
                LOG.info("In answer action function!!!!!!! " + responseList.get(i) + " " + answers.get(i));
            }
            return "resultpage";
        } else {
            return "errordatabase";
        }
    }

}
