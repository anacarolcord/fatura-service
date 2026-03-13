package com.fatura_service.api;

import com.fatura_service.dto.TransacaoRequestDTO;
import com.fatura_service.dto.TransacaoResponseDTO;
import com.fatura_service.model.enums.TipoConta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Transações", description = "API para gerenciamento de transações")
public interface TransacaoApi {

    @Operation(summary = "Salvar nova transação", description = "Cria uma nova transação no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    TransacaoResponseDTO salvarTransacao(
            @Valid @RequestBody TransacaoRequestDTO request
    );

    @Operation(summary = "Listar todas as transações do usuário",
            description = "Retorna todas as transações de um usuário específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transações retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/todas/{idUsuario}")
    List<TransacaoResponseDTO> listarTransacoesUsuario(
            @Parameter(description = "ID do usuário", required = true)
            @PathVariable Long idUsuario
    );

    @Operation(summary = "Listar transações por tipo de conta",
            description = "Retorna transações de um usuário filtradas por tipo de conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transações retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Tipo de conta inválido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/conta/{idUsuario}")
    List<TransacaoResponseDTO> listarPorConta(
            @Parameter(description = "ID do usuário", required = true)
            @PathVariable Long idUsuario,

            @Parameter(description = "Tipo da conta (ex: CORRENTE, POUPANCA)", required = true)
            @RequestParam TipoConta tipoConta
    );

    @Operation(summary = "Listar transações por período",
            description = "Retorna transações de um usuário em um intervalo de datas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transações retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Formato de data inválido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/datas/{idUsuario}")
    List<TransacaoResponseDTO> listarPorData(
            @Parameter(description = "ID do usuário", required = true)
            @PathVariable Long idUsuario,

            @Parameter(description = "Data de início (formato: dd-MM-yyyy HH:mm:ss)",
                    example = "01-01-2024 00:00:00", required = true)
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime inicio,

            @Parameter(description = "Data de fim (formato: dd-MM-yyyy HH:mm:ss)",
                    example = "31-12-2024 23:59:59", required = true)
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime fim
    );
}