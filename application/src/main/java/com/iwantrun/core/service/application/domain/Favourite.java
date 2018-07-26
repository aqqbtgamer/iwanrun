package com.iwantrun.core.service.application.domain;

import javax.persistence.*;

@Entity
@Table(name="biz_favourite")
public class Favourite {
    /**
     * 收藏: id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id ;

    /**
     * 分类类型：产品，场地，案例，...
     */
    @Column(name="user_id")
    private String userId ;

    /**
     * 分类类型：产品，场地，案例，...
     */
    @Column(name="case_type")
    private String caseType ;

    /**
     * 分类：caseID
     */
    @Column(name="case_id",nullable=false)
    private Integer caseId ;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCaseType() {
        return caseType;
    }
    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public int getCaseId() {
        return id;
    }
    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }
}
