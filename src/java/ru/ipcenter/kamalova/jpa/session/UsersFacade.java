/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ipcenter.kamalova.jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ru.ipcenter.kamalova.jpa.entities.Users;

/**
 *
 * @author днс
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "NetWorkExaminationSystemPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public Users findUser(String user) {
        return (Users) em.createNamedQuery("Users.findByNickName").setParameter("nickName", user).getSingleResult();
    }
    
    public Users findPassword(String password) {
        return (Users) em.createNamedQuery("Users.findByPassword").setParameter("password", password).getSingleResult();
    }
    
    public boolean ifexist(String user, String password) {
        try {
            if(findUser(user).equals(findPassword(password))) {
                return true;
            }
            else
            return false;
        }
        catch(javax.persistence.NoResultException e) {
            return false;
        }
    }
    public UsersFacade() {
        super(Users.class);
    }
    
}
