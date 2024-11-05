package com.exo1.exo1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskPerProjectDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long projectId;
    private String projectName;
    private Long tasksCount;
}
