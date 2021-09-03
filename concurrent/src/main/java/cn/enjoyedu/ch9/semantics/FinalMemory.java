package cn.enjoyedu.ch9.semantics;

/**
 * 类说明：final的内存语义
 */
public class FinalMemory {
    /**
     * 普通变量
     */

    int i;
    /**
     * final变量
     */
    final int j;
    static FinalMemory obj;

    /**
     * 构造函数
     */
    public FinalMemory() {
        // 写普通域
        i = 1;
        // 写final域
        j = 2;
    }

    public static void writer() {// 写线程A执行
        obj = new FinalMemory();
    }

    public static void reader() {
        // 读线程B执行
        // 读对象引用
        FinalMemory object = obj;
        // 读普通域
        int a = object.i;
        // 读final域
        int b = object.j;
    }
}
