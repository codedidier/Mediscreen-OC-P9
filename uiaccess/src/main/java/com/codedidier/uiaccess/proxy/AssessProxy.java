package com.codedidier.uiaccess.proxy;

import com.codedidier.uiaccess.model.AssessModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name="msassess", url="${proxy.assess}")
public interface AssessProxy {

    @GetMapping("/api/assess/{id}")
    AssessModel getAssessById(@PathVariable long id);
}
