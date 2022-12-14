package com.psp.creditcardservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    /*@Setter
    private Long paymentId;*/

    @Setter
    private Long acquirerOrderId;

    @Setter
    private LocalDateTime acquirerTimestamp;
}
