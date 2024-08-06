package com.ex.popply.ticket.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateTicketRequest {

    @NotEmpty(message = "티켓상품 이름을 입력해주세요")
    @Schema(nullable = false, example = "테스트용 티켓")
    private String name;

    @Schema(nullable = true, example = "티켓 설명을 입력하세요.")
    @Nullable
    private String description;

    @NotNull
    @Schema(defaultValue = "0", nullable = false, example = "4000")
    private Long price;

    @NotNull
    @Schema(nullable = false, example = "100")
    private Long supplyCount;

    @NotNull
    @Schema(nullable = false, example = "true")
    private Boolean isQuantityPublic;

    @NotNull
    @Schema(nullable = false, example = "1")
    private Long purchaseLimit;


}
