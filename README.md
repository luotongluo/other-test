# other-test
    日常测试的文件包含如下：
    excl文件的测试，
    读取，写入，写出，设置单元格格式、将excl文件中的内容转化为sql文件、

#daily-test
#springcloud-test
springcoud 的测试项目
    spring-cloud-common 公共依赖的文件或者jar包，工具类文件
    spring-cloud-parent 引入的公共的依赖文件，spring版本等依赖
    eurke1 注册中心 port：8001
    eurek2 注册中心 port：8002
    eurek3 注册中心 port：8003
eureka.instance.hostname=localhost
###### 认证的用户名,比如root
spring.security.user.name=root
spring.security.user.password=root
eureka.client.serviceUrl.defaultZone=http://root:root@${eureka.instance.hostname}:8001/eureka/,http://root:root@${eureka.instance.hostname}:8003/eureka/

    con-con 集合服务 port：8020
    con-base 基础服务 port：8010
    con-mq  mq测试 port：8030