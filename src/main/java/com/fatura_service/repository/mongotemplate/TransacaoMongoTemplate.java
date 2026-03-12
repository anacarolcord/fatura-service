package com.fatura_service.repository.mongotemplate;

import com.fatura_service.model.Transacao;
import com.fatura_service.model.enums.TipoConta;
import com.fatura_service.repository.TransacaoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;


public abstract class TransacaoMongoTemplate implements TransacaoRepository {

      final MongoTemplate mongoTemplate;

    protected TransacaoMongoTemplate(MongoTemplate mongoTemplate) {
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

        return mongoTemplate.find(query, Transacao.class);
    }

    @Override
    public List<Transacao> findAllByIdUsuarioAndTipoConta(Long idUsuario, TipoConta tipoConta){

        Query query = Query.query(Criteria.where("idUsuario").is(idUsuario).and("tipoConta").is(tipoConta));

        return mongoTemplate.find(query, Transacao.class);
    }

    @Override
    public List<Transacao> findByUsuarioAndDate(Long idUsuario, LocalDateTime dataInicio, LocalDateTime dataFim){


        Query query = Query.query(Criteria.where("idUsuario").is(idUsuario)
                .and("dataCriacao").gte(dataInicio).lte(dataFim));

        return mongoTemplate.find(query, Transacao.class);
    }






}
