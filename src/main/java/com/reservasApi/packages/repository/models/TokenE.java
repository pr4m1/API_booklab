package com.reservasApi.packages.repository.models;

import com.reservasApi.packages.repository.models.UserE;
import com.reservasApi.packages.security.SecurityConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="token")
public class TokenE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(unique = true)
    public String token;

    public String tokenType = SecurityConstants.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserE user;

    @Override
    public String toString(){
        return "{\n\t\"token\":\"" +this.token+"\"\n}";
    }
}
