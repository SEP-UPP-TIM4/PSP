package com.qrcodeservice.controller;

import com.qrcodeservice.dto.NewQRCodeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qr/v1")
public class QrCodeController {

    @PostMapping("/generate")

    public ResponseEntity<String> generateQRCode(@RequestBody NewQRCodeDto qrCodeDto){
        System.out.println("Pogodioooooooooo!!!!!!!");
        return ResponseEntity.ok(qrCodeDto.getK());
    }
}
