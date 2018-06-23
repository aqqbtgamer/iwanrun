package com.iwantrun.core.service.application.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.iwantrun.core.service.application.enums.ReadyStatus;

@Entity
@Table(name="biz_order_message")
public class OrderMessage {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	
	@Column(name="order_id")
	private Integer orderId;
	
	@Column(name="message_text")
	private String messageText;
	
	@Column(name="message_from" ,nullable=false)
	private String messageFrom;
	
	
	@Column(name="message_reply")
	private String messageReply; 
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="already_ready")
	private Integer alreadyRead;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	public String getMessageReply() {
		return messageReply;
	}

	public void setMessageReply(String messageReply) {
		this.messageReply = messageReply;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getCreateTimeString() {
		if(createTime == null) {
			return null ;
		}else {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.createTime);
		}
	}

	public Integer getAlreadyRead() {
		return alreadyRead;
	}

	public void setAlreadyRead(Integer alreadyRead) {
		this.alreadyRead = alreadyRead;
	}

	public String getAlreadyReadyString() {
		if(this.alreadyRead !=  null) {
			ReadyStatus status =  ReadyStatus.matchById(this.alreadyRead);
			if(status != null) {
				return status.getName();
			}else {
				return null ;
			}
		}else {
			return null;
		}
	}

}
