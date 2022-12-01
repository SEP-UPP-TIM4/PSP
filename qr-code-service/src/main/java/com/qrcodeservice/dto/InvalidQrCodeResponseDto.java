package com.qrcodeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidQrCodeResponseDto {
    private String code;
    private List<String> reason = new ArrayList<>();
}
