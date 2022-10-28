package com.codedidier.uiaccess.dto;

import com.codedidier.uiaccess.confirm.ConfirmBirthDate;
import com.codedidier.uiaccess.confirm.ConfirmGender;
import com.codedidier.uiaccess.model.PatientModel;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class PatientDto {

    private long id;

    @NotBlank(message = "Veuillez entrer le prénom du patient.")
    private String firstName;

    @NotBlank (message = "Veuillez entrer le nom du patient.")
    private String lastName;

    @ConfirmBirthDate
    private String dateOfBirth;

    @ConfirmGender
    private String gender;

    @NotBlank(message = "Veuillez entrer l'adresse du patient.")
    private String address;

    private String phone;

    public PatientDto() {
    }

    public PatientDto(long id, String firstName, String lastName, String dateOfBirth, String gender, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public static PatientModel converterToModel(PatientDto dto) {
        LocalDate date = LocalDate.parse(dto.dateOfBirth);
        char gender = dto.getGender().charAt(0);
        return new PatientModel(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                date,
                gender,
                dto.getAddress(),
                dto.getPhone()
        );
    }
    public static PatientDto converterToDto(PatientModel bean) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new PatientDto(
                bean.getId(),
                bean.getFirstName(),
                bean.getLastName(),
                bean.getDateOfBirth().format(formatter),
                String.valueOf(bean.getGender()),
                bean.getAddress(),
                bean.getPhone()
        );
    }
}
