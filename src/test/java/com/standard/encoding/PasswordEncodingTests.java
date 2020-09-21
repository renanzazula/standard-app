package com.standard.encoding;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.springframework.util.DigestUtils;

public class PasswordEncodingTests {


    static final String PASSWORD = "standard";
//
//    @Test
//    void testBcrypt15() {
//        PasswordEncoder bcrypt = new BCryptPasswordEncoder(10);
//
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode("standard"));
//
//    }
//
//    @Test
//    void testBcrypt() {
//        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
//
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode("guru"));
//
//    }
//
//    @Test
//    void testSha256() {
//        PasswordEncoder sha256 = new StandardPasswordEncoder();
//
//        System.out.println(sha256.encode(PASSWORD));
//        System.out.println(sha256.encode(PASSWORD));
//    }
//
//    @Test
//    void testLdap() {
//        PasswordEncoder ldap = new LdapShaPasswordEncoder();
//        System.out.println(ldap.encode(PASSWORD));
//        System.out.println(ldap.encode(PASSWORD));
//        System.out.println(ldap.encode("tiger"));
//        String encodedPwd = ldap.encode(PASSWORD);
//
//        Assert.assertTrue(ldap.matches(PASSWORD, encodedPwd ));
//
//    }
//
//    @Test
//    void testNoOp() {
//        PasswordEncoder noOp = NoOpPasswordEncoder.getInstance();
//
//        System.out.println(noOp.encode(PASSWORD));
//    }
//
//    @Test
//    void hashingExample() {
//        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
//        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
//
//        String salted = PASSWORD + "ThisIsMySALTVALUE";
//        System.out.println(DigestUtils.md5DigestAsHex(salted.getBytes()));
//    }
}
