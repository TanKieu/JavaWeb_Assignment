/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author winnh
 */
public class Init implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Chay cai nay khi deploy");
        // can map vao web.xml
        ServletContext context = sce.getServletContext();
        String path = context.getRealPath("/");
        String FILE = "WEB-INF\\indexPage.txt";
        String url = path + FILE;
        System.out.println(url);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // khong dung thif bo trong
    }

}
