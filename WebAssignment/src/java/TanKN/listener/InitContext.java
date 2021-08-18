/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author winnh
 */
public class InitContext implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
            Map<String, String> indexList= new HashMap<>();
            ServletContext context= sce.getServletContext();
            String path=context.getRealPath("/");
            String FILE="WEB-INF\\indexPage.txt";
            String urlFile=path+FILE;
            File file= new File(urlFile);
           try{
               FileReader f= new FileReader(file);
               BufferedReader br= new BufferedReader(f);
               String line;
               StringTokenizer stk;
               String index, value;
               while((line=br.readLine())!=null){
                   stk=new StringTokenizer(line, "=");
                   index=stk.nextToken();
                   value=stk.nextToken();
                   indexList.put(index, value);

               }
               
               context.setAttribute("SITE", indexList);
               br.close();
               f.close();
           }catch(IOException e){
           }        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
   
    }
    
}
