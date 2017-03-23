package online.shop.model.entity;

import java.util.Date;

/**
 * Created by andri on 1/1/2017.
 */
public class User extends BaseEntity{
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date birthDate;
    private RoleType role;

    public User(String name, String surname, String email, String password, Date birthDate, RoleType role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
    }

    public User(int id, String name, String surname, String email, String password, Date birthDate, RoleType role) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public RoleType getRole() {
        return role;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getName().equals(user.getName())) return false;
        if (!getSurname().equals(user.getSurname())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getBirthDate().equals(user.getBirthDate())) return false;
        return getRole() == user.getRole();

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getBirthDate().hashCode();
        result = 31 * result + getRole().hashCode();
        return result;
    }

    public static class Builder{
        User instance = new User();

        public Builder setId(int id){
            instance.setId(id);
            return this;
        }

        public Builder setName(String name){
            instance.name = name;
            return this;
        }

        public Builder setSurname(String surname){
            instance.surname = surname;
            return this;
        }

        public Builder setEmail(String email){
            instance.email = email;
            return this;
        }

        public Builder setPassword(String password){
            instance.password=password;
            return this;
        }

        public Builder setBirthDate(Date birthDate){
            instance.birthDate = birthDate;
            return this;
        }

        public Builder setRole(RoleType role){
            instance.role = role;
            return this;
        }

        public User build(){
            return instance;
        }
    }
}
