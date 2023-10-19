package JVM;

/**
 * @author yanglin
 * @date 2020/9/8 21:31
 */
public class JvmTest {

    public static void main(String[] args) {
        Integer s = 1;
        change(s);
        System.out.println(s);
    }

    public static void change(Integer s){
        s = 2;
    }
}
