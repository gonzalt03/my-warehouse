package fr.onefox.mywarehouse.repositories;


import fr.onefox.mywarehouse.domain.Transaction;
import fr.onefox.mywarehouse.domain.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAll(Pageable pageable);

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.refCode=:refCode AND t.type=:type")
    Long countByRefCodeAndType(@Param("refCode") String refCode, @Param("type") TransactionType type);

}
