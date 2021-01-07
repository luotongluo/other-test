package test;

/**
 * @author tong.luo
 * @description TestCapacity
 * @date 2021/1/7 16:15
 */
public class TestCapacity {
    public static void main(String[] args) {
        Long cap = 16777216L;
        cap = cap / 1024 /1024 ;
        System.out.println(cap);

        /*
        * 对于引用类型来说， ==指的是地址指的比较
        * 双引号直接写的 字符串在常量池中，而new出来的对象则不在常量池中
        * */
//        String s = "hello";
//        String s1 = new String("hello");
//        String s2 = new String("hello");
//        System.out.println(s1 == s);
//        //表示比较的是栈帧存放的地址不同，所以是不想等的
//        System.out.println(s1 == s2);
        //表示返回的引用的数据相同 运行期间加入常量池中的类型
//        System.out.println(s1.intern() == s2.intern());
    }
}
