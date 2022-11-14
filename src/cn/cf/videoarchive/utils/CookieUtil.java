package cn.cf.videoarchive.utils;

import javax.servlet.http.Cookie;

public class CookieUtil {
    /**
     * 根据cookie的key，从一堆cookie中取出你要的cookie
     *
     * @param key     cookie的key
     * @param cookies 要寻找指定cookie的cookie数组
     * @return 找到的cookie，或者NULL
     */
    public static Cookie getCookieByKey(String key, Cookie[] cookies) {
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals(key)) {
                return cookie;
            }
        }
        return null;
    }
}
