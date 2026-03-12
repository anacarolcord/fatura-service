package com.fatura_service.exceptions;

public class TransacaoIncorretaException extends RuntimeException {
    public TransacaoIncorretaException() {
        super("Transação incorreta, faltam dados");
    }
}
