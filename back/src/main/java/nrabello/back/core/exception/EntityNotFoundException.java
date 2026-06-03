package nrabello.back.core.exception;

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

    public static EntityNotFoundException TaskNaoEncontrada(Object id){
        return new EntityNotFoundException("Task", id);
    }

    public static EntityNotFoundException statusTaskNaoEncontrada(Object id){
        return new EntityNotFoundException("StatusTask", id);
    }

    public static EntityNotFoundException usuarioNaoEncontrado(Object id){
        return new EntityNotFoundException("Usuario", id);
    }

    public static EntityNotFoundException organizacaoNaoEncontrada(Object id){
        return new EntityNotFoundException("Organization", id);
    }

    public static EntityNotFoundException projetoNaoEncontrado(Object id){
        return new EntityNotFoundException("Project", id);
    }
}
