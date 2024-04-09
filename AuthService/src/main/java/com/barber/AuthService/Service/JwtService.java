package com.barber.AuthService.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

    //watch if the token is expired or not
    private boolean IsExpired (String jwt){
        return ExtracExpirationDate(jwt).before(new Date());
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

    // In this way I generated a token with the possibility to add extraClaims
    public String GenerateToken (Map<String, Object> ExtraClaim , UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(ExtraClaim)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+450000000))
                .setSubject(userDetails.getUsername())
                .signWith(GetSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // with this way I generated a token without the possibility to add extraClaims
    public String GenerateToken (UserDetails userDetails){
        return GenerateToken(new HashMap<>(), userDetails);
    }

    //
    public boolean TokenIsValid (UserDetails userDetails, String jwt){
        return (!IsExpired(jwt) && ExtracEmail(jwt).equals(userDetails.getUsername()));
        }









}
