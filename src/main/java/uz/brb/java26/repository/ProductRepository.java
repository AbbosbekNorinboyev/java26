package uz.brb.java26.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.brb.java26.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
            SELECT p FROM Product p
            WHERE LOWER(p.barcode) LIKE LOWER(CONCAT('%', :code, '%'))
               OR LOWER(p.sku)     LIKE LOWER(CONCAT('%', :code, '%'))
            """)
    Optional<Product> findByBarcodeOrSkuIgnoreCase(String code);
}
