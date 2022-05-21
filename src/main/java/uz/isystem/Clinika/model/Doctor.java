package uz.isystem.Clinika.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("doctor"))
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String direction;

    private String contact;

    private Integer experience;

    private Boolean status;

    @Column(name = ("created_at"))
    private LocalDateTime createAt;

    @Column(name = ("update_at"))
    private LocalDateTime updateAt;

}
