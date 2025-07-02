package nrabello.back.core.domain.enums;

public enum StatusTaskEnum {

    TO_DO("To do"), IN_PROGRESS("In progress"), PAUSED("Paused"), DONE("Done");

    private final String description;

    StatusTaskEnum(String description) {
        this.description = description;
    }

    public String getDescricao() {
        return description;
    }
}
