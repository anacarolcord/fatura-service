package com.fatura_service.model;

import com.fatura_service.model.enums.StatusTransacao;
import com.fatura_service.model.enums.TipoConta;
import com.fatura_service.model.enums.TipoTransacao;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Transacao {

    @Id
      UUID idTransacao;
      Long idUsuario;
      BigDecimal valor;
      LocalDateTime dataCriacao;
      TipoTransacao tipoTransacao;
      TipoConta tipoConta;
      StatusTransacao statusTransacao;


}
