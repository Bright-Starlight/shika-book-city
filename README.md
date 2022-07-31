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

![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image002.gif)

![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image004.gif)

希卡书城管理系统实现时主要包括如下几个页面：【登录】页面、【注册】页面、【书籍管理】页面、【活动设置】页面、【订单管理】页面、【销量统计】页面、【客户评论】页面、【个人中心】页面。

#### 登录

主要用于店家登录店铺后台

   ![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image006.gif)

#### 注册

用于店家注册账号

![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image008.gif)

#### 书籍管理

按照上架与不上架分类，商家可以自由设置书籍的上架和下架，以及添加书籍，修改书籍和删除书籍。右上角附带搜索功能，可以根据书名搜索。

![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image010.gif)

  

####  活动设置

活动设置主要用于商家可以发布促销活动来获取更多的营销额。上架可以设置活动使时间，商家手动发布后，系统自动会进行激活和结束。商家可以自由添加活动和修改活动，也可以强行中止活动。

![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image012.gif)



####  订单管理

订单管理主要用于商家查看订单情况：

 

# ![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image014.gif)

#### 销量统计

销量统计用于统计商家日，月，年的销售情况：

![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image016.gif)

 

#### 客户评论

客户评论可以查看用户对产品的评价：

![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image018.gif)

![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image020.gif)

 

#### 个人中心

个人中心，用户可以对自己的资料进行修改：

![img](file:///C:/Users/machi/AppData/Local/Temp/msohtmlclip1/01/clip_image022.gif)

 

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

  1. 创建数据库 shika_book_city
  2. 配置redis和数据库
  3. 运行ShikaBookCityApplication启动后端项目

- 前端

  1. 进入shika-book-city-ui文件夹 打开cmd 
  2. 执行 yarn install

     3.执行 yarn serve 启动前端项目