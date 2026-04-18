package uz.brb.java26.service;

import org.springframework.data.domain.Pageable;
import uz.brb.java26.dto.response.Response;

public interface AuditEventService {
    Response<?> getAll(Pageable pageable);
}
