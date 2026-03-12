package com.fatura_service.dto;

import com.fatura_service.model.enums.StatusTransacao;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record TransacaoResponseDTO(
        UUID idTransacao,
        BigDecimal valor,
        LocalDateTime dataCriacao,
        StatusTransacao statusTransacao
) {

}
