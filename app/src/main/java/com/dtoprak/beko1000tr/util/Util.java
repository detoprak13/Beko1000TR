package com.dtoprak.beko1000tr.util;

import com.dtoprak.beko1000tr.models.QrData;

public class Util {

    public static QrData qrParser (String qr) {
        if (qr.length() == 0) {
            return null;
        }
        QrData qrData = new QrData();
        while (qr.length() > 0) {
            String tag = qr.substring(0, 2);
            int sectionLength = 4 + Integer.parseInt(qr.substring(2, 4));
            String data = qr.substring(4, sectionLength);
            switch (tag) {
                case "00":
                    qrData.setPayloadFormatIndicator(data);
                    break;
                case "53":
                    qrData.setTransactionCurrency(Integer.parseInt(data));
                    break;
                case "54":
                    qrData.setTransactionAmount(Integer.parseInt(data));
                    break;
                case "80":
                    qrData.setQrVersion(data);
                    break;
                case "81":
                    qrData.setTransactionType(data);
                    break;
                case "82":
                    qrData.setReceiptDateTime(data);
                    break;
                case "83":
                    qrData.setReceiptId(data);
                    break;
                case "86":
                    qrData.setVatStr(data);
                    break;
                case "87":
                    qrData.setPosId(data);
                    break;
                case "89":
                    qrData.setBatchNumber(Integer.parseInt(data));
                    break;
                case "84":
                    qrData.setSessionId(data);
                    break;
                case "88":
                    qrData.setSecureQrSignature(data);
                    break;
            }
            qr = qr.substring(sectionLength);
        }

        return qrData;
    }
}
