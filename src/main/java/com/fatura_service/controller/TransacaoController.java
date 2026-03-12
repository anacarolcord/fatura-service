package com.fatura_service.controller;

import com.fatura_service.dto.TransacaoRequestDTO;
import com.fatura_service.dto.TransacaoResponseDTO;
import com.fatura_service.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/todas")
    public List<TransacaoResponseDTO> listarTransacoesUsuario(@RequestBody TransacaoRequestDTO request){
        return transacaoService.listarTodasPorUsuario(request);
    }

    @GetMapping("/conta")
    public List<TransacaoResponseDTO> listarPorConta(@RequestBody TransacaoRequestDTO request){
        return transacaoService.listarPorUsuarioTipoConta(request);
    }

    @GetMapping("/datas")
    public List<TransacaoResponseDTO> listarPorData(@RequestBody TransacaoRequestDTO requestDTO,
                                                    @RequestParam LocalDateTime inicio,
                                                    @RequestParam LocalDateTime fim){
        return transacaoService.listarEntreDatas(requestDTO,inicio,fim);
    }


}
