package JVM;

/**
 * @author yanglin
 * @date 2020/9/8 21:31
 */
public class JvmTest {

    public static void main(String[] args) {
        byte[] allocation1,allocation2;
        allocation1 = new byte[125800*1024];
        allocation2 = new byte[2000*1024];
        if (allocation1==allocation2);
        System.out.println(true);
    }
}
