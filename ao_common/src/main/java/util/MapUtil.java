package util;

import exception.AoException;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    public static Map createMap(String[] key,Object[] value){
        if(key.length==value.length){
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < key.length; i++) {
                map.put(key[i], value[i]);
            }
            return map;
        }else {
            throw new AoException("参数错误");
        }
    }
}
