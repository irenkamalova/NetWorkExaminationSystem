/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ipcenter.kamalova.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean  // аннотация указывающая что это managed bean и поэтому мы его не описываем в faces-config.xml
@SessionScoped // аннотация указывающая что это managed bean является сессионным

public class MainBean {
    private String user;
    private String password;
    private String text;
    private String command = "Router>";

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    
    
    public void condition() {
        int f = 0;     
        if(text.equals("enable"))
            command = "Router#";
        if(text.equals("configure terminal"))
            command = "Router(config)#";
        
    }
    
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

 
   //Метод используемый нами в качестве простейшей авторизации, проверяет имя и пароль пользователя и возвращает имя страницы на которую произойдет переход
    public String checkUser(){
        if(this.user.equals("user") && this.password.equals("123")){
            return "success";
        }else{
            return "loginfailed";
        }
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }
}

