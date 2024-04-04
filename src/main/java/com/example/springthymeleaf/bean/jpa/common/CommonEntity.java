package com.example.springthymeleaf.bean.jpa.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class CommonEntity implements Serializable {
    // Delete flag
    @Column(name = "del_flg")
    @JsonProperty("delFlg")
    @JsonIgnore
    private String delFlg = "0";

    // Creation date
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    @JsonProperty("createDate")
    @JsonIgnore
    private Date createDate;

    // Creator ID
    @Column(name = "create_by", updatable = false)
    @JsonProperty("createBy")
    @JsonIgnore
    private Integer createBy;

    // Update date
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    @JsonProperty("updateDate")
    @JsonIgnore
    private Date updateDate;

    // Updater ID
    @Column(name = "update_by")
    @JsonProperty("updateBy")
    @JsonIgnore
    private Integer updateBy;

    @PrePersist
    protected void onCreate() {
        this.createBy = 1;
        this.updateBy = 1;
//        this.createDate = new Date();
//        this.updateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateBy = 2;
//        this.updateDate = new Date();
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}
