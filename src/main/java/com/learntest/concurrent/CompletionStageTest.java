package com.learntest.concurrent;

import com.learntest.resilience4jtest.exception.MyException;

import javax.print.attribute.Size2DSyntax;
import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.concurrent.CompletableFuture;

/**
 * @author yanglin
 * @date 2020/11/27 13:56
 */
public class CompletionStageTest {

    public static void main(String[] args) {
//        thenApply();
//        thenCombine();
//        applyToEither();
//        thenAccept();
//        thenAcceptBoth();
//        acceptEither();
//        thenRun();
//        runAfterBoth();
//        runAfterEither();
//        thenCompose();
//        whenComplete();
//        handle();
        exceptionally();
    }

    public static void thenApply() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "sssss");
        String join = stringCompletableFuture.thenApply(s -> s + "xxxxxxxxxx").join();
        System.out.println(join);
    }

    public static void thenCombine() {
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "sssssssssssss";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "fffffffffffff";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(join);
    }

    public static void applyToEither() {
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "sssssssssssss";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "fffffffffffff";
        }), s -> s + "   ").join();
        System.out.println(join);
    }

    public static void thenAccept() {
        CompletableFuture.supplyAsync(() -> "sssssssss").thenAccept(s -> System.out.println(s + "kkkkkkkkkkkk"));
    }

    public static void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ssssssssss";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "kkkkkkkkkk";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));
        while (true) {
        }
    }

    public static void acceptEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "sssssssssss";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "kkkkkkkkkk";
        }), System.out::println);
        while (true) {
        }
    }

    public static void thenRun() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ssssssssssssss";
        }).thenRun(() -> System.out.println("rrrrrrrrrrrrrrrr"));
        while (true) {
        }
    }

    public static void runAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "sssssssssssss";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "rrrrrrrrrrrrrrrr";
        }), () -> System.out.println("ffffffffffff"));
        while (true) {
        }
    }

    public static void runAfterEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "sssssssssssss";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "rrrrrrrrrrrrrrrr";
        }), () -> System.out.println("ffffffffffff"));
        while (true) {
        }
    }

    public static void thenCompose() {
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ssssssssss";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "cccccccccccc";
        })).join();
        System.out.println(join);
    }

    public static void whenComplete() {
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new MyException("异常");
            }
            return "ssssssssssss";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "wwwwwwwwwwwwww";
        }), (s1, s2) -> {
            String s = s1 + s2;
            System.out.println(s);
            return s;
        }).whenComplete((s, t) -> {
            if (t != null) {
                System.out.println("出现异常");
                t.printStackTrace();
            }
        }).join();
        System.out.println(join);
    }

    public static void handle(){
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new MyException("异常");
            }
            return "ssssssssssss";
        }).handle((s, t) -> {
            if (t != null) {
                return "hhhhhhhhhhhhh";
            }
            return s;
        }).join();
        System.out.println(join);
    }

    public static void exceptionally(){
        String join = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new MyException("异常");
            }
            return "ssssssssssss";
        }).exceptionally(e -> {
            e.printStackTrace();
            return "eeeeeeeeeeeeeeeeee";
        }).join();
        System.out.println(join);
    }
}
