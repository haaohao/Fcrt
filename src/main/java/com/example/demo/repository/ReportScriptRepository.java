package com.example.demo.repository;

import com.example.demo.model.ReportScript;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReportScriptRepository extends JpaRepository<ReportScript, String> {

  @Query(name="select r from ReportScript r where r.isActive = ?1")
  List<ReportScript> findByIsActive(boolean active);

  Page<ReportScript> findByIsActive(boolean active, Pageable page);

  @Transactional
  void deleteById(String id);
}
