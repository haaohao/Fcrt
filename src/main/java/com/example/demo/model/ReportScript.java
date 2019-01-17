package com.example.demo.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="report_script")
public class ReportScript {
  @Id
  @GeneratedValue(generator="base-uuid")
  @GenericGenerator(name="base-uuid", strategy="uuid")
  private String id;

  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "content")
  private String content;
  @Column(name = "is_active")
  private boolean isActive;
  @Column(name = "script_type")
  private String scriptType;
  @Column(name = "create_by")
  private Date createBy;
  @Column(name = "update_by")
  private String updateBy;
  @Column(name = "update_Date")
  private Date updateDate;
  @Column(name = "create_Date")
  private Date createDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean getActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public String getScriptType() {
    return scriptType;
  }

  public void setScriptType(String scriptType) {
    this.scriptType = scriptType;
  }

  public Date getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Date createBy) {
    this.createBy = createBy;
  }

  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }
}
