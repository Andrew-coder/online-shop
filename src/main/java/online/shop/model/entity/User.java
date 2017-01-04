package online.shop.model.entity;

import java.util.Date;

/**
 * Created by andri on 1/1/2017.
 */
public class User {
    private String name;
    private String surname;
    private String email;
    private Date birthDate;
    private boolean worker;
    private RoleType role;

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setWorker(boolean worker) {
        this.worker = worker;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public boolean isWorker(){
        return worker;
    }

    public static class Builder{
        User instance = new User();

        public User setName(String name){
            instance.name = name;
            return instance;
        }

        public User setSurname(String surname){
            instance.surname = surname;
            return instance;
        }

        public User setEmail(String email){
            instance.email = email;
            return instance;
        }

        public User setBirthDate(Date birthDate){
            instance.birthDate = birthDate;
            return instance;
        }

        public User setWorker(boolean worker){
            instance.worker = worker;
            return instance;
        }

        public User setRole(RoleType role){
            instance.role = role;
            return instance;
        }

        public User build(){
            return instance;
        }
    }
}
