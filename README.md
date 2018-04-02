spring-boot-start-oss
===================================
Spring boot with aliyun oss support

### usage
in pom.xml add following dependency:

      <dependency>
           <groupId>org.mvnsearch.boot</groupId>
           <artifactId>spring-boot-starter-oss</artifactId>
           <version>1.0.0-SNAPSHOT</version>
      </dependency>
      
in application.properties file, please add following keys:

     spring.oss.key=xxxx
     spring.oss.secret=yyyy
     spring.oss.bucket=xxx-dev
     
in your code you can use OssClient directly:

     @Autowired
     OSSClient ossClient;

you can use FileStorageService API to operate file:

     @Autowired
     FileStorageService fileStorageService;

### Spring XML Configuration
If you want to setup bean in Spring xml file, please use following code:

     <bean id="ossClient" class="com.aliyun.oss.OSSClient">
          <constructor-arg value="access_key"/>
          <constructor-arg value="access_secret"/>
     </bean>
          
     <bean id="fileStorageService" class="org.mvnsearch.boot.oss.impl.FileStorageServiceOssImpl">
          <property name="secret" value="access_key"/>
          <property name="accessSecret" value="access_key"/>
          <property name="bucket" value="bucket_name"/>
     </bean>
     
### spring-boot-start-oss service List

* com.minlia.modules.starter.oss.FileStorageService: API to operate the file
* com.aliyun.oss.OSSClient: aliyun OSSClient

Please use FileStorageService as usual, not OSSClient.
     
### Aliyun OSS endpoint

Please visit http://localhost:8080/oss to view detail information.


### FaQ

* Please upload a "ok.txt" under your buket for health indicator.
