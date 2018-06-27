package com.iwantrun.core.service.application.domain;

/**
 * 用户安全问题
 * 
 * @author user
 *
 */
public class SafetyQuestion {
	/**
	 * 问题
	 */
	private String question;
	/**
	 * 答案
	 */
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
