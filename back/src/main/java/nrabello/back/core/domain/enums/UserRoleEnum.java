package nrabello.back.core.domain.enums;

public enum UserRoleEnum {

    admin("admin"), user("user");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
