package com.example.demo.repository;

import com.example.demo.model.ReportScript;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReportScriptRepository extends JpaRepository<ReportScript, String> {
  List<ReportScript> findByIsActive(String s);

  Page<ReportScript> findByIsActive(String s, Pageable page);

  @Transactional
  void deleteById(String id);
}
