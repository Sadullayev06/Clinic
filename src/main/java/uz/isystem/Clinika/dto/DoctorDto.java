package uz.isystem.Clinika.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDto {
    private Integer id;
    @NotBlank(message = ("Ismda xatolik"))
    private String name;
    @NotBlank(message = ("Familiyasida xatolik togri kirit"))
    private String surname;
    @NotBlank(message = ("Yonalishda xatolik"))
    private String direction;
    @NotBlank(message = ("Kontaktni togri kirit"))
    @Size(min=12,max = 13,message = ("Contactni togri kirit 12-13 oraligida"))
    private String contact;
    private Integer experience;
    private Boolean status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
