package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_site_message")
public class SiteMessage {

	public static final String HAS_READ = "1";
	public static final String NO_READ = "1";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // '系统主键'

    @Column(name="from_user" ,nullable=false)
    private String fromUser ;

    @Column(name="sendto_user" ,nullable=false)
    private String sendtoUser ;

    @Column(name="message_text" ,nullable=false)
    private String messageText;

    @Column(name="order_no",nullable=false)
    private String orderNo;

    @Column(name="create_time")
    private Date createTime;

	@Column(name = "is_read", nullable = false)
	private String isRead;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getSendtoUser() {
        return sendtoUser;
    }

    public void setSendtoUser(String sendtoUser) {
        this.sendtoUser = sendtoUser;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public boolean isBlRead() {
		return HAS_READ.equals(isRead);
	}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

}
