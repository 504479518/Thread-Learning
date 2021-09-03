package cn.enjoyedu.ch9;

/**
 * 控制依赖
 */
public class ControlDep {
    int a = 0;
    volatile boolean flag = false;

    public void init() {
        // 1
        a = 1;
        // 2
        flag = true;
        //.......
    }

    public synchronized void use() {
        // 3
        if (flag) {
            // 4
            int i = a * a;
        }
        //.......
    }
}
