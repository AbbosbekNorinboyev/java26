package uz.brb.java26.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brb.java26.entity.AuditEventEntity;
import uz.brb.java26.enums.ActionType;
import uz.brb.java26.enums.EntityType;
import uz.brb.java26.repository.AuditEventEntityRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditEventEntityRepository auditRepository;
    private final ObjectMapper objectMapper;

    public void log(ActionType actionType,
                    EntityType entityType,
                    Long entityId,
                    Object before,
                    Object after,
                    String actor,
                    String description,
                    String ipAddress) {

        try {
            AuditEventEntity audit = AuditEventEntity.builder()
                    .id(UUID.randomUUID())
                    .actionType(actionType)
                    .entityType(entityType)
                    .entityId(entityId)
                    .actor(actor)
                    .beforeSnapshot(before != null ? objectMapper.writeValueAsString(before) : null)
                    .afterSnapshot(after != null ? objectMapper.writeValueAsString(after) : null)
                    .description(description)
                    .ipAddress(ipAddress)
                    .occurredAt(Instant.now())
                    .build();

            auditRepository.save(audit);

        } catch (Exception e) {
            // audit main flowni buzmasligi kerak
        }
    }
}