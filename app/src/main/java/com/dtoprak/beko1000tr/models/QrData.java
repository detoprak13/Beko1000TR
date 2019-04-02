package com.dtoprak.beko1000tr.models;

public class QrData {
    private String payloadFormatIndicator, qrVersion, transactionType,receiptDateTime, receiptId,
    vatStr, posId, sessionId, secureQrSignature, asSting;
    private int transactionCurrency, transactionAmount, batchNumber;

    public QrData() {

    }

    public String getAsSting() {
        return asSting;
    }

    public void setAsSting(String asSting) {
        this.asSting = asSting;
    }

    public String getPayloadFormatIndicator() {
        return payloadFormatIndicator;
    }

    public void setPayloadFormatIndicator(String payloadFormatIndicator) {
        this.payloadFormatIndicator = payloadFormatIndicator;
    }

    public String getQrVersion() {
        return qrVersion;
    }

    public void setQrVersion(String qrVersion) {
        this.qrVersion = qrVersion;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getReceiptDateTime() {
        return receiptDateTime;
    }

    public void setReceiptDateTime(String receiptDateTime) {
        this.receiptDateTime = receiptDateTime;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getVatStr() {
        return vatStr;
    }

    public void setVatStr(String vatStr) {
        this.vatStr = vatStr;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSecureQrSignature() {
        return secureQrSignature;
    }

    public void setSecureQrSignature(String secureQrSignature) {
        this.secureQrSignature = secureQrSignature;
    }

    public int getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(int transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Override
    public String toString() {
        return "QrData{" +
                "payloadFormatIndicator='" + payloadFormatIndicator + '\'' +
                ", qrVersion='" + qrVersion + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", receiptDateTime='" + receiptDateTime + '\'' +
                ", receiptId='" + receiptId + '\'' +
                ", vatStr='" + vatStr + '\'' +
                ", posId='" + posId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", secureQrSignature='" + secureQrSignature + '\'' +
                ", transactionCurrency=" + transactionCurrency +
                ", transactionAmount=" + transactionAmount +
                ", batchNumber=" + batchNumber +
                '}';
    }
}
