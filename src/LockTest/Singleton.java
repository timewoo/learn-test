package LockTest;

/**
 * 单例模式
 * @author yanglin
 * @date 2020/9/1 20:49
 */
public class Singleton {

    private volatile static Singleton uniqueInstance;

    public Singleton() {
    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
