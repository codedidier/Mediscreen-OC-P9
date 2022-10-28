package com.codedidier.uiaccess.proxy;

import com.codedidier.uiaccess.model.PatientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient( name="mspatient", url="${proxy.patient}")
public interface PatientProxy {

    @GetMapping("/api/patient/{id}")
    PatientModel getPatientById(@PathVariable long id);

    @GetMapping("/api/patient/all")
    List<PatientModel> allPatients();
    @PostMapping("/api/patient/add")
    PatientModel savePatient(@RequestBody @Valid PatientModel patient);

    @PutMapping("api/patient/update/{id}")
    PatientModel updatePatient(@PathVariable long id, @RequestBody @Valid PatientModel patient);

    @GetMapping("/api/patient/all")
    PatientModel allPatientsList(String converterToModel);
}
