#### Serlvet3.0
##### Servlet 3.0 作为 Java EE 6 规范体系中一员，随着 Java EE 6 规范一起发布。
    1.新增的注解支持：该版本新增了若干注解，用于简化 Servlet（@WebServlet）、过滤器（@filterByAnnotation）和监听器（@WebListener）的声明。
    这使得 web.xml 部署描述文件从该版本开始不再是必选的了。
    2.Servlet3.0中新增的可插性支持则将 Servlet 配置的灵活性提升到了新的高度。 
    现在我们可以在不修改已有 Web 应用的前提下，只需将按照一定格式打成的 JAR 包放到 WEB-INF/lib 目录下，即可实现新功能的扩充，不需要额外的配置。
    通过模块化部署描述符解决了这个问题，一个Web分片可以看作是Web.xml文件的一个逻辑段，
    可以存在多个Web分片，每个分片代表一个逻辑段，Web分片集可以组成一个完整的Web.xml文件，
    Web.xml文件这种逻辑分区让Web框架自己可以在Web容器中完成注册。你在Web应用程序中使用的每个Web框架可以在一个Web分片中定义所有它需要的工具，
    3. Web 应用增加一个 Servlet 配置有如下三种方式： 
    ( 过滤器、监听器与 Servlet 三者的配置都是等价的，故在此以 Servlet 配置为例进行讲述，过滤器和监听器具有与之非常类似的特性 )
    (1)编写一个类继承自 HttpServlet，将该类放在 classes 目录下的对应包结构中，修改 web.xml，在其中增加一个 Servlet 声明。这是最原始的方式；
    (2)编写一个类继承自 HttpServlet，并且在该类上使用 @WebServlet 注解将该类声明为 Servlet，将该类放在 classes 目录下的对应包结构中，无需修改 web.xml 文件。
    (3)编写一个类继承自 HttpServlet，将该类打成 JAR 包，并且在 JAR 包的 META-INF 目录下放置一个 web-fragment.xml 文件，该文件中声明了相应的 Servlet 配置。

#### 演示项目的目的：

  	1.上面3种单独使用的时候的详细配置，以及重要的参数说明，相互转换的等价配置
    2.上面3种混合使用的时候，3种配置的初始化的顺序，和执行顺序
	
#### 模块具体解释如下：	
    1.Servlet3Demo::maven parent module,用来管理serlvet3-web,serlvet3-registration,servlet3-xml,
    servlet3-annotation,servlet3-fragment1,servlet3-fragment2共6个子模块。
    2.serlvet3-web（war）：主要是配置和依赖其他5个模块。
    3.servlet3-xml（jar）：servlet，filter，listener的XML配置（也包含几个注解配置，后面用来测试执行顺序）。
    4.serlvet3-annotation（jar）：servlet，filter，listener的注解配置。
    5.serlvet3-registration（jar）：servlet，filter，listener的动态注册配置。
    6.servlet3-fragment1,servlet3-fragment2（jar）：servlet，filter，listener的web-fragment.xml配置
#### XML和注解配置参数说明和等级配置
##### 超级注意点
    注解方式请用tomcat7.0以上，JDK1.7以上，还有就是web.xml记得一定要是3.0以上。
    weblogic的话，注意安全，我也很迷茫。
##### Servlet的配置
    属性名	            类型	          描述
    name	            String	        指定 Servlet 的 name 属性，等价于 <servlet-name>。如果没有显式指定，则该 Servlet 的取值即为类的全限定名。
    value	            String[]	        该属性等价于 urlPatterns 属性。两个属性不能同时使用。
    urlPatterns	    String[]	        指定一组 Servlet 的 URL 匹配模式。等价于 <url-pattern> 标签。
    loadOnStartup	    int	                指定 Servlet 的加载顺序，等价于 <load-on-startup> 标签。
    initParams	    WebInitParam[]	指定一组 Servlet 初始化参数，等价于 <init-param> 标签。
    asyncSupported	    boolean	        声明 Servlet 是否支持异步操作模式，等价于 <async-supported> 标签。
    description	    String	        该 Servlet 的描述信息，等价于 <description> 标签。
    displayName	    String	        该 Servlet 的显示名，通常配合工具使用，等价于 <display-name> 标签。
```
//注解方式
@WebServlet(urlPatterns = {"/simple"}, asyncSupported = true, 
loadOnStartup = -1, name = "SimpleServlet", displayName = "ss", 
initParams = {@WebInitParam(name = "username", value = "tom"),@WebInitParam(name = "username2", value = "tom2")} 
) 
public class SimpleServlet extends HttpServlet{ … }
```
```
//XML配置
<servlet>
    <display-name>ss</display-name>
    <servlet-name>SimpleServlet</servlet-name>
    <servlet-class>footmark.servlet.SimpleServlet</servlet-class>
    <load-on-startup>-1</load-on-startup>
    <async-supported>true</async-supported>
    <init-param>
        <param-name>username</param-name>
        <param-value>tom</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>SimpleServlet</servlet-name>
    <url-pattern>/simple</url-pattern>
</servlet-mapping>
```
##### Filter配置
    属性名	        类型	        描述
    filterName	String	        指定过滤器的 name 属性，等价于 <filter-name>
    value	        String[]	该属性等价于 urlPatterns 属性。但是两者不应该同时使用。
    urlPatterns	String[]	指定一组过滤器的 URL 匹配模式。等价于 <url-pattern> 标签。
    servletNames	String[]	指定过滤器将应用于哪些 Servlet。取值是 @WebServlet 中的 name 属性的取值，或者是 web.xml 中 <servlet-name> 的取值。
    dispatcherTypes	DispatcherType	指定过滤器的转发模式。具体取值包括：ASYNC、ERROR、FORWARD、INCLUDE、REQUEST。
    initParams	WebInitParam[]	指定一组过滤器初始化参数，等价于 <init-param> 标签。
    asyncSupported	boolean	        声明过滤器是否支持异步操作模式，等价于 <async-supported> 标签。
    description	String	        该过滤器的描述信息，等价于 <description> 标签。
    displayName	String	        该过滤器的显示名，通常配合工具使用，等价于 <display-name> 标签。
