package com.qrcodeservice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qrcodeservice.dto.NewQrCodeDto;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

@Service
public class QrCodeService {

    public BufferedImage generateQrCode(NewQrCodeDto qrCodeDto, int size) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(generateQrCodeString(qrCodeDto), BarcodeFormat.QR_CODE, size, size, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }


    private String generateQrCodeString(NewQrCodeDto qrCodeDto){
        String qrCodeString = "";
        if(!qrCodeDto.getK().equals(""))
            qrCodeString += "K:" + qrCodeDto.getK();
        if(!qrCodeDto.getV().equals(""))
            qrCodeString += "|V:" + qrCodeDto.getV();
        if(!qrCodeDto.getC().equals(""))
            qrCodeString += "|C:" + qrCodeDto.getC();
        if(!qrCodeDto.getR().equals(""))
            qrCodeString += "|R:" + qrCodeDto.getR();
        if(!qrCodeDto.getN().equals(""))
            qrCodeString += "|N:" + qrCodeDto.getN();
        if(!qrCodeDto.getI().equals(""))
            qrCodeString += "|I:" + qrCodeDto.getI();
        if(!qrCodeDto.getP().equals(""))
            qrCodeString += "|P:" + qrCodeDto.getP();
        if(!qrCodeDto.getSf().equals(""))
            qrCodeString += "|SF:" + qrCodeDto.getSf();
        if(!qrCodeDto.getS().equals(""))
            qrCodeString += "|S:" + qrCodeDto.getS();
        if(!qrCodeDto.getRo().equals(""))
            qrCodeString += "|RO:" + qrCodeDto.getRo();

        return qrCodeString;
    }
}
