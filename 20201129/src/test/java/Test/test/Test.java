package Test.test;

public class Test {

    /**
     * 字段没有多态性，子类继承父类的成员变量，父类和子类，都有自己的成员变量
     * 静态分配
     */
    private static class P{
        protected int x = 3;

        public P(){
            System.out.println(x);
            s();
            System.out.println(x);
        }
        protected void s(){
            x = 4;
        }
    }

    private static class C extends P{
       protected int x = 1;

        public C(){
            System.out.println(x);
        }
        protected void s(){
            x = 6;
        }
    }

    public static void main(String[] args) {
        C c = new C();
        P p=new C();
        System.out.println(p.x);
        System.out.println(c.x);
    }
}


