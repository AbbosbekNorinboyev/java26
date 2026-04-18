package uz.brb.java26.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.brb.java26.dto.response.Response;
import uz.brb.java26.service.AuditEventService;

@RestController
@RequestMapping("/api/audits")
@RequiredArgsConstructor
public class AuditEventController {
    private final AuditEventService auditEventService;

    @GetMapping("/getAll")
    public Response<?> getAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "20") int size) {
        return auditEventService.getAll(PageRequest.of(page, size));
    }
}
