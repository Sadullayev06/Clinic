package uz.isystem.Clinika.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("patients"))
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private LocalDate birthday;

    private String contact;

    private Integer age;

    private Boolean status;

    @Column(name = ("created_at"))
    private LocalDateTime createAt;

    @Column(name = ("update_at"))
    private LocalDateTime updateAt;
}
