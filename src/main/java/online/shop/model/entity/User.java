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
    private boolean worker;
    private RoleType role;

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


    public boolean isWorker(){
        return worker;
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

        public Builder setWorker(boolean worker){
            instance.worker = worker;
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
