package uz.brb.java26.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.brb.java26.entity.AuthUser;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<@NonNull AuthUser, @NonNull Long> {
    Optional<AuthUser> findByUsername(String username);

    @Query("""
            SELECT a FROM AuthUser a
                WHERE a.username = :name
                    OR a.fullName = :name
            """)
    Optional<AuthUser> findByUsernameOrFullName(String name);
}