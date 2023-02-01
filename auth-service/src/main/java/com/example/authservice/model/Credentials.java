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

    @ManyToOne
    @JoinColumn(name = "bank_id")
    @Setter
    private Bank bank;

    public Credentials(Credentials credentials, String encryptedPassword){
        this.id = credentials.getId();
        this.username = credentials.getUsername();
        this.password = encryptedPassword;
        this.paymentMethod = credentials.getPaymentMethod();
        this.bank = credentials.getBank();
    }

}
