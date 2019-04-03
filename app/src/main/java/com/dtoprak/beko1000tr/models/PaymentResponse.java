package com.dtoprak.beko1000tr.models;

public class PaymentResponse {
	public String applicationId, sessionId, posId, returnCode, returnDesc;

	public PaymentResponse(String applicationId, String sessionId, String posId, String returnCode, String returnDesc) {
		this.applicationId = applicationId;
		this.sessionId = sessionId;
		this.posId = posId;
		this.returnCode = returnCode;
		this.returnDesc = returnDesc;
	}
}