```
//注解方式
@WebFilter(servletNames = {"SimpleServlet"},filterName="SimpleFilter") 
public class LessThanSixFilter implements Filter{...}
```
```
//XML配置
<filter> 
    <filter-name>SimpleFilter</filter-name> 
    <filter-class>xxx</filter-class> 
</filter> 
<filter-mapping> 
    <filter-name>SimpleFilter</filter-name> 
    <servlet-name>SimpleServlet</servlet-name> 
</filter-mapping>
```
###### Listener配置
    属性名	类型	是否可选	  描述
    value	String	  是	  该监听器的描述信息。
```
//注解配置
@WebListener("This is only a demo listener") 
public class SimpleListener implements ServletContextListener{...}
```
```
//XML配置
<listener> 
    <listener-class>footmark.servlet.SimpleListener</listener-class> 
</listener>
```
#### 动态注册配置
##### 方式1,监听ServletContextListener的初始化过程中定义。
```
//创建一个监听器，实现ServletContextListener
//更多参数设定请Google一下
public class RegistrationByListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        //Serlvet动态注册
        ServletRegistration sr = sc.addServlet("servletByRegistration","com.servlet3.demo.controller.ServletByRegistration");
        sr.addMapping("/ServletByRegistration");
        //Filter动态注册（重点参数addMappingForUrlPatterns（..,?,..）,这个？为true则在xml的filter配置完在动态注册，false则相反）
        FilterRegistration fr = sc.addFilter("filterByRegistration","com.servlet3.demo.filter.FilterByRegistration");
        fr.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        fr = sc.addFilter("filterByRegistration2","com.servlet3.demo.filter.FilterByRegistration2");
        fr.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
        //Listener的动态注册
        sc.addListener("com.servlet3.demo.Listener.ListenerByRegistration");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
```
##### 方式2
```
等待完成
等待完成
等待完成
等待完成
等待完成
```
#### fragment文件配置
    web模块必须用web-fragment文件存放在根路径下的META-INF文件夹下。
    Servlet，Filter，Listener的代码还是像XML配置一样写，只不过把本来在Web.xml的配置信息，写到了web-fragment中
    注意定义fragment的name，然后通过ordering如下代码，可以设置初始化执行的顺序
```
 <?xml version="1.0" encoding="UTF-8"?>
<web-fragment id="WebFragment_ID" version="3.0"
	xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-fragment_3_0.xsd">
	<display-name>servlet3-fragment2</display-name>
	<name>fragment2</name>
	<ordering>
		<after>
			<others></others>
		</after>
		<before>
			<name>fragment1</name>
		</before>
	</ordering>
	<servlet>
		<servlet-name>serlvetByFragment2</servlet-name>
		<servlet-class>com.servlet3.demo.controller.ServletByFragment2</servlet-class>
	</servlet>

	<servlet-mapping>
		<servlet-name>serlvetByFragment2</servlet-name>
		<url-pattern>/serlvetByFragment2</url-pattern>
	</servlet-mapping>

	<filter>
		<filter-name>filterByFragment4</filter-name>
		<filter-class>com.servlet3.demo.filter.FilterByFragment4</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>filterByFragment4</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

	<filter>
		<filter-name>filterByFragment3</filter-name>
		<filter-class>com.servlet3.demo.filter.FilterByFragment3</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>filterByFragment3</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	
	<listener>
		<listener-class>com.servlet3.demo.Listener.ListenerByFragment2</listener-class>
	</listener>https://github.com/positiveenergyquan/servlet3demo
</web-fragment>
```

#### web-fragment.xml文件模板
```
<?xml version="1.0" encoding="UTF-8"?>
<web-fragment 
	xmlns="http://java.sun.com/xml/ns/javaee"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
    http://java.sun.com/xml/ns/javaee/web-fragment_3_0.xsd" 
    version="3.0">  
   
</web-fragment> 

```
#### web.xml文件模板
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app  
	xmlns="http://java.sun.com/xml/ns/javaee" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee                       
	http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	version="3.0">

</web-app>

```

#### 3种混合使用的时候，3种配置的初始化的顺序，和执行顺序
![Alt text](https://github.com/positiveenergyquan/servlet3demo/tree/master/servlet3-web/src/main/resources/inti.png) 
		初始化顺序为：
		
