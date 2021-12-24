package com.lt.dailytest;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.dao.TestMapper;
import com.lt.dailytest.utils.project.SelfJedisUtils;
import com.lt.dailytest.utils.MultiThreadTransactionComponent;
import com.lt.dailytest.utils.common.ValidatorUtil;
import com.lt.dailytest.othertest.validate.TestBean;
import com.lt.dailytest.utils.major.MajorKeyFactory;
import com.lt.dailytest.utils.project.ThreadPoolUtils;
import com.lt.dailytest.vo.MailVo;
import com.lt.dailytest.vo.MailVotest;
import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootTest
class DailyTestApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SelfJedisUtils jedisUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    TestMapper testMapper;

    @Test
    public void BigdicimalTest() {

    }

    @Test
    public void getId() {
        int loopcal = 5000;
        CountDownLatch countDownLatch = new CountDownLatch(loopcal);
        Executor defaultThreadPool = ThreadPoolUtils.getDefaultThreadPool();
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            for (int i = 0; i < loopcal; i++) {
                countDownLatch.countDown();
                defaultThreadPool.execute(() -> {
                    for (int j = 0; j < 10000; j++) {
                        String generatePrimaryKey = MajorKeyFactory.generatePrimaryKey();
                        Long generatePrimaryKeyLongVal = MajorKeyFactory.generatePrimaryKeyLongVal();

                        logger.info("generatePrimaryKey:[{}],generatePrimaryKeyLongVal:[{}]", generatePrimaryKey, generatePrimaryKeyLongVal);
                        if (null != hashMap.get(generatePrimaryKey)) {
                            this.logger.info("repecr id : [{}]", generatePrimaryKey);
                        }
                        if (null != hashMap.get(String.valueOf(generatePrimaryKeyLongVal))) {
                            this.logger.info("repecr id : [{}]", generatePrimaryKeyLongVal);
                        }
                        hashMap.put(generatePrimaryKey, generatePrimaryKey);
                        hashMap.put(String.valueOf(generatePrimaryKeyLongVal), String.valueOf(generatePrimaryKeyLongVal));
                        /*logger.info("generatePrimaryKey thread id:[{}];thread name:[{}] :[{}],count:{}",
                                Thread.currentThread().getId(), Thread.currentThread().getName(), generatePrimaryKey, countDownLatch.getCount());*/

                    }
                });
            }

            countDownLatch.await(3, TimeUnit.MINUTES);

            logger.info(",count:{}", countDownLatch.getCount());
        } catch (Exception e) {

        }
        while (true) {

        }
    }

    @Test
    public void testgetId() {
        String generatePrimaryKey = MajorKeyFactory.generatePrimaryKey();
        logger.info("generatePrimaryKey :[{}],count:", generatePrimaryKey);
    }

    @Test
    public void testSql() {
        List<Object> objects = this.testMapper.selectAll();
        System.out.println(JSON.toJSONString(objects));
    }

    @Test
    public void testTransaction() {
        PlatformTransactionManager platformTransactionManager = new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return null;
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {

            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {
                status.setRollbackOnly();
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        MultiThreadTransactionComponent mttc = new MultiThreadTransactionComponent(platformTransactionManager, threadPoolExecutor);
        for (int k = 0; k < 10; k++) {
            int i = RandomUtils.nextInt(0, 5);
            int y = RandomUtils.nextInt(0, 5);
            //添加要执行的业务代码
            mttc.addFunction(() -> {
                System.out.println("当前线程：" + Thread.currentThread().getName());
                System.out.println(i % y);  //除数为0时 执行失败
//                MarketGeomUpLog marketGeomUpLog = new MarketGeomUpLog();
//                marketGeomUpLog.setContent(i + "--" + y);
//                marketGeomUpLogMapper.addLog(marketGeomUpLog);
                return 0;
            });
        }
        mttc.execut();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mapStructTest() {
        System.out.println();
//        ConverMapper mapper = Mappers.getMapper(ConverMapper.class);
//        CopyBean1 copyBean1 = new CopyBean1();
//        copyBean1.setName("123");
//        copyBean1.setAge(012);
//        copyBean1.setAnInt(022);
//        copyBean1.setDate(new Date());
//        copyBean1.setStringList(Arrays.asList("123","2222"));
//
//        CopyBean2 copyBean2 = mapper.convertBean(copyBean1);
//        logger.info("copyBean1:[{}]",JSON.toJSONString(copyBean1));
//        logger.info("copyBean2:[{}]",JSON.toJSONString(copyBean2));
    }

    @Test
    public void testcopy() {
        MailVo mailVo = new MailVo();
        mailVo.setPageIndex(123);
        mailVo.setPageSize(123);
        mailVo.setBcc("123");
        MailVotest mailVotest = new MailVotest();
        BeanUtils.copyProperties(mailVo, mailVotest);
        logger.info("info:[{}]", JSON.toJSONString(mailVotest));
    }

    @Test
    void contextLoads() {
        TestBean testBean = new TestBean();
        Map<String, StringBuffer> validate = ValidatorUtil.validate(testBean);
        System.out.println(JSON.toJSON(validate));
    }

    @Test
    void jedisTest() {
        String ss = "DailyTestApplicationTests";
        this.jedisUtils.set(ss, UUID.randomUUID());
        Object o = this.jedisUtils.get(ss);
        System.out.println(JSON.toJSONString(o));
    }

    @Test
    public void readFile() throws Exception {
        String fileName = "templates/开票单张限额.xls";
        //InputStream resourceAsStream = this.getClass().getResourceAsStream(fileName);
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        XSSFWorkbook wb = new XSSFWorkbook(resourceAsStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(0);
        row = sheet.getRow(1);
        row = sheet.getRow(2);
        row = sheet.getRow(3);
    }


    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("china");
        stringArrayList.add("china");
        stringArrayList.add("US");
        Map<String, Long> collect = stringArrayList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);
        System.out.println("==========================");
        Integer a = new Integer(1234);
        Integer b = new Integer(1234);
        Integer d = 1234;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println("==========================");
        String ss = "水果苹果";
        String sp = "*";
        String substring = ss.substring(1, ss.length());
        int indexOf = substring.indexOf(sp);
        String substring1 = substring.substring(indexOf + 1, substring.length());
        System.out.println(substring1);
//        System.out.println(split.length);
        System.out.println("==========================");
        String taxRateInRecord = "0.239";
        BigDecimal bigDecimal = new BigDecimal(taxRateInRecord).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);
        System.out.println("==========================");
        String goodsName = "其他营养、-保健食品1030208990000000000";
        String ONE2HUNDRED_STR_REG = "^[一-龥\\w\\s~!@%#$^*+='?\\-\\\\/(){}\\[\\],.\\|《》，。！{}·#￥……*（）——:：“”？【】；‘’`_;\"]{1,100}$";
        Matcher matcher = Pattern.compile(ONE2HUNDRED_STR_REG).matcher(goodsName);
        boolean matches = matcher.matches();
        System.out.println(matches);
        System.out.println("==========================");
        double powa = 22;
        double powerb = -2;
        double pow = Math.pow(powa, powerb);
        System.out.println(powa + "^" + powerb + "=" + pow);

    }

    @Test
    public void testsub() {
        Integer aa = 4;
        Integer bb = -0;
        System.out.println(aa + bb);
        System.out.println(1 >> 6);
        System.out.println(1 << 6);
    }
}
