package com.fatura_service.repository;

import com.fatura_service.model.Transacao;
import com.fatura_service.model.enums.StatusTransacao;
import com.fatura_service.model.enums.TipoConta;
import com.fatura_service.model.enums.TipoTransacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransacaoRepository {

    Transacao salvarTransacaoCredito(Transacao transacao);
    Transacao salvarTransacaoDebito(Transacao transacao);
    List<Transacao> findAllByIdUsuario(Long idUsuario);
    List<Transacao> findAllByIdUsuarioAndTipoConta(Long idUsuario, TipoConta tipoConta);
    List<Transacao> findByUsuarioAndDate(Long idUsuario, LocalDateTime dataInicio, LocalDateTime dataFim);

    //    List<Transacao> findAllByTipoTransacao(TipoTransacao tipoTransacao);
//    List<Transacao> findAllByStatusTransacao(StatusTransacao statusTransacao);
//    List<Transacao> findAllByTipoConta(TipoConta tipoConta);


}
