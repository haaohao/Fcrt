package com.example.demo;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GirlController {

  @Autowired
  private GirlRepository girlRepository;

  /**
   * 查询所有女生列表
   */

  //通过年龄查询女生列表
  @GetMapping(value = "/girls/age")
  public List<Girl> girlListByAge(@RequestParam("age") Integer age, @RequestParam("id") Integer id) {
    return girlRepository.findByAgeAndId(age, id);
  }

  //通过年龄查询女生列表
  @GetMapping(value = "/girls/dd/age/{age}/id/{id}")
  public List<Girl> girlListByIdAndAge(@PathVariable("age") Integer age, @PathVariable("id") Integer id) {
    return girlRepository.findByIdAndAge(id, age);
  }
  @GetMapping(value = "/girls")
  public List<Girl> girlList() {
    return girlRepository.findAll();
  }

  /**
   * 添加一个女生
   */
  @PostMapping(value = "/girls")
  public Girl girlAdd(@RequestParam("cupSize") String cupSize,
      @RequestParam("age") Integer age) {
    Girl girl = new Girl();
    girl.setCupSize(cupSize);
    girl.setAge(age);
    return girlRepository.save(girl);
  }


  @GetMapping(value = "/girls/{id}")
  public Optional<Girl> girlFindOne(@PathVariable("id") Integer id) {
    return girlRepository.findById(id);
  }

  //更新
  @PutMapping(value = "/girls/{id}")
  public Girl girlUpdate(@PathVariable("id") Integer id,
      @RequestParam("cupSize") String cupSize,
      @RequestParam("age") Integer age) {
    Girl girl = new Girl();
    girl.setId(id);
    girl.setCupSize(cupSize);
    girl.setAge(age);
    return girlRepository.save(girl);
  }

  //删除
  @DeleteMapping(value = "/girls/{id}")
  public void girlDelete(@PathVariable("id") Integer id) {
    girlRepository.deleteById(id);
  }
//
}
