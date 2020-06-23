##什么是权限管理
  权限管理实现对用户访问系统的控制。权限管理包括身份认证和授权。对于用户首先进行身份认证，然后判断是否有权限。
##什么是身份认证
  身份认证判断一个用户是否为合法用户。
##什么是授权
  授权即访问控制。
##shiro架构图
![Image text](https://images2015.cnblogs.com/blog/12617/201612/12617-20161206222914835-1184096667.jpg)
##shiro的认证
 ###1.关键对象
   #####·Subject 主体（用户）
   #####·Principal 身份信息（用户名、手机号、邮箱等）
   #####·credential 凭证信息（密码、证书等）
 ###2.认证流程
 ![Image text](https://img-blog.csdn.net/20171012105949778?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvRW1tYV9Kb2Fucw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
 ###3.配置文件
  shiro使用.ini配置文件，用来书写系统中的相关权限数据。为了初次学习方便，先不用读取数据库。
##shiro授权
###1.关键对象
#####·Subject 主体（用户）
  #####·Resource 菜单、页面等
  #####·Permission 权限
  ###授权方式
  ##### ·基于角色
  if(subject.hasrole("admin"))
  #####·基于资源  格式：资源标识符：操作：资源类型
  if(subject.isPermission("user:admin"))
   #####·shiro授权使用
   ######1.编码
   ######1.注解
   ######1.标签
  ##shiro整合springboot
  #### 1.流程
  #####（1）通过过滤器过滤所有请求，对请求进行认证
  #####（2）认证通过进入系统，对各个方法进行授权
  #### 2.数据库表设计
  #####·用户----->角色----->权限----->资源
  #####·用户----->角色
  #####·用户----->权限
  #####·用户----->角色  用户----->权限
  ![Image text](https://images2015.cnblogs.com/blog/841496/201606/841496-20160605202550524-144021396.png)
   ##整合redis
   ##### 用户每个操作都会触发授权，这样会频繁的调用数据库，使用缓存来缓解这样的压力。 