package MyTreeMap;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapDemo {
    public static void main(String[] args) {

    Map<String,Integer>  mailList = new TreeMap<>();
        mailList.put("B", 119);
        mailList.put("C",119);
        mailList.put("A",119);
        mailList.put("E", 119);
        System.out.println(mailList);

        Set<String> keys = mailList.keySet();
        for (String key:keys) {
            Integer value = mailList.get(key);
            System.out.println(key + "=" + value);
        }

        Set<Map.Entry<String,Integer>>  entries = mailList.entrySet();
        for(Map.Entry<String,Integer> e:entries){
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + "=" + value);
        }
    }
}
