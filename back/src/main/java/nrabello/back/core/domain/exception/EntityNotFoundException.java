package nrabello.back.core.domain.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final String entityType;

    private final Object entityId;

    public EntityNotFoundException(String entityType, Object entityId) {
        super(String.format("Entidade '%s' com id '%s' não encontrada", entityType, entityId));
        this.entityType = entityType;
        this.entityId = entityId;
    }

    public static EntityNotFoundException usuarioNaoEncontrado(Object id){
        return new EntityNotFoundException("Usuário", id);
    }

    public static EntityNotFoundException TaskNaoEncontrada(Object id){
        return new EntityNotFoundException("Task", id);
    }

    public static EntityNotFoundException statusTaskNaoEncontrada(Object id){
        return new EntityNotFoundException("StatusTask", id);
    }
}
