
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





> [https://juejin.im/post/5aa4a2e35188255589496eb8](https://juejin.im/post/5aa4a2e35188255589496eb8)
> [https://juejin.im/post/5cb34beee51d456e303db862](https://juejin.im/post/5cb34beee51d456e303db862)

