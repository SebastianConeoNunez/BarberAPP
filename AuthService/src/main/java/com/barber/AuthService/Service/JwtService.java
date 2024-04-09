package com.barber.AuthService.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SecretKey = "AAAAAAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBCCCCCCCCCCC";



    public  <T> T ExtracSingleClaim(String jwt , Function<Claims,T> ClaimResolver){
        final Claims claims = ExtracAllClaims(jwt);
        return ClaimResolver.apply(claims);
    }
    public String ExtracEmail(String jwt) {
        return ExtracSingleClaim(jwt,Claims::getSubject);
    }

    public Date ExtracExpirationDate(String jwt){
        return ExtracSingleClaim(jwt,Claims::getExpiration);
    }

    private Claims ExtracAllClaims (String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(GetSigningKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key GetSigningKey(){
        byte[] keyByte = Decoders.BASE64.decode(SecretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }


}
