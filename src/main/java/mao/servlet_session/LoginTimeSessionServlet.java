package mao.servlet_session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project name(项目名称)：Servlet_Session
 * Package(包名): mao.servlet_session
 * Class(类名): LoginTimeSessionServlet
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/24
 * Time(创建时间)： 19:48
 * Version(版本): 1.0
 * Description(描述)： Session 的工作原理
 * 当客户端第一次请求会话对象时，服务器会创建一个 Session 对象，并为该 Session 对象分配一个唯一的 SessionID（用来标识这个 Session 对象）；
 * 服务器将 SessionID 以 Cookie（Cookie 名称为：“JSESSIONID”，值为 SessionID 的值）的形式发送给客户端浏览器；
 * 客户端浏览器再次发送 HTTP 请求时，会将携带 SessionID 的 Cookie 随请求一起发送给服务器；
 * 服务器从请求中读取 SessionID，然后根据 SessionID 找到对应的 Session 对象。
 * Session 与 Cookie 对比
 * Session 和 Cookie 都属于会话技术，都能帮助服务器保存和跟踪用户状态，但两者也存在差异，如下表。
 *
 * 不同点	Cookie	Session
 * 存储位置不同	Cookie 将数据存放在客户端浏览器内存中或硬盘上。	Session 将数据存储在服务器端。
 * 大小和数量限制不同	浏览器对 Cookie 的大小和数量有限制。	Session 的大小和数量一般不受限制。
 * 存放数据类型不同	Cookie 中保存的是字符串。	Session 中保存的是对象。
 * 安全性不同	Cookie 明文传递，安全性低，他人可以分析存放在本地的 Cookie 并进行 Cookie 欺骗。	Session 存在服务器端，安全性较高。
 * 对服务器造成的压力不同	Cookie 保存在客户端，不占用服务器资源。	Session 保存在服务端，每一个用户独占一个 Session。
 * 若并发访问的用户十分多，就会占用大量服务端资源。
 * 跨域支持上不同	Cookie 支持跨域名访问。	Session 不支持跨域名访问。
 * HttpSession 接口定义了一系列对 Session 对象操作的方法
 * 返回值类型	方法	描述
 * long	getCreationTime()	返回创建 Session 的时间。
 * String	getId()	返回获取 Seesion 的唯一的 ID。
 * long	getLastAccessedTime()	返回客户端上一次发送与此 Session 关联的请求的时间。
 * int	getMaxInactiveInterval() 	返回在无任何操作的情况下，Session 失效的时间，以秒为单位。
 * ServletContext	getServletContext() 	返回 Session 所属的 ServletContext 对象。
 * void	invalidate() 	使 Session 失效。
 * void	setMaxInactiveInterval(int interval)	指定在无任何操作的情况下，Session 失效的时间，以秒为单位。负数表示 Session 永远不会失效。
 */

public class LoginTimeSessionServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //设置页面输出的格式
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("欢迎！</h3>");
        // 记录当前的时间
        Date date = new Date();
        //时间的格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //会话创建时间
        Date CreationTime = new Date(request.getSession().getCreationTime());
        //会话上次关联的时间
        Date LastAccessedTime = new Date(request.getSession().getLastAccessedTime());
        //格式化
        String sDate = simpleDateFormat.format(date);
        //将当前时间赋值到session域对象中
        request.getSession().setAttribute("lastTime", sDate);
        //设置会话的失效时间
        request.getSession().setMaxInactiveInterval(100);
        //对session中各个信息输出到页面
        writer.write("<h3>当前时间：" + sDate + "</h3>"
                + "当前会话的SessionID:  " + request.getSession().getId() + "<br/>"
                + "创建此会话的时间为：" + simpleDateFormat.format(CreationTime) + "<br/>"
                + "Sesssion上次关联的时间为：" + simpleDateFormat.format(LastAccessedTime) + "<br/>"
                + "话保持打开状态的最大时间间隔：" + request.getSession().getMaxInactiveInterval() + "<br/>"
        );
        //浏览器不支持COOKIE
        String url = response.encodeURL("/Servlet_Session_war_exploded/SencodTimeServlet");
        writer.write("<a href=" + url + ">再次访问</a>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
