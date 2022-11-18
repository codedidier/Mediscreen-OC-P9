package com.codedidier.uiaccess.proxy;

import com.codedidier.uiaccess.model.HistoryModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name="MsHistory", url="${proxy.history}")
public interface HistoryProxy {

    @GetMapping("/api/note/all/{id}")
    List<HistoryModel> allNotes(@PathVariable long id);

    @GetMapping("api/note/{id}")
    HistoryModel getNoteById(@PathVariable String id);


    @PostMapping("api/note/add")
    HistoryModel saveNote(@RequestBody @Valid HistoryModel note);

    @PutMapping("api/note/update/{id}")
    HistoryModel updateNote(@PathVariable String id, @RequestBody @Valid HistoryModel note);

}
