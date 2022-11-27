package com.example.apigateway.model;

import lombok.*;
import org.bouncycastle.crypto.generators.BCrypt;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchant {

    @Id
    @GeneratedValue
    private UUID id;

    @Setter
    private String name;

    @Setter
    private String apiKey;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    @Setter
    @ToString.Exclude
    private Set<Credentials> credentials;

}
