## Questions and Answers

(1) What is web.xml and what is the usage of it?  
Generally speaking, this is the configuration file of web applications in java. 
It instructs the servlet container (tomcat for ex.) which classes to load, 
what parameters to set in the context, and how to intercept requests coming from browsers.
Click [here](https://cloud.google.com/appengine/docs/standard/java/config/webxml) 
to learn further about web.xml and servlets.

(2) What is a servlet?  
A servlet is simply a class which responds to a particular type of network request - 
most commonly an HTTP request. Basically servlets are usually used to implement 
web applications - but there are also various frameworks which operate on top of 
servlets (e.g. Struts,Spring) to give a higher-level abstraction than the 
"here's an HTTP request, write to this HTTP response" level which servlets provide.

(3) What is the dispatcher servlet?  
Click here to read about [Spring documentation](https://docs.spring.io/spring/docs/3.0.0.M4/reference/html/ch15s02.html)

(4) What order xml configuration applied?  
When configure web.xml file first it will look for root config (appconfig-root.xml)  
```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/servlet-root.xml</param-value>
</context-param>
```
If contextConfiguration is not given spring will look for /WEB-INF/applicationContext.xml file
Then if will look for servelet specific configurations.  
```xml
<servlet>
    <servlet-name>mvc-dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value/>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
```
Since the servelet name is "mvc-dispatcher" it will look for /WEB-INF/mvc-dispatcher-servelet.xml file
>Note: You can have multiple files for different servlets