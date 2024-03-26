package com.standard.security.utils;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Slf4j
public class HashUtils {

	private HashUtils() {
	}

	public static StringBuilder hashString(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.reset();
			byte[] buffer = input.getBytes(StandardCharsets.UTF_8);
			md.update(buffer);
			byte[] digest = md.digest();

			StringBuilder hexStr = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				hexStr.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
			}
			return hexStr;
		}
		catch (Exception e) {
			log.error("error in hashString", e);
			return null;
		}
	}


}