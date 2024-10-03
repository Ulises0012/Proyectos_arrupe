/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.www.jpa;

/**
 *
 * @author ulise
 */
public class Main {
    public static void main(String[]args){
        EmployeeService service = new EmployeeService();
        
        Employee emp = new Employee();
        emp.setName("Daniel SOsa");
        emp.setDepartment("graphic designer");
        emp.setSalary(1000);
        service.addEmployee(emp);
        service.getAllEmployees().forEach(e ->{
            System.out.println("Employee: " +e.getName() + ", Department: " + e.getDepartment() + ", Salary: " + e.getSalary());
        });
    }
    
    
}
