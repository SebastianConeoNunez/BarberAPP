package com.barber.AuthService.Service;

import com.barber.AuthService.model.Role;
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
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SecretKey = "AAAAAAA1321321AAAAAAAAAAA13112321AAAAAAAAABBBBBBBBBBBBBa12131BBBBBBBBBBBBCCCCCCCCCCC";



    public  <T> T ExtracSingleClaim(String jwt , Function<Claims,T> ClaimResolver){
        final Claims claims = ExtracAllClaims(jwt);
        return ClaimResolver.apply(claims);
    }
    public String ExtracEmail(String jwt) {
        return ExtracSingleClaim(jwt,Claims::getSubject);
    }


    public String ExtracRole(String jwt) {
        return ExtracSingleClaim(jwt, claims -> {
            String role = claims.get("Role", String.class);
            return role;
        });
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
    public String GenerateToken (Map<String,Object> ExtraClaim , UserDetails userDetails){
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

    //check if token is expired or not
    public boolean TokenIsValid (UserDetails userDetails, String jwt){
        return (!IsExpired(jwt) && ExtracEmail(jwt).equals(userDetails.getUsername()));
        }









}
