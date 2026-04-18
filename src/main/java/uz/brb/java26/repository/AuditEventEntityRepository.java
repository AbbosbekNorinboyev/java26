package uz.brb.java26.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.brb.java26.entity.AuditEventEntity;

@Repository
public interface AuditEventEntityRepository extends JpaRepository<@NonNull AuditEventEntity, @NonNull Long> {
    @Query("""
            select a from AuditEventEntity a 
                where (
                    :query is null or :query = '' 
                    or lower(a.actionType) like lower(concat('%', :query, '%')) 
                )
            """)
    Page<AuditEventEntity> search(@Param("query") String query, Pageable pageable);
}