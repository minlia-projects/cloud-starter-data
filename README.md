# 数据访问自动化  

结合mybatis与mybatisplus框架提供的强大功能，提供数据访问自动化的能力

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.minlia.cloud.starter/cloud-starter-data/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/com.minlia.cloud.starter/cloud-starter-data/) 
[![Apache License 2](https://img.shields.io/badge/license-ASF2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt) 
[![Build Status](https://travis-ci.org/minlia-projects/cloud-starter-data.svg?branch=master)](https://travis-ci.org/minlia-projects/cloud-starter-data)
[![Waffle.io - Columns and their card count](https://badge.waffle.io/minlia-projects/cloud-starter-data.svg?columns=all)](https://waffle.io/minlia-projects/cloud-starter-data)

## 集成到自已的项目时添加依赖项    

```pom
<dependency>
  <groupId>com.minlia.cloud.starter</groupId>
  <artifactId>cloud-starter-data</artifactId>
  <version>2.0.0.RELEASE</version>
</dependency>
```
## 后端Endpoint层代码

```
  @PostMapping(value = "/api/status/post")
  @ApiOperation(value = "状态", notes = "测试提交", httpMethod = "POST")
  public StatefulBody postStatus(@RequestBody WithIdBody body) {
    Assertion.is(true, ApiCode.ACCOUNT_DISABLED);
    return SuccessResponseBody.builder().build();
  }
```

## 返回报文示例

```
{
  "payload":{
    "id":33333333333,
    "balance":228866.00
  }
  "code": 1,//业务返回码
  "message": "OK",//业务返回释义
  "requestId": "cZCu5aAftUn2ivn2rcKb2YUhb6N7ijP420180402212405106502",//当前请求编号
  "status": 200,//请求的http状态码
  "timestamp": 1522675445311//当前请求时间戳
}
```

