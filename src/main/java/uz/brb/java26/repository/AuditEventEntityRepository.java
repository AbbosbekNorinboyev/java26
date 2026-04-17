package uz.brb.java26.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brb.java26.entity.AuditEventEntity;

@Repository
public interface AuditEventEntityRepository extends JpaRepository<@NonNull AuditEventEntity, @NonNull Long> {
}