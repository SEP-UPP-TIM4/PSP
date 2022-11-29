package com.example.authservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String username;        // MERCHANT_ID

    @Setter
    private String password;        // MERCHANT_PASSWORD

    @ManyToOne
    @JoinColumn
    private PaymentMethod paymentMethod;
}
