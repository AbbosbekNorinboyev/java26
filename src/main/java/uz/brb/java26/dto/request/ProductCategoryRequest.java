package uz.brb.java26.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategoryRequest {
    @NotBlank
    private String nameRu;
    private String nameUz;
    private Long parentId;
    private int sortOrder;
    private Boolean active;
}
