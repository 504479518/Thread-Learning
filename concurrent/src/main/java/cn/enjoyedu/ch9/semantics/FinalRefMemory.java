package cn.enjoyedu.ch9.semantics;

/**
 * 类说明：
 */
public class FinalRefMemory {
    /**
     * 是引用类型
     */
    final int[] intArray;
    static FinalRefMemory obj;

    /**
     * 构造函数
     */
    public FinalRefMemory() {
        // 1
        intArray = new int[1];
        // 2
        intArray[0] = 1;
    }

    /**
     * 写线程A执行
     */
    public static void writerOne() {
        // 3
        obj = new FinalRefMemory();
    }

    /**
     * 写线程B执行
     */
    public static void writeTwo() {
        // 4
        obj.intArray[0] = 2;
    }

    /**
     * 读线程C执行
     */
    public static void reader() {
        // 5
        if (obj != null) {
            // 6
            int temp1 = obj.intArray[0];
        }
    }

}
