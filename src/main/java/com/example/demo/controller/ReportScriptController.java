package com.example.demo.controller;

import com.example.demo.model.ReportScript;
import com.example.demo.repository.ReportScriptRepository;
import com.example.demo.utils.PageUtil;
import com.example.demo.utils.ResponseUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/fcrt/reportScript")
public class ReportScriptController {

  @Autowired
  private ReportScriptRepository reportScriptRepository;
  private final boolean  ACTIVE = true;

  @PostMapping(value = "/save")
  public ResponseEntity addReportScript(@RequestBody ReportScript reportScript) {
    reportScript.setActive(true);
    reportScriptRepository.save(reportScript);
    return ResponseUtil.success(getAllActiveReportScript());
  }

  @GetMapping(value = "/findAll")
  public ResponseEntity getAll(){
    return ResponseUtil.success(getAllActiveReportScript());
  }

  @PostMapping(value = "/uploadFile")
  public String uploadFile(MultipartFile file){
    return "success";
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity deleteById(@PathVariable String id){
    reportScriptRepository.deleteById(id);
    return ResponseUtil.success(getAllActiveReportScript());
  }

  @GetMapping(value = "/findAllWithConditionQuery")
  public ResponseEntity findAllWithConditionQuery(@RequestParam int pageSize, @RequestParam int pageIndex) {
    return ResponseUtil.success(reportScriptRepository.findByIsActive(ACTIVE, PageRequest.of(pageIndex - 1, pageSize)));
  }

  private List<ReportScript> getAllActiveReportScript() {
    return reportScriptRepository.findByIsActive(ACTIVE);
  }
}
