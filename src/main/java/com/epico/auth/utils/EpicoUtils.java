package com.epico.auth.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class EpicoUtils {
	
	
	public static String getJWT(String username, String storeCode) {
		
		int validezInMin =  30; // 1440;
		int validezInSeconds =  60;
		String key = "cpMW1RjvLE41Ra3GwMZYTLhyMwZNesxo";
		String emisorToken = "https://www.google.com";
		
		int audience = 1;
		
		
		Date time = new Date(System.currentTimeMillis());
		List<GrantedAuthority> grandAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		// ALMACENA INFORMACION EN EL TOKEN
		HashMap<String, Object> clain = new HashMap<>();
		clain.put("username", username);
		clain.put("storeCode", storeCode);
		
		String token = Jwts
				.builder()
				.setIssuer(emisorToken)
				.setAudience(String.valueOf(audience))
				.setId(MD5(username + time))
				.setIssuedAt(time)
				.setNotBefore(time)
				.setExpiration(new Date(System.currentTimeMillis() + (validezInSeconds * 10000)))
				.claim("data", clain)
				.claim("roles", grandAuthorities
						.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.signWith(SignatureAlgorithm.HS256, key.getBytes()).compact()
				;
		
		
		return "Bearer " + token;
	}
	
	
	public static String MD5(String md5) {
		
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0; i < array.length; i++) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			System.out.println("EpicoUtils-MD5-Error: " + e.getMessage());
		}
		
		return null;
	}

}
