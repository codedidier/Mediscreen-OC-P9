package com.codedidier.mspatient.controllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.codedidier.mspatient.entity.Patient;
import com.codedidier.mspatient.repository.PatientRepository;
import com.codedidier.mspatient.service.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {
    @Autowired
    public MockMvc mvc;

    @Autowired
    PatientServiceImpl patientServiceImpl;

    @Autowired
    PatientRepository patientRepository;


    static Patient getPrenomTest() {
        Patient patient = new Patient();
        patient.setId(1l);
        patient.setFirstName("PrenomTest");
        patient.setLastName("NomTest");
        patient.setDateOfBirth(LocalDate.of(2000, 01, 01));
        patient.setGender('M');
        patient.setAddress("1 Ma RUE");
        patient.setPhone("06-06-06-06-06");
        return patient;
    }


    @Test
    void home() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        is("Welcome to MsPatient API !")));
    }

    @Test
    void allPatients() throws Exception {
        patientRepository.deleteAll();
        Patient patient = getPrenomTest();
        patientRepository.save(patient);
        mvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName",
                        is("PrenomTest")));
    }

    @Test
    void getPatientById() throws Exception {
        patientRepository.deleteAll();
        Patient patient = getPrenomTest();
        patientRepository.save(patient);
        mvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",
                        is("PrenomTest")));
    }

    @Test
    void getPatientByIdException() throws Exception {
        patientRepository.deleteAll();
        mvc.perform(get("/188"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$",
                        is("this id : 188, not found in database")));
    }

    @Test
    void savePatient() throws Exception {
        patientRepository.deleteAll();
        String newPatient = "{"
                    +"\"firstName\" : \"PrenomTest\","
                    +"\"lastName\" : \"NomTest\","
                    +"\"dateOfBirth\" : \"2000-01-01\","
                    +"\"gender\" : \"M\","
                    +"\"address\" : \"1 Ma Rue\","
                    +"\"phone\": \"06-06-06-06-06\"}";

        mvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPatient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("PrenomTest")));
    }

    @Test
    void updatePatient() throws Exception {
        patientRepository.deleteAll();
        Patient patient = getPrenomTest();
        patient.setId(80l);
        Patient save = patientRepository.save(patient);
        String updatePatient = "{"
                +"\"firstName\" : \"PrenomTest2\","
                +"\"lastName\" : \"NomTest\","
                +"\"dateOfBirth\" : \"2000-01-01\","
                +"\"gender\" : \"F\","
                +"\"address\" : \"1 Ma Rue\","
                +"\"phone\": \"06-06-06-06-06\"}";

        mvc.perform(put("/update/"+save.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePatient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("PrenomTest2")));
    }
}
