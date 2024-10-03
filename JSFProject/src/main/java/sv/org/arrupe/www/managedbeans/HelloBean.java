/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.www.managedbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 *
 * @author aldes16
 */
@Named
@RequestScoped
public class HelloBean {
    
    private String name;
    private String greeting;
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getGreeting(){
        return greeting;
    }
    
    public void setGreeting(String greeting){
        this.greeting = greeting;
    }
    
    public String sayHello(){
        greeting = "Hello, " + name + "!";
        return null;
    }
}
