package com.api.base;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenUtil {
	private final long minuteMillis = 60L * 1000L;
	private final String issuer = "localhost";
	private final String subject = "api token";
	
	public SecretKey getSecretKey() {
		String algorithm = "HmacSHA256";
		String key = UUID.randomUUID().toString();
		key = Base64Utils.encodeToUrlSafeString(key.getBytes());
		SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(new String(key)), algorithm);
		return secretKey;
	}
	public String generateToken(){return generateToken(minuteMillis);}
	public String generateToken(long expirationOffsetMillis){
		final long now = System.currentTimeMillis();
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer(issuer)
				.setIssuedAt(new Date(now))
				.setNotBefore(new Date(now - minuteMillis))
				.setExpiration(new Date(now + expirationOffsetMillis))
				.signWith(getSecretKey())
				.compact();
	}
	public boolean verifyToken(String token){
		try{
			final Jws<Claims> jws = parseToken(token);
			final String tokenSubject = jws.getBody().getSubject();
			final String tokenIssuer = jws.getBody().getIssuer();
			if(StringUtils.hasText(tokenSubject) && StringUtils.hasText(tokenIssuer)){
				return tokenSubject.equals(this.subject) && tokenIssuer.equals(this.issuer);
			}
		}catch(Exception e){
			log.error("Invalidate token: "+token, e);
		}
		return false;
	}
	public Jws<Claims> parseToken(String token){
		return Jwts.parserBuilder().setSigningKey(getSecretKey())
			.build().parseClaimsJws(token);
	}
}
