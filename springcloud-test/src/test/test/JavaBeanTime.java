import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tong.luo
 * @description JavaBeanTime
 * @date 2021/1/25 16:23
 */
public class JavaBeanTime {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaBeanTime.class);

    private static String word = "hello";

    static {
        System.out.println("静态函数 + " + word);
    }

    public JavaBeanTime() {
        System.out.println("wucan构造函数+ " + word);
    }

    public static void main(String[] args) {
        Son javaBeanTime = new Son();
    }

    static class Son extends JavaBeanTime {
        static {
            System.out.println("Son 静态函数 + " + word);
        }

        public Son() {
            System.out.println("Son wucan构造函数+ " + word);
        }
    }

}

