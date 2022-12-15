package cn.cf.videoarchive.utils;

public class WebUtils {
        /**
     * 将字符串转换成为int类型的数据
     * @param strInt 想要转成数字的字符串
     * @param defaultValue 转换失败时的默认值
     * @return 转换后的数字
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
            System.err.println("WebUtils：没有传入数字，返回默认值：" + defaultValue);
        }
        return defaultValue;
    }

}
