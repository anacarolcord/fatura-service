package com.fatura_service.repository.mongotemplate;

import com.fatura_service.model.Transacao;
import com.fatura_service.model.enums.TipoConta;
import com.fatura_service.repository.TransacaoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public  class TransacaoMongoTemplateImpl implements TransacaoRepository {

      final MongoTemplate mongoTemplate;

    protected TransacaoMongoTemplateImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Transacao salvarTransacaoCredito(Transacao transacao){
        return mongoTemplate.save(transacao, "FATURA");
    }

    @Override
    public Transacao salvarTransacaoDebito(Transacao transacao){
        return mongoTemplate.save(transacao,"EXTRATO");
    }

    @Override
    public List<Transacao> findAllByIdUsuario(Long idUsuario){
        Query query = Query.query(Criteria.where("idUsuario").is(idUsuario));

        List <Transacao> transacoesCredito = mongoTemplate.find(query, Transacao.class, "FATURA");
        List <Transacao> transacoesDebito = mongoTemplate.find(query, Transacao.class, "EXTRATO");

        List<Transacao> todasTransacoes = new ArrayList<>();
        todasTransacoes.addAll(transacoesCredito);
        todasTransacoes.addAll(transacoesDebito);

        return todasTransacoes;

    }

    @Override
    public List<Transacao> findAllByIdUsuarioAndTipoConta(Long idUsuario, TipoConta tipoConta){
        Query query = Query.query(Criteria.where("idUsuario").is(idUsuario).and("tipoConta").is(tipoConta));

        if (Objects.equals(tipoConta,TipoConta.CREDITO)){
            return mongoTemplate.find(query, Transacao.class, "FATURA");
        }else{
            return mongoTemplate.find(query, Transacao.class, "EXTRATO");
        }
    }

    @Override
    public List<Transacao> findByUsuarioAndDate(Long idUsuario, LocalDateTime dataInicio, LocalDateTime dataFim){


        Query query = Query.query(Criteria.where("idUsuario").is(idUsuario)
                .and("dataCriacao").gte(dataInicio).lte(dataFim));

        List <Transacao> transacoesCredito = mongoTemplate.find(query, Transacao.class, "FATURA");
        List <Transacao> transacoesDebito = mongoTemplate.find(query, Transacao.class, "EXTRATO");

        List<Transacao> todasTransacoes = new ArrayList<>();
        todasTransacoes.addAll(transacoesCredito);
        todasTransacoes.addAll(transacoesDebito);

        return todasTransacoes;
    }






}
