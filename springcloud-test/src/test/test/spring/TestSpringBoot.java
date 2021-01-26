package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tong.luo
 * @description TestSpringBoot
 * @date 2021/1/25 16:51
 */
@ComponentScan("spring")
public class TestSpringBoot {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestSpringBoot.class);

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestSpringBoot.class);
        ApplicationContext context = new AnnotationConfigApplicationContext(TestSpringBoot.class);
        LOGGER.info("before get bean");
        context.getBean(TestBean.class);
        LOGGER.info("after get bean");
    }
}
