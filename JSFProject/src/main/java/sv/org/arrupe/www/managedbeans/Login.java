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
public class Login {
    
    private String username;
    private String password;
    private String message;
    
    //definimos datos estáticos, en este caso "Daniel" y "Password" para nuestras credenciales
    
    private final String STATIC_USERNAME = "Daniel";
    private final String STATIC_PASSWORD = "password";
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setMessage(String message){
        this.message = message;
    }
    
    public String login(){
        if (STATIC_USERNAME.equals(username) && STATIC_PASSWORD.equals(password)){
            message = "Login succeful!";
        return "home"; // si todo sale bien nos redigirá a "home.xhtml" página que vamos a crear
    } else {
        message="Invalid username or password!";
        return null; // Si fallan las credenciales, pues unicamente mostramos un mensaje de Error.
        
        }
    }
}
