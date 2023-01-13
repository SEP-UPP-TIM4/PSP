package com.qrcodeservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    private UUID id;

    @Setter
    private String url;

    @Setter
    private String merchantId;

    @Setter
    @OneToOne
    private Bank bank;

    @Setter
    @OneToOne
    private Transaction transaction;


    @Setter
    private Long acquirerOrderId;

    @Setter
    private LocalDateTime acquirerTimestamp;
}
