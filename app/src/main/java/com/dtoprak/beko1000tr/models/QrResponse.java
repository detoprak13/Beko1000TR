package com.dtoprak.beko1000tr.models;

public class QrResponse {
    public String returnCode, returnDesc, QRdata;

    public QrResponse(String returnCode, String returnDesc, String QRdata) {
        this.returnCode = returnCode;
        this.returnDesc = returnDesc;
        this.QRdata = QRdata;
    }
}
