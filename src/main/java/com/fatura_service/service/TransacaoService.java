package com.fatura_service.service;

import com.fatura_service.dto.TransacaoRequestDTO;
import com.fatura_service.dto.TransacaoResponseDTO;
import com.fatura_service.exceptions.TransacaoIncorretaException;
import com.fatura_service.model.Transacao;
import com.fatura_service.model.enums.StatusTransacao;
import com.fatura_service.model.enums.TipoConta;
import com.fatura_service.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TransacaoService {

      TransacaoRepository transacaoRepository;

    public TransacaoResponseDTO salvarTransacao(TransacaoRequestDTO requestDTO){
        TipoConta tipoConta = requestDTO.tipoConta();

        switch (tipoConta){
            case DEBITO -> {
                Transacao transacao = toTransacao(requestDTO);
                return toResponse(transacaoRepository.salvarTransacaoCredito(transacao));
            }
            case CREDITO -> {
                Transacao transacao = toTransacao(requestDTO);
                return toResponse(transacaoRepository.salvarTransacaoDebito(transacao));
            }
            case null -> {
                throw new TransacaoIncorretaException();
            }

        }

    }

    public List<TransacaoResponseDTO> listarTodasPorUsuario(TransacaoRequestDTO requestDTO){

        List<Transacao> transacoesUsuario =  transacaoRepository.findAllByIdUsuario(requestDTO.idUsuario());

        return transacoesUsuario.stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TransacaoResponseDTO> listarPorUsuarioTipoConta(TransacaoRequestDTO requestDTO){
        List<Transacao> transacoes = transacaoRepository
                .findAllByIdUsuarioAndTipoConta(requestDTO.idUsuario(), requestDTO.tipoConta());

        return transacoes.stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TransacaoResponseDTO> listarEntreDatas(TransacaoRequestDTO request, LocalDateTime inicio, LocalDateTime fim){

        List<Transacao> transacoesDatas = transacaoRepository.findByUsuarioAndDate(request.idUsuario(), inicio, fim);

        return transacoesDatas.stream()
                .map(this::toResponse)
                .toList();
    }

    public Transacao toTransacao(TransacaoRequestDTO requestDTO){
        return Transacao.builder()
                .dataCriacao(LocalDateTime.now())
                .idUsuario(requestDTO.idUsuario())
                .valor(requestDTO.valor())
                .tipoConta(requestDTO.tipoConta())
                .tipoTransacao(requestDTO.tipoTransacao())
                .statusTransacao(StatusTransacao.APROVADO).build();
    }

    public TransacaoResponseDTO toResponse(Transacao transacao){
        return TransacaoResponseDTO.builder()
                .idTransacao(transacao.getIdTransacao())
                .valor(transacao.getValor())
                .statusTransacao(transacao.getStatusTransacao())
                .dataCriacao(transacao.getDataCriacao())
                .build();

    }

}
