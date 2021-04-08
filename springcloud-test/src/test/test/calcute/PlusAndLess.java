package calcute;

/**
 * @author admin
 * @description PlusAndLess
 * 如果++ 在前用运算符操作之后的数据参与计算
 * 如果++ 或者 -- 在变量的后面的话 则用变量的原始的值参与计算
 * @date 2021/2/5 16:20
 */
public class PlusAndLess {
    public static void main(String[] args) {
        int a, b, c, d, e;
        a = b = c = d = 1;
        //2 + 1 +0 + 0 + 0
        e = ++a + b++ + --c + c++ + --d;
        System.out.println(e);
    }
}
