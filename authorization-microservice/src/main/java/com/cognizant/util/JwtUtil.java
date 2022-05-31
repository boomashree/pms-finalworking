package com.cognizant.util;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



//import com.cts.authorization.exception.InvalidTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtUtil {
	
	 private String secret_key = "secret";

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	    private Claims extractAllClaims(String token) {
	    	Claims claims;
	    	try {
	    		claims = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
	    	}
	    	catch (ExpiredJwtException e) { // caught first cause it has higher precedence
				throw new com.cognizant.exception.InvalidTokenException("Token Expired");
			} 
	    	catch (MalformedJwtException e) {
				throw new com.cognizant.exception.InvalidTokenException("Malformed Token");
	    	}
    	
			return claims;
	    	
	    }
	


	    private Boolean isTokenExpired(String token) {
	    	
	         return extractExpiration(token).before(new Date());
	        	 
	         }
	         
	         
	    
	    
	    //generating token using the user name

	    public String generateToken(String username) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, username);
	    }

	    //create token based on HS256 algorithm using the secret key
	    private String createToken(Map<String, Object> claims, String subject) {

	        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
	                .signWith(SignatureAlgorithm.HS256, secret_key).compact();
	    }

	    //validating generated jwt token
	    
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

}
