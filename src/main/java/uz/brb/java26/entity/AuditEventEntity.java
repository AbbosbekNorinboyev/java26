package uz.brb.java26.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.brb.java26.enums.ActionType;
import uz.brb.java26.enums.EntityType;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "audit_event",
        indexes = {
                @Index(name = "idx_audit_time", columnList = "occurred_at"),
                @Index(name = "idx_audit_action_type", columnList = "action_type"),
                @Index(name = "idx_audit_entity", columnList = "entity_type,entity_id"),
                @Index(name = "idx_audit_actor", columnList = "actor")
        }
)
public class AuditEventEntity {

    @Id
    private UUID id;

    @Column(name = "action_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ActionType actionType; // CREATE, UPDATE, DELETE

    @Column(name = "entity_type", nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    private EntityType entityType; // VEHICLE, DRIVER, TRIP

    @Column(name = "entity_id", nullable = false)
    private Long entityId;

    @Column(length = 128)
    private String actor; // kim qilgan (admin, system, user)

    @Column(name = "action", length = 32)
    private String action; // optional: CREATE/UPDATE/DELETE

    @Column(name = "before_snapshot", columnDefinition = "TEXT")
    private String beforeSnapshot; // oldingi holat JSON

    @Column(name = "after_snapshot", columnDefinition = "TEXT")
    private String afterSnapshot; // yangi holat JSON

    @Column(length = 512)
    private String description; // qisqa izoh

    @Column(name = "ip_address", length = 64)
    private String ipAddress; // optional security uchun

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;
}