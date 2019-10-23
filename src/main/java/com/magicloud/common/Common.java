package com.magicloud.common;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Common {

    /** 
     * 获取一个四位随机数，并且四位数不重复 
     *  
     * @return Set<Integer> 
     */  
    public static String GetRandomNumber() {  
        // 使用SET以此保证写入的数据不重复  
        Set<Integer> set = new HashSet<Integer>();  
        // 随机数  
        Random random = new Random();  
          
        while (set.size() < 20) {  
            // nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）  
            // 和指定值（不包括）之间均匀分布的 int 值。  
            set.add(random.nextInt(10));  
        }  
        String ss = "0";
        for(Integer s : set){
        	ss = ss + s.toString();
        }
        return ss;  
    }  
}
