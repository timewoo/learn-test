package collectionstest;

import java.util.*;

/**
 * @author yanglin
 * @date 2020/8/25 15:35
 */
public class ListCompare {

    private static final Integer local = 10000000;

    public static void main(String[] args) {
//        compareFirst();
//        compareLast();
//        compareMiddle();
//        List<String> list = Arrays.asList("foo","boo");
//        Object[] objects = list.toArray();
//        objects[0] = new Object();
//        System.out.println(Arrays.toString(objects));
//        Map map = new HashMap();
//        map.put()

        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4));
        for (int i = 0;i<list.size(); ++i) {
            if (list.get(i)==3){
                list.remove(i);
            }
        }
        System.out.println(list);
    }

    /**
     * 首部插入
     */
    public static void compareFirst() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < local; i++) {
            list.add(i);
        }
        // 首部插入
        long now = System.currentTimeMillis();
        list.add(0, 100001);
        System.out.println(System.currentTimeMillis() - now);
        System.out.println("--------------------------------------------------------");
        List<Integer> list1 = new ArrayList<>(10000);
        for (int i = 0; i < local; i++) {
            list1.add(i);
        }
        // 首部插入(扩容)
        long now1 = System.currentTimeMillis();
        list1.add(0, 100001);
        System.out.println(System.currentTimeMillis() - now1);
        System.out.println("--------------------------------------------------------");
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < local; i++) {
            linkedList.add(i);
        }
        // 首部插入
        long now2 = System.currentTimeMillis();
        linkedList.add(0, 100001);
        System.out.println(System.currentTimeMillis() - now2);
    }

    /**
     * 尾部插入
     */
    public static void compareLast() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < local; i++) {
            list.add(i);
        }
        // 尾部插入
        long now = System.currentTimeMillis();
        list.add(list.size() - 1, 100001);
        System.out.println(System.currentTimeMillis() - now);
        System.out.println("--------------------------------------------------------");
        List<Integer> list1 = new ArrayList<>(local);
        for (int i = 0; i < local; i++) {
            list1.add(i);
        }
        // 尾部插入(扩容)
        long now1 = System.currentTimeMillis();
        list1.add(list1.size() - 1, 100001);
        System.out.println(System.currentTimeMillis() - now1);
        System.out.println("--------------------------------------------------------");
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < local; i++) {
            linkedList.add(i);
        }
        // 尾部插入
        long now2 = System.currentTimeMillis();
        linkedList.add(linkedList.size() - 1, 100001);
        System.out.println(System.currentTimeMillis() - now2);
    }

    /**
     * 中部插入
     */
    public static void compareMiddle() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < local; i++) {
            list.add(i);
        }
        // 中部插入
        long now = System.currentTimeMillis();
        list.add(local / 2, 100001);
        System.out.println(System.currentTimeMillis() - now);
        System.out.println("--------------------------------------------------------");
        List<Integer> list1 = new ArrayList<>(local);
        for (int i = 0; i < local; i++) {
            list1.add(i);
        }
        // 中部插入(扩容)
        long now1 = System.currentTimeMillis();
        list1.add(local / 2, 100001);
        System.out.println(System.currentTimeMillis() - now1);
        System.out.println("--------------------------------------------------------");
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < local; i++) {
            linkedList.add(i);
        }
        // 中部插入
        long now2 = System.currentTimeMillis();
        linkedList.add(local / 2, 100001);
        System.out.println(System.currentTimeMillis() - now2);
    }

    public static void sort(List<Integer> list) {
        System.out.println("------------------------默认排序----------------------------");
        Collections.sort(list);
        System.out.println(list);
        System.out.println("------------------------定制排序----------------------------");
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(list);
    }
}
