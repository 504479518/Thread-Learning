package cn.enjoyedu.ch7.safeclass;

/**
 * 类不可变
 */
public class ImmutableClass {
    private final int a;
    /**
     * 不安全
     */
    private final UserVo user = new UserVo();

    public int getA() {
        return a;
    }

    public UserVo getUser() {
        return user;
    }


    public ImmutableClass(int a) {
        this.a = a;
    }

    public static class User {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
