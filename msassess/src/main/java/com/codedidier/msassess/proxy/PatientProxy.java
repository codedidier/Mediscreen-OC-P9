package com.codedidier.msassess.proxy;


import com.codedidier.msassess.model.PatientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="mspatient", url="${proxy.patient}")
public interface PatientProxy {

    @GetMapping("/api/patient/{id}")
    PatientModel getPatientById(@PathVariable long id);
}
