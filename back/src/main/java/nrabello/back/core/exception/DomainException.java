package nrabello.back.core.exception;

public class DomainException extends RuntimeException {

    private final String code;

    public DomainException(String message, String code) {
        super(message);
        this.code = code;
    }

    public static DomainException userAlreadyExists() {
        return new DomainException("Usuário já existe.", "USR-01");
    }

    public static DomainException userInvalid() {
        return new DomainException("Usuário inválido.", "USR-02");
    }

    public static DomainException roleNotFound() {
        return new DomainException("A role não existe.", "ROL-01");
    }

    public static DomainException userOrganizationNotFound() {
        return new DomainException("A organização não existe ou você não está vinculado a ela.", "UOR-01");
    }
}
