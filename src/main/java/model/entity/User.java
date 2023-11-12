package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.enums.ExceptionEnum;
import model.exception.ApplicationException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@NamedQueries({
        @NamedQuery(name = "findAllUsers",query = "select u from users u"),
        @NamedQuery(name = "findUserByUsername",query = "select u from users u where u.username = :username"),
        @NamedQuery(name = "findUserByFinNumber",query = "select u from users u where u.finNumber = :finNumber"),
        @NamedQuery(name = "findUserByEmail",query = "select u from users u where u.accountEmail = :accountEmail")
})
@Data
@Builder
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence",allocationSize = 75)
    private long id;
    @Column(name = "user_name", nullable = false, length = 15)
    private String name;
    @Column(name = "user_surname", nullable = false, length = 20)
    private String surname;
    @Column(name = "user_birth_date", nullable = false, updatable = false)
    private LocalDate birthdate;
    @Column(name = "fin_number", nullable = false, updatable = false, unique = true)
    private String finNumber;
    @Column(name = "address", nullable = false, length = 25)
    private String address;
    @Column(name = "username", nullable = false, length = 10, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "account_email", nullable = false, updatable = false, unique = true)
    private String accountEmail;
    @Column(name = "user_balance", nullable = false)
    private BigDecimal userBalance;
    @Builder.Default
    @Column(name = "user_status", nullable = false)
    private boolean status = true;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Cart cart;

    public User(){
        setName(name);
        setSurname(surname);
        setBirthdate(birthdate);
        setFinNumber(finNumber);
        setUsername(username);
        setPassword(password);
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setSurname(String surname) {
        this.surname = surname.toUpperCase();
    }

    public void setBirthdate(LocalDate birthdate) {
        int age = Period.between(birthdate, LocalDate.now()).getYears();
        if (age > 18) {
            this.birthdate = birthdate;
        } else {
            throw new ApplicationException(ExceptionEnum.UNDER_AGE_EXCEPTION);
        }
    }

    public void setFinNumber(String finNumber){
        if(finNumber.length() == 7){
            this.finNumber = finNumber.toUpperCase();
        }else throw  new ApplicationException(ExceptionEnum.FIN_NOT_FOUND);
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void setPassword(String password) throws ApplicationException {
        if (password.length() >= 9) {
            this.password = password;
        }
        else throw new ApplicationException(ExceptionEnum.INCORRECT_PASSWORD_LENGTH);
    }
}
