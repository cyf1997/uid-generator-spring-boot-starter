uid-generator-spring-boot-starter
==========================
基于 [百度UidGenerator](https://github.com/baidu/uid-generator), 做了以下改动：
- 改造为spring-boot-starter的形式，不用部署为分布式，直接建表、在项目中引入，即可使用



Quick Start
------------

这里介绍如何在SpringBoot项目中使用uid-generator-spring-boot-starter, 具体流程如下:<br/>

### 步骤1: 创建表WORKER_NODE
在项目数据库里，运行sql脚本以导入表WORKER_NODE, 脚本如下:
```sql
DROP TABLE IF EXISTS WORKER_NODE;
CREATE TABLE WORKER_NODE
(
ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
HOST_NAME VARCHAR(64) NOT NULL COMMENT 'host name',
PORT VARCHAR(64) NOT NULL COMMENT 'port',
TYPE INT NOT NULL COMMENT 'node type: CONTAINER(1), ACTUAL(2), FAKE(3)',
LAUNCH_DATE DATE NOT NULL COMMENT 'launch date',
MODIFIED TIMESTAMP NOT NULL COMMENT 'modified time',
CREATED TIMESTAMP NOT NULL COMMENT 'created time',
PRIMARY KEY(ID)
) COMMENT='DB WorkerID Assigner for UID Generator',ENGINE = INNODB;
```
配置好数据库连接

### 步骤2: Maven引用
当前项目打包，或从Maven仓库中引入uid-generator-spring-boot-starter包
```xml
<dependency>
    <groupId>com.github.cyf1997</groupId>
    <artifactId>uid-generator-spring-boot-starter</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```
### 步骤3: 开始使用

UidGenerator接口提供了 UID 生成和解析的方法，提供了两种实现:
- [DefaultUidGenerator](src/main/java/com/github/cyf1997/uid/impl/DefaultUidGenerator.java)  
  实时生成
- [CachedUidGenerator](src/main/java/com/github/cyf1997/uid/impl/CachedUidGenerator.java)  
  生成一次id之后，按序列号+1生成一批id，缓存，供之后请求

如对UID生成性能有要求, 请使用CachedUidGenerator

```java
//@Resource
//private UidGenerator defaultUidGenerator;

@Resource
private UidGenerator cachedUidGenerator;

@Test
public void testSerialGenerate() {
    // Generate UID
    long uid = cachedUidGenerator.getUID();

    // Parse UID into [Timestamp, WorkerId, Sequence]
    // {"UID":"450795408770","timestamp":"2019-02-20 14:55:39","workerId":"27","sequence":"2"}
    System.out.println(cachedUidGenerator.parseUID(uid));

}
```

