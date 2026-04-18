package uz.brb.java26.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.brb.java26.enums.ActionType;
import uz.brb.java26.enums.EntityType;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditEventResponse {
    private UUID id;
    private ActionType actionType;
    private EntityType entityType;
    private Long entityId;
    private String actor;
    private String action;
    private String beforeSnapshot;
    private String afterSnapshot;
    private String description;
    private String ipAddress;
    private Instant occurredAt;
}