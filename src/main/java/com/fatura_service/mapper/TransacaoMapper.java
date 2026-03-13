package com.fatura_service.mapper;

import com.fatura_service.dto.TransacaoRequestDTO;
import com.fatura_service.dto.TransacaoResponseDTO;
import com.fatura_service.model.Transacao;
import com.fatura_service.model.enums.StatusTransacao;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class TransacaoMapper {

    public static Transacao toTransacao(TransacaoRequestDTO requestDTO) {
        return Transacao.builder()
                .idTransacao(UUID.randomUUID())
                .dataCriacao(LocalDateTime.now())
                .idUsuario(requestDTO.idUsuario())
                .valor(requestDTO.valor())
                .tipoConta(requestDTO.tipoConta())
                .tipoTransacao(requestDTO.tipoTransacao())
                .statusTransacao(StatusTransacao.APROVADO).build();
    }

    public static TransacaoResponseDTO toResponse(Transacao transacao) {
        return TransacaoResponseDTO.builder()
                .idTransacao(transacao.getIdTransacao())
                .valor(transacao.getValor())
                .statusTransacao(transacao.getStatusTransacao())
                .dataCriacao(transacao.getDataCriacao())
                .tipoConta(transacao.getTipoConta())
                .build();
    }
}
