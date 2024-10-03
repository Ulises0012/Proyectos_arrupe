/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.www.jpa;

/**
 *
 * @author ulise
 */
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
public class EmployeeService {
    private EntityManagerFactory emf;
    public EmployeeService(){
        emf = Persistence.createEntityManagerFactory("JPAWithHibernatePU");
    }
    public void addEmployee(Employee employee){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
    }
    public List<Employee> getAllEmployees(){
        EntityManager em = emf.createEntityManager();
        List<Employee>employees = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        em.close();
        return employees;
    }
}
