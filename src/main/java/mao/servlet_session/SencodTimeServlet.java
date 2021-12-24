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
 * Class(类名): SencodTimeServlet
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/24
 * Time(创建时间)： 19:51
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class SencodTimeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //从session中获取上次访问的时间
        String value = (String) request.getSession().getAttribute("lastTime");
        //不是第一次访问
        writer.write("<h3>您上次的时间是" + value + "</h3>");
        Date date = new Date();
        //时间的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化
        String sDate = sdf.format(date);
        //将当前时间赋值到session域对象中
        request.getSession().setAttribute("lastTime", sDate);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
