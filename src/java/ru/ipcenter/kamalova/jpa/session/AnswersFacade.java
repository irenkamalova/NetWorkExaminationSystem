/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ipcenter.kamalova.jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.ipcenter.kamalova.jpa.entities.Answers;

/**
 *
 * @author днс
 */
@Stateless
public class AnswersFacade extends AbstractFacade<Answers> {
    @PersistenceContext(unitName = "NetWorkExaminationSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswersFacade() {
        super(Answers.class);
    }
    
}
