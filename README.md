# studentManage
---
# Servlet
>
* 页面放在WEB-INF下是访问不到的，该文件夹被写保护。
* servlet中要求servlet-name在servlet-class上方
* servlet中不建议修改service()方法，而是修改doGet()/doPost()方法
* 可以在web.xml文件中<servlet>下，初始化参数
* 整个web容器是在维护一个ServletContext对象
* ServletContext--绝对路径（推荐）	ServletRequest--相对路径
---
# Serializable
>
* 实体类序列化，信息保存在硬盘上。
---
# Thread
>
* 多线程访问一个变量安全：
* 1.可是实现SingleThreadModel（强烈不建议！）
* 2.对某一变量加synchronized，进行原子性操作，讲逻辑代码放入其中
* 3.或者某一变量不想修改，加final修饰
* 4.Class AtomicInteger 他的父类是Number 他是线程安全的
* 5.尽量使用局部变量，只有方法内部访问，方法结束就销毁了
* Properties是Map的一个实现类，内部键值对结构
* DButils.class.getResourceAsStream("/common.properties");"/"：代表classes路径下的根路径，相对于DButils类而言
* forward:将request和response向下一个对象提交，对于客户端url不变   redirect:告诉浏览器跳转到另一个页面
---
# Other
>
* 端口号冲突可以尝试在进程中结束javaw.exe进程
* 要避免重复的代码 
* 建议使用con.prepareStatement(arg0)，使用statment(arg0)的话会产生注入漏洞
* JNDI	java命名目录接口   使用数据库连接池
* Connection是线程安全的
* 工具类里的方法都是静态的。（习惯）
* ctrl + 1 快速查看错误信息
* ctrl+2 create local var;

M:model--service
V:view--jsp
C:controller--servlet/struts/spring...

服务器返回的键值对保存到客户端cookie中，用户每次访问服务器都携带cookie。每组键值对都是一个cookie

request(请求)的生命周期是一次请求,request中属性的生命周期，就是request的生命周期
session(会话)也可以setAttribute()放入键值对。生命周期:从打开浏览器到关闭浏览器,或调用invalidate()方法。session有唯一Id,键值对JSESSIONID=50856973093D0820A9997AF645204C3E,服务器根据客户端的JSESSIONID识别每个用户.在Internet选项-隐私中可以禁用cookie
为防止服务器内存被占用满了，能放到request中不放到session中
http协议是无状态的

servletContext中也放键值对，但属于公共区域.由tomcat第一次打开创建对象往里放东西，其他人知识从中读取。

监听器ServletContextListener，当ServletContext生成好和销毁的时候调用监听器中的初始化和销毁方法

把浏览器关闭了其实服务器器端是不知道的。

>
强转做了些什么？欺骗java编译器，告诉它这里我确定是什么类型，你编译就行了，根本就不会改变对象的类型。
栈中保存临时变量，堆区保存对象，方法区保存的是类型信息、方法数据结构、静态成员。 
刚才的req和reqs都是在栈区中的临时变量，它们共同指向堆区的同一个对象，你对req这个遥控器强制类型转换，根本不会影响到堆区中的电视机。

[JPBM表结构和使用说明](http://search.sina.com.cn/?q=JBPM%E6%95%B0%E6%8D%AE%E5%BA%93%E8%A1%A8%E8%AF%B4%E6%98%8E&ie=utf-8&e=utf-8&range=article&t=&s=sup&by=all&type=&stype=1&c=blog)
