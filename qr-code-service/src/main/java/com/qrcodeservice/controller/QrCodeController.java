package com.qrcodeservice.controller;

import com.google.zxing.WriterException;
import com.qrcodeservice.dto.NewQrCodeDto;
import com.qrcodeservice.service.QrCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.util.Optional;

@RestController
@RequestMapping("/api/qr/v1")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @PostMapping(value = {"/gen", "/gen/{size}"}, produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(value= HttpStatus.OK)
    public BufferedImage generateQRCode(@Valid @RequestBody NewQrCodeDto qrCodeDto, @PathVariable(name = "size") Optional<Integer> size) throws WriterException {
        if(!size.isPresent())
            return qrCodeService.generateQrCode(qrCodeDto, 250);
        return qrCodeService.generateQrCode(qrCodeDto, size.get());
    }
}
