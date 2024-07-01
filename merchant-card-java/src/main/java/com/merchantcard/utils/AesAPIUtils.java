package com.merchantcard.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class AesAPIUtils {

    public static String encode(String data, String apiKey) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, HexUtil.decodeHex(apiKey));
        data = Base64.encode(data);
        return aes.encryptHex(data,CharsetUtil.CHARSET_UTF_8);
    }

    public static String decode(String encodeData, String apiKey) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, HexUtil.decodeHex(apiKey));
        return Base64.decodeStr(aes.decryptStr(encodeData,CharsetUtil.CHARSET_UTF_8));
    }

    public static void main(String[] arg) {

//        String aes = encode("123456","b635dd5c87f7bf73387929203321b1e1");
//        System.out.println(aes);
        System.out.println(decode("9758b59b8bb1e3ac80ac688311dde25d4d1074bf98988afa486d0cb8905882a27c0159241e314c82c4261a6aa81634208e8fe8a170c653fe52a7e97e2629d61409e2ad1a0392889497b4d626f6b2e2de3a10ae134985a317c89d7d7af4db61909c13567368ac3e6ce1ff6ed07ec82026d0d80be0d549724f9bff963aa77559f95b3b2e550c852b66c1cf1ffbae0a1a67a81e5dd7073f068ad50d28241117e185","8257d5ac1accba50047c00bab897f65e"));
    }
}
