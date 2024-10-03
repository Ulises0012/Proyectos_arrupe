/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.util;

/**
 *
 * @author Wendita
 */
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
    public static void addGlobalErrorMessage(String message){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "Error"));
    }
    public static void addGlobalMessage(String message){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, "info"));
    }
    
}
