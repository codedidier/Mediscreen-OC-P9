package com.codedidier.mspatient.serviceTest;

import com.codedidier.mspatient.entity.Patient;
import com.codedidier.mspatient.repository.PatientRepository;
import com.codedidier.mspatient.service.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PatientServiceImplTest {
    PatientRepository patientRepository = mock(PatientRepository.class);

    PatientServiceImpl classUnderTest = new PatientServiceImpl(patientRepository);

    @Test
    void getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        when(patientRepository.findAll()).thenReturn(patients);

        classUnderTest.getAllPatients();
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void getPatientById() {
        Patient expectedPatient = new Patient();
        expectedPatient.setId(4);
        when(patientRepository.findById(4l)).thenReturn(java.util.Optional.of(expectedPatient));

        Patient actualPatient = classUnderTest.getPatientById(4l);

        assertEquals(expectedPatient.getId(), actualPatient.getId());


    }

    @Test
    void savePatient() {
        Patient expectedPatient = new Patient();
        expectedPatient.setId(8l);
        expectedPatient.setFirstName("PrenomTest");

        when(patientRepository.save(expectedPatient)).thenReturn(expectedPatient);

        Patient actualPatient = classUnderTest.savePatient(expectedPatient);

        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    void updatePatient() {
        Patient existingPatient = new Patient();
        existingPatient.setId(88l);
        existingPatient.setFirstName("PrenomTest");
        when(patientRepository.findById(88l)).thenReturn(java.util.Optional.of(existingPatient));
        Patient expectedPatient = new Patient();
        expectedPatient.setId(88l);
        expectedPatient.setFirstName("PrenomTest2");
        when(patientRepository.save(expectedPatient)).thenReturn(expectedPatient);

        Patient actualPatient = classUnderTest.updatePatient(88l, expectedPatient);

        assertEquals(actualPatient.getFirstName(),"PrenomTest2");

    }
}
