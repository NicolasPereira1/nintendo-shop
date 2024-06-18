package nintendo.shop.role;

import javax.persistence.Table;

@Table(name = "roles")
public enum RoleEnum {
    ADMIN, USER;
}
