import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yanglin
 * @date 2020/8/25 15:35
 */
public class ListCompare {

    private static final Integer local = 10000000;

    public static void main(String[] args) {
        compareFirst();
    }

    /**
     * 首部插入
     */
    public static void compareFirst(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < local; i++) {
            list.add(i);
        }
        // 首部插入
        long now = System.currentTimeMillis();
        list.add(0,100001);
        System.out.println(System.currentTimeMillis()-now);
        System.out.println("--------------------------------------------------------");
        List<Integer> list1 = new ArrayList<>(10000);
        for (int i = 0; i < local; i++) {
            list1.add(i);
        }
        // 首部插入(扩容)
        long now1 = System.currentTimeMillis();
        list1.add(0,100001);
        System.out.println(System.currentTimeMillis()-now1);
        System.out.println("--------------------------------------------------------");
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < local; i++) {
            linkedList.add(i);
        }
        // 首部插入
        long now2 = System.currentTimeMillis();
        linkedList.add(0,100001);
        System.out.println(System.currentTimeMillis()-now2);
    }
}
