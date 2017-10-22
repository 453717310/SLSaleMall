package org.slsale.util;

/**
 * Created by dll on 2017/9/30.
 */
public class SQLTools {
        /**
         * mybaits 模糊查询防止sql注入（字符替换）
         * @param keyword
         * @return
         */
        public static String transfer(String keyword){
            if(keyword.contains("%") || keyword.contains("_")){
                keyword = keyword.replaceAll("\\\\", "\\\\\\\\")
                        .replaceAll("\\%", "\\\\%")
                        .replaceAll("\\_", "\\\\_");
            }
            return keyword;
        }
}
