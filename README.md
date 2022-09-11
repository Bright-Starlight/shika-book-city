# shika-book-city

#### 介绍

希卡书城管理系统是服务于网上书店卖家的一个程序，里面的功能可以满足大多数商家对于店面的管理需求。

#### 功能

1、对图书的添加，修改，和删除  

2、店铺的活动管理  

3、店铺的订单管理  

4、自动化对店铺的销量统计  

5、客户对书籍的评论评论  

6、个人中心  

#### 软件页面

   进入希卡书城管理系统网页版后，可看到主界面如下图所示：

[![vFfuex.png](https://s1.ax1x.com/2022/07/31/vFfuex.png)](https://imgtu.com/i/vFfuex)

[![vFfZl9.png](https://s1.ax1x.com/2022/07/31/vFfZl9.png)](https://imgtu.com/i/vFfZl9)

希卡书城管理系统实现时主要包括如下几个页面：【登录】页面、【注册】页面、【书籍管理】页面、【活动设置】页面、【订单管理】页面、【销量统计】页面、【客户评论】页面、【个人中心】页面。

#### 登录

主要用于店家登录店铺后台

[![vFfmO1.png](https://s1.ax1x.com/2022/07/31/vFfmO1.png)](https://imgtu.com/i/vFfmO1)

#### 注册

用于店家注册账号

[![vFfeyR.png](https://s1.ax1x.com/2022/07/31/vFfeyR.png)](https://imgtu.com/i/vFfeyR)

#### 书籍管理

按照上架与不上架分类，商家可以自由设置书籍的上架和下架，以及添加书籍，修改书籍和删除书籍。右上角附带搜索功能，可以根据书名搜索。

[![vFfVSJ.png](https://s1.ax1x.com/2022/07/31/vFfVSJ.png)](https://imgtu.com/i/vFfVSJ)

  

####  活动设置

活动设置主要用于商家可以发布促销活动来获取更多的营销额。上架可以设置活动使时间，商家手动发布后，系统自动会进行激活和结束。商家可以自由添加活动和修改活动，也可以强行中止活动。

[![vFfAW4.png](https://s1.ax1x.com/2022/07/31/vFfAW4.png)](https://imgtu.com/i/vFfAW4)


####  订单管理

订单管理主要用于商家查看订单情况：

 

[![vFf9e0.png](https://s1.ax1x.com/2022/07/31/vFf9e0.png)](https://imgtu.com/i/vFf9e0)

#### 销量统计

销量统计用于统计商家日，月，年的销售情况：

[![vFfFFU.png](https://s1.ax1x.com/2022/07/31/vFfFFU.png)](https://imgtu.com/i/vFfFFU)

 

#### 客户评论

客户评论可以查看用户对产品的评价：

[![vFfkYF.png](https://s1.ax1x.com/2022/07/31/vFfkYF.png)](https://imgtu.com/i/vFfkYF)
[![vFfCwV.png](https://s1.ax1x.com/2022/07/31/vFfCwV.png)](https://imgtu.com/i/vFfCwV)

 

#### 个人中心

个人中心，用户可以对自己的资料进行修改：

[![vFfPoT.png](https://s1.ax1x.com/2022/07/31/vFfPoT.png)](https://imgtu.com/i/vFfPoT)

 

#### 软件架构

SpringBoot + Vue3.0 + element-ui-plus + Mybatisplus +Echarts + 前后分离



#### 主要技术栈

- #### SpringBoot

- #### Lombok

- #### Druid

- #### Pagehelper

- #### Redis

- #### Apache Shiro

- #### Mysql

- #### MybatisPlus

- #### UserAgentUtils

- #### Gson

- #### Apache HttpClient

- #### Vue

- #### axios

- #### Echarts

#### 安装教程

- 后端

  1. 创建数据库 shika_book_city 在文件加docs/mysql导入数据库数据
  2. 配置redis和数据库
  3. 运行ShikaBookCityApplication启动后端项目
  4.注册路过图床https://imgtu.com/login 用于文件上传功能
  5.进入src/main/java/com/parachute/shikabookcity/util/ImgtuUtils.java修改用户名和密码即可
- 前端

  1. 进入shika-book-city-ui文件夹 打开cmd 
  2. 执行 yarn install
  3.执行 yarn serve 启动前端项目
有疑问可联系qq：2417600913