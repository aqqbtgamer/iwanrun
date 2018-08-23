package com.iwantrun.core.service.application.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="biz_trade_status")
public class TradeStatus {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // '系统主键'

    @Column(name = "user_info", nullable = false)
    private String userInfo;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "modify_time")
    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}