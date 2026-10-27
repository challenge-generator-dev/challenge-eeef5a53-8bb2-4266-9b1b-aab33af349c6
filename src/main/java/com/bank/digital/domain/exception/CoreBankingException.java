package com.bank.digital.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Excepción personalizada para fallos del core bancario.
 * Se lanza cuando el servicio de core bancario no responde o retorna errores.
 */
public class CoreBankingException extends RuntimeException {
    
    private final String errorCode;
    private final UUID correlationId;
    private final LocalDateTime timestamp;
    private final String serviceName;
    private final String operation;
    
    public CoreBankingException(String message) {
        super(message);
        this.errorCode = "CORE_BANKING_ERROR";
        this.correlationId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.serviceName = "CoreBankingService";
        this.operation = "UNKNOWN";
    }
    
    public CoreBankingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.correlationId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.serviceName = "CoreBankingService";
        this.operation = "UNKNOWN";
    }
    
    public CoreBankingException(String message, Throwable cause, String operation) {
        super(message, cause);
        this.errorCode = "CORE_BANKING_ERROR";
        this.correlationId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.serviceName = "CoreBankingService";
        this.operation = operation;
    }
    
    public CoreBankingException(String message, Throwable cause, String errorCode, String operation) {
        super(message, cause);
        this.errorCode = errorCode;
        this.correlationId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.serviceName = "CoreBankingService";
        this.operation = operation;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public UUID getCorrelationId() {
        return correlationId;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public String getOperation() {
        return operation;
    }
    
    @Override
    public String toString() {
        return String.format(
            "CoreBankingException{errorCode='%s', correlationId=%s, timestamp=%s, serviceName='%s', operation='%s', message='%s'}",
            errorCode, correlationId, timestamp, serviceName, operation, getMessage()
        );
    }
}