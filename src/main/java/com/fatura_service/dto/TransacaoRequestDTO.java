package com.fatura_service.dto;

import com.fatura_service.model.Transacao;
import com.fatura_service.model.enums.StatusTransacao;
import com.fatura_service.model.enums.TipoConta;
import com.fatura_service.model.enums.TipoTransacao;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
public record TransacaoRequestDTO(
          Long idUsuario,
          BigDecimal valor,
          TipoTransacao tipoTransacao,
          TipoConta tipoConta
) {

}
