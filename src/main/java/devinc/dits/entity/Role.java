package devinc.dits.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    @Column
    private String name;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "user_2_role", joinColumns = @JoinColumn(name = "roleId"), inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> userList;

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> professionList) {
        this.userList = professionList;
    }
}
