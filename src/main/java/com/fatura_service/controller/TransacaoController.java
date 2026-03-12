package com.fatura_service.controller;

import com.fatura_service.dto.TransacaoRequestDTO;
import com.fatura_service.dto.TransacaoResponseDTO;
import com.fatura_service.model.enums.TipoConta;
import com.fatura_service.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public TransacaoResponseDTO salvarTransacao(@RequestBody TransacaoRequestDTO request){
        return transacaoService.salvarTransacao(request);
    }

    @GetMapping("/todas/{idUsuario}")
    public List<TransacaoResponseDTO> listarTransacoesUsuario(@PathVariable Long idUsuario){
        return transacaoService.listarTodasPorUsuario(idUsuario);
    }

    @GetMapping("/conta/{idUsuario}")
    public List<TransacaoResponseDTO> listarPorConta(@PathVariable Long idUsuario,
                                                     @RequestParam TipoConta tipoConta){
        return transacaoService.listarPorUsuarioTipoConta(idUsuario,tipoConta);
    }

    @GetMapping("/datas/{idUsuario}")
    public List<TransacaoResponseDTO> listarPorData(@PathVariable Long idUsuario,
                                                    @RequestParam @DateTimeFormat LocalDateTime inicio,
                                                    @RequestParam @DateTimeFormat LocalDateTime fim){
        return transacaoService.listarEntreDatas(idUsuario,inicio,fim);
    }


}
