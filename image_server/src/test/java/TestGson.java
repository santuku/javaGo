import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class TestGson {
    public static void main(String[] args) {
        HashMap<String,Object> data = new HashMap<>();
        //Gson除了支持HashMap这样的容器类，也支普通类
        data.put("name","mulan");
        data.put("character1","tough");
        data.put("character2","brave");
        data.put("character3","humorous");
        data.put("character4","sympathetic");

        Gson gson = new GsonBuilder().create();
//        Gson提供了两种创建对象的方式：
//        直接使用Gson构造方法创建；
//        Gson gson = new Gson();
//        使用GsonBuilder创建；
//        Gson gson = new GsonBuilder().create();
//        相比直接使用构造方法，GsonBuilder创建的方式更灵活，因为它支持对Gson的配置。

        String str = gson.toJson(data);
        System.out.println(data);
        //　Gson提供了fromJson() 和toJson() 两个直接用于解析和生成的方法，前者实现反序列化，后者实现了序列化；同时每个方法都提供了重载方法
        //输出顺序不重要
    }
}
