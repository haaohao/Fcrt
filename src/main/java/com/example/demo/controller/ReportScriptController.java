package com.example.demo.controller;

import com.example.demo.model.ReportScript;
import com.example.demo.model.searchModel.modelForSearch.BaseQuery;
import com.example.demo.repository.ReportScriptRepository;
import com.example.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    return ResponseUtil.success(reportScriptRepository.findByIsActive("1"));
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity getAll(){
    return ResponseUtil.success(reportScriptRepository.findByIsActive("1"));
  }

  @PostMapping(value = "/uploadFile")
  public String uploadFile(MultipartFile file){
    return "success";
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity deleteById(@PathVariable String id){
    reportScriptRepository.deleteById(id);
    return ResponseUtil.success(reportScriptRepository.findByIsActive("1"));
  }

  @PostMapping(value = "/findAllWithConditionQuery")
  public ResponseEntity findAllWithConditionQuery(@RequestBody BaseQuery query) {
    return ResponseUtil.success(reportScriptRepository.findByIsActive("1", query.getPageable()));
  }
}
