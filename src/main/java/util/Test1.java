package util;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test1 {

    /**
     * 将整数转换为4字节的字节数组（与Perl的 pack("L") 等效）
     *
     * @param value 整数值
     * @return 4字节的字节数组
     */
    private static byte[] intToByteArray(int value) {
        return new byte[]{
            (byte) (value & 0xFF),
            (byte) ((value >> 8) & 0xFF),
            (byte) ((value >> 16) & 0xFF),
            (byte) ((value >> 24) & 0xFF)
        };
    }

    /**
     * 将4字节的字节数组转换为整数（与Perl的 unpack("L") 等效）
     *
     * @param bytes 4字节的字节数组
     * @return 解码后的整数
     */
    private static int byteArrayToInt(byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException("Invalid byte array length for decoding pageId");
        }
        return (bytes[3] & 0xFF) << 24 |
            (bytes[2] & 0xFF) << 16 |
            (bytes[1] & 0xFF) << 8 |
            (bytes[0] & 0xFF);
    }

    /**
     * 根据给定的 pageId 生成短链接字符串
     */
    public static String generateShortLink(String pageId) {
        // 将 pageID 转换为字节数组并进行 Base64 编码
        String tinyString = Base64.getEncoder().encodeToString(intToByteArray(Integer.parseInt(pageId)));

        // 替换 Base64 编码中的特殊字符，生成 URL 安全的字符串
        StringBuilder actualTinyString = new StringBuilder();
        for (char c : tinyString.toCharArray()) {
            if (c == '=') {
                continue; // 跳过填充字符 '='
            }
            if (c == '/') {
                actualTinyString.append('-'); // 替换 '/' 为 '-'
            } else if (c == '+') {
                actualTinyString.append('_'); // 替换 '+' 为 '_'
            } else if (c == '\n') {
                actualTinyString.append('/'); // 替换换行符为 '/'
            } else {
                actualTinyString.append(c); // 其他字符保持不变
            }
        }
        return actualTinyString.toString();
    }

    /**
     * 根据给定的短链接字符串逆向生成 pageId
     */
    public static String decodeShortLink(String shortLink) {
        // 将URL安全字符替换回Base64标准字符
        shortLink = shortLink.replaceAll("-", "/").replaceAll("_", "+");
        // Base64解码
        byte[] decodedBytes = Base64.getDecoder().decode(shortLink);
        // 将字节数组转换为整数（还原pageId）
        return String.valueOf(byteArrayToInt(decodedBytes));
    }

    /**
     */
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("aN9oDw", "258531176");
        map.put("Fp_GCw", "193371926");
        map.put("V-B1Cg", "175501399");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String shortLink = entry.getKey();
            String pageId = entry.getValue();
            String decodedPageId = decodeShortLink(shortLink);
            if (Objects.equals(pageId, decodedPageId)) {
                System.out.println("短链接: " + shortLink + " 解码成功，pageId: " + decodedPageId);
            } else {
                System.out.println("短链接: " + shortLink + " 解码失败，期望 pageId: " + pageId + " 实际 pageId: " + decodedPageId);
            }
        }
    }

}
