package com.codedidier.msassess.proxy;

import com.codedidier.msassess.model.HistoryModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="mshistory", url="${proxy.history}")
public interface HistoryProxy {

    @GetMapping("/api/note/all/{id}")
    List<HistoryModel> allNotes(@PathVariable long id);
}
