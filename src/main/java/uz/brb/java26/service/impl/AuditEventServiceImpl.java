package uz.brb.java26.service.impl;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.brb.java26.dto.response.AuditEventResponse;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.entity.AuditEventEntity;
import uz.brb.java26.repository.AuditEventEntityRepository;
import uz.brb.java26.service.AuditEventService;

import java.time.LocalDateTime;
import java.util.List;

import static uz.brb.java26.util.Util.localDateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuditEventServiceImpl implements AuditEventService {
    private final AuditEventEntityRepository auditEventEntityRepository;

    @Override
    public Response<?> getAll(Pageable pageable, String query) {
        List<@NonNull AuditEventEntity> auditEventEntities = auditEventEntityRepository.search(query, pageable).getContent();
        List<AuditEventResponse> auditEventResponse = auditEventEntities.stream()
                .map(auditEventEntity -> AuditEventResponse.builder()
                        .id(auditEventEntity.getId())
                        .actionType(auditEventEntity.getActionType())
                        .entityType(auditEventEntity.getEntityType())
                        .entityId(auditEventEntity.getEntityId())
                        .actor(auditEventEntity.getActor())
                        .action(auditEventEntity.getAction())
                        .beforeSnapshot(auditEventEntity.getBeforeSnapshot())
                        .afterSnapshot(auditEventEntity.getAfterSnapshot())
                        .description(auditEventEntity.getDescription())
                        .ipAddress(auditEventEntity.getIpAddress())
                        .occurredAt(auditEventEntity.getOccurredAt())
                        .build()
                ).toList();
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .success(true)
                .message("AuditEvent list successfully found")
                .data(auditEventResponse)
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }
}
