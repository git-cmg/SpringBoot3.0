package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 统一接收分页参数类型
 */
@Data
public class TableListDto {

    @Schema(description = "分页参数pageSize")
    @NotNull(message = "分页参数pageSize不允许为空")
    private int pageSize;

    @Schema(description = "分页参数current")
    @NotNull(message = "分页参数current不允许为空")
    private int current;
}
