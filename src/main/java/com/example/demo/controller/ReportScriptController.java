package com.example.demo.controller;

import com.example.demo.model.ReportScript;
import com.example.demo.model.searchModel.modelForSearch.BaseQuery;
import com.example.demo.repository.ReportScriptRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/fcrt/reportScript")
public class ReportScriptController {

  @Autowired
  private ReportScriptRepository reportScriptRepository;

  @PostMapping(value = "/save")
  public ResponseEntity addReportScript(@RequestBody ReportScript reportScript) {
    reportScript.setIsActive("1");
    reportScriptRepository.save(reportScript);
    Result<Object> result = new Result<>(reportScriptRepository.findByIsActive("1"));
    result.setCode("SUCCESS");
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping(value = "/findAll")
  public List<ReportScript> getAll(){
    return reportScriptRepository.findByIsActive("1");
  }

  @PostMapping(value = "/uploadFile")
  public String uploadFile(MultipartFile file){
    return "success";
  }

  @DeleteMapping(value = "/delete/{id}")
  public List<ReportScript> deleteById(@PathVariable String id){
    reportScriptRepository.deleteById(id);
    return reportScriptRepository.findByIsActive("1");
  }

  @PostMapping(value = "/findAllWithConditionQuery")
  public ResponseEntity findAllWithConditionQuery(@RequestBody BaseQuery query) {
    Result<Object> result = new Result<>(reportScriptRepository.findByIsActive("1", query.getPageable()));
    result.setCode("SUCCESS");
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
