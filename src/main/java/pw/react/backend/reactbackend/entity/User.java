package pw.react.backend.reactbackend.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="Users")
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "date_of_birth")
    private Date date_of_birth;
    @Column(name = "is_active")
    private Boolean is_active;

    public User(){}

    public User(String login, String first_name, String last_name,Date birth,Boolean is_active) {
        this.login = login;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = birth;
        this.is_active = is_active;
    }

    public String ToString()
    {
        return "id: " +this.getId() +"\n login: " + this.getLogin()+"\n first name: "
                + this.getFirst_name()+"\n last name: " + this.getLast_name()+
                "\n date of birth: " + this.getDate_of_birth()+"\n active : " + this.getActive();
    }

    public long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getFirst_name() { return first_name; }
    public String getLast_name() {
        return last_name;
    }
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public Boolean getActive() { return is_active; }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}

