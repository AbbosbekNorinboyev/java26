package uz.brb.java26.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductCategoryResponse(
        Long id,
        String nameRu,
        String nameUz,
        Long parentId,
        int sortOrder,
        boolean active,
        LocalDateTime datetimeCreated
) {
}
