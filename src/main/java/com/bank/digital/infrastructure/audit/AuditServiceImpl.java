package com.bank.digital.infrastructure.audit;

import com.bank.digital.domain.ports.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public void logOperation(String operationType, String entityId, String details) {
        logger.info("Registrando operación en auditoría: tipo={}, entidad={}", operationType, entityId);

        AuditEntry entry = new AuditEntry(
                UUID.randomUUID(),
                operationType,
                entityId,
                details,
                LocalDateTime.now(),
                "SYSTEM"
        );

        try {
            auditLogRepository.save(entry);
            logger.debug("Entrada de auditoría persistida: {}", entry.id());
        } catch (Exception e) {
            logger.error("Error al persistir entrada de auditoría: {}", e.getMessage());
            throw new RuntimeException("Error al registrar operación en auditoría", e);
        }
    }

    @Override
    public void logOperation(String operationType, String entityId, String details, String userId) {
        logger.info("Registrando operación en auditoría: tipo={}, entidad={}, usuario={}", 
                operationType, entityId, userId);

        AuditEntry entry = new AuditEntry(
                UUID.randomUUID(),
                operationType,
                entityId,
                details,
                LocalDateTime.now(),
                userId
        );

        try {
            auditLogRepository.save(entry);
            logger.debug("Entrada de auditoría persistida: {}", entry.id());
        } catch (Exception e) {
            logger.error("Error al persistir entrada de auditoría: {}", e.getMessage());
        }
    }

    public record AuditEntry(UUID id, String operationType, String entityId, 
                              String details, LocalDateTime timestamp, String userId) {}

    public interface AuditLogRepository {
        void save(AuditEntry entry);
    }
}