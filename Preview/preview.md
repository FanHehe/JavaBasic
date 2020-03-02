
## 自我介绍

您好，我叫范廷雷，黑龙江哈尔滨人。

大工软件学院毕业，工作两年半，目前在杭州乐见公司做Java开发。

之前在富途网络公司有有NODE / PHP的开发经历，目前在做Java开发，做在线盲盒相关的购买发货功能。


## 项目


#### 项目介绍



> 因为公司规模较小，主营业务是用第三方快捷登陆。

#### 承担角色
全栈开发
#### 问题解决

1. Http通信问题
    - HttpUtil

```java
Type type = InvokeResult.class;
Type tp = getClass().getGenericSuperclass();
// reference https://juejin.im/entry/5b5e6bb7e51d45195312803a

if (tp instanceof ParameterizedType) {
    Type[] types = ((ParameterizedType)tp).getActualTypeArguments();
    type = new ParameterizedTypeImpl(InvokeResult.class, new Type[]{ types[0] });
}

return gson.fromJson(content, type);

```

2. 公司规模小，接受这个项目人都离职了，而且没有交接文档

3. 历史遗留问题
    - GBK 问题
    - 代码耦合严重

4. 重复代码太多: try-catch-finllay 每个都相同

#### 运行情况

### 股票雨活动

#### 项目介绍

六周年司庆活动，天降红包雨的H5小游戏。

花费积分开启游戏，积分可通过邀请人来获得，天降的红包可以被点开，可能为空，可能不空

当红包不空时，为自己加一分，每个人最多累计5分。

每局游戏10秒，游戏结束后，根据积分，可随机出现对应数量的随机股票。

用户可以选择其一作为自己这局游戏的奖励。

我负责的任务：

- 全部后端逻辑的开发
    - 邀请模块的开发
    - 花费积分开启游戏
    - 奖励多选一以及奖励发送

#### 承担角色
后端开发
#### 问题解决

1. 奖励一键领取，发送情况
    - 使用了rabbitmq异步
2. 游戏玩法
    - 不是真正做游戏的公司，目的是要有用户来玩，就把股票送出去。

#### 运行情况

## 我的问题

- 公司开发流程
- 我的职位对应的技术栈，我去了做什么
- 工作时间
- 会议情况

- 五险一金
- 评级晋升


## 关键字段

### Http Client

#### Http Client 封装

### Cookie Session Token

#### Cookie

- name
- value
- domain
- path
- expire
- httponly

##### cors

整个CORS通信过程，都是浏览器自动完成，不需要用户参与。

对于开发者来说，CORS通信与同源的AJAX通信没有差别，代码完全一样。浏览器一旦发现AJAX请求跨源，就会自动添加一些附加的头信息，有时还会多出一次附加的请求，但用户不会有感觉。

因此，实现CORS通信的关键是服务器。只要服务器实现了CORS接口，就可以跨源通信。

浏览器将CORS请求分成两类：简单请求（simple request）和非简单请求（not-so-simple request）。

只要同时满足以下两大条件，就属于简单请求。

HEAD、GET、POST

并且Http首部

Accept
Accept-Language
Content-Language
Last-Event-ID
Content-Type：只限于三个值application/x-www-form-urlencoded、multipart/form-data、text/plain

浏览器对这两种请求的处理，是不一样的。


> http://www.ruanyifeng.com/blog/2016/04/cors.html

#### Session

#### Json Web Token

JWT 的三个部分依次如下。

- Header（头部）
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```
- Payload（负载）
```java
// iss (issuer)：签发人
// exp (expiration time)：过期时间
// sub (subject)：主题
// aud (audience)：受众
// nbf (Not Before)：生效时间
// iat (Issued At)：签发时间
// jti (JWT ID)：编号
```
- Signature（签名）

```java
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret)
```
Signature 部分是对前两部分的签名，防止数据篡改。

首先，需要指定一个密钥（secret）。
这个密钥只有服务器才知道，不能泄露给用户。然后，使用 Header 里面指定的签名算法（默认是 HMAC SHA256），按照下面的公式产生签名。
格式：Header.Payload.Signature
算出签名以后，把 Header、Payload、Signature 三个部分拼成一个字符串，每个部分之间用"点"（.）分隔，就可以返回给用户。

JSON Web Token（缩写 JWT）是目前最流行的跨域认证解决方案，本文介绍它的原理和用法。

[JWT](http://www.ruanyifeng.com/blog/2018/07/json_web_token-tutorial.html)

