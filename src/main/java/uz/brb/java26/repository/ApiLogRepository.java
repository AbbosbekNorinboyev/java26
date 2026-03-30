package uz.brb.java26.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brb.java26.entity.ApiLog;

@Repository
public interface ApiLogRepository extends JpaRepository<@NonNull ApiLog, @NonNull Long> {
}