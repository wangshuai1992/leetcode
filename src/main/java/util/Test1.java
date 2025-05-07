package util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test1 {

    /**
     * 根据给定的 pageId 生成短链接字符串
     */
    public static String generateShortLink(String pageId) {
        // 将整数转为小端序字节数组
        byte[] bytes = ByteBuffer.allocate(4)
                .order(ByteOrder.LITTLE_ENDIAN)
                .putInt(Integer.parseInt(pageId))
                .array();
        // Base64编码并替换字符
        return Base64.getEncoder().encodeToString(bytes)
                .replace('/', '-')
                .replace('+', '_')
                .replaceAll("[=A]+$", ""); // 移除末尾的填充字符
    }

    /**
     * 根据给定的短链接字符串逆向生成 pageId
     */
    public static String decodeShortLink(String shortLink) {
        // 填充到8字符并用A补位
        String padded = String.format("%-8s", shortLink).replace(' ', 'A');
        // 还原Base64特殊字符
        String base64Str = padded.replace('-', '/').replace('_', '+');
        // 补全等号填充
        int padding = (4 - (base64Str.length() % 4)) % 4;
        for (int i = 0; i < padding; i++) {
            base64Str += "=";
        }
        // 解码字节数组
        byte[] decoded = Base64.getDecoder().decode(base64Str);
        // 小端序转换回整数
        return String.valueOf(ByteBuffer.wrap(decoded)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getInt());
    }

    /**
     */
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("aN9oDw", "258531176");
        map.put("Fp_GCw", "193371926");
        map.put("V-B1Cg", "175501399");
        map.put("24GJE", "277447131");
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
