/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sv.org.arrupe.springmvc.repositories;
import sv.org.arrupe.springmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author hecto
 */
public interface ProductRepository extends JpaRepository<Product,Long>{
    Product findByProdName(final String prodName);
}
