////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.parser.Feature;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import spark.Spark;
//import spark.servlet.SparkApplication;
//
//public class IndexFilter implements SparkApplication {
//    public IndexFilter() {
//    }
//
//    public void init() {
//        Spark.get("/", (var0, var1) -> {
//            return "Hello World";
//        });
//        Spark.post("/", (var0, var1) -> {
//            String var2 = var0.body();
//            var2.replace("@type","");
//            JSONObject var3 = JSON.parseObject(var2, new Feature[]{Feature.SupportNonPublicField});
//            JSONObject var4 = new JSONObject();
//            var4.put("success", 200);
//            Object var10002 = var3.get("name");
//            var4.put("data", "this is your " + var10002 + ",you age  " + var3.get("age"));
//            var1.status(200);
//            var1.type("application/json");
//            return var4.toJSONString();
//        });
//        Spark.get("/check", (var0, var1) -> {
//            File var2 = new File("/tmp/exploit.html");
//            if (!var2.exists()) {
//                return "NO exists";
//            } else {
//                BufferedReader var3 = new BufferedReader(new FileReader("/tmp/exploit.html"));
//                String var4 = var3.readLine();
//                return var4;
//            }
//        });
//    }
//
//    public static void main(String[] var0) {
//        IndexFilter var1 = new IndexFilter();
//        var1.init();
//    }
//}
