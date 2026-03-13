package com.fatura_service.controller;

import com.fatura_service.api.TransacaoApi;
import com.fatura_service.dto.TransacaoRequestDTO;
import com.fatura_service.dto.TransacaoResponseDTO;
import com.fatura_service.model.enums.TipoConta;
import com.fatura_service.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacoes")
public class TransacaoController implements TransacaoApi {

    private final TransacaoService transacaoService;

    @Override
    public TransacaoResponseDTO salvarTransacao(TransacaoRequestDTO request) {
        return transacaoService.salvarTransacao(request);
    }

    @Override
    public List<TransacaoResponseDTO> listarTransacoesUsuario(Long idUsuario) {
        return transacaoService.listarTodasPorUsuario(idUsuario);
    }

    @Override
    public List<TransacaoResponseDTO> listarPorConta(Long idUsuario, TipoConta tipoConta) {
        return transacaoService.listarPorUsuarioTipoConta(idUsuario, tipoConta);
    }

    @Override
    public List<TransacaoResponseDTO> listarPorData(Long idUsuario, LocalDateTime inicio, LocalDateTime fim) {
        return transacaoService.listarEntreDatas(idUsuario, inicio, fim);
    }
}