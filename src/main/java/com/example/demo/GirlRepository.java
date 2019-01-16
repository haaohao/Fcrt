package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GirlRepository extends JpaRepository<Girl, Integer> {

  //通过年龄来检查
  @Query("select u from Girl u where u.age = :age and u.id = :id")
  public List<Girl> findByAgeAndId(@Param("age") Integer age, @Param("id") Integer id);

  public List<Girl> findByIdAndAge(Integer id, Integer age);
}
