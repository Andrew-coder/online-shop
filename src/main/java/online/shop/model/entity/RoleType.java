package online.shop.model.entity;

/**
 * Created by andri on 1/3/2017.
 */
public enum RoleType {
    USER("user"),
    ADMIN("admin"),
    SALE_MANAGER("manager");

    private String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName(){
        return roleName;
    }

    public static RoleType getRole(String roleName){
        for(RoleType roleType:values()) {
            if (roleType.getRoleName().equals(roleName))
                return roleType;
        }
        return null;
    }
}
