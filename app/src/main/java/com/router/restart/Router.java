
package com.router.restart;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author van
 *
 */
public class Router {
    private static final String USERNAME_PASSWORD = "max:max";
    public Router() throws IOException {
        this.disconnect();
    }

    //http://192.168.3.1/do_cmd.htm?CMD=SYS_CONF&CCMD=0&nowait=1 restart the router
    private void disconnect() throws IOException {
        String urlStr = "http://192.168.1.1/userRpm/SysRebootRpm.htm?Reboot=Reboot";
        this.runCgi(urlStr, USERNAME_PASSWORD);
    }

    private void runCgi(String urlStr, String authorizationStr) throws IOException {
        URL xUrl = null;
        HttpURLConnection xHuc = null;
        xUrl = new URL(urlStr);
        if (xUrl != null) {
            xHuc = (HttpURLConnection) xUrl.openConnection();
            if (xHuc != null) {
                if (!"".equals(authorizationStr)) {
                    xHuc.setRequestProperty("Authorization", "Basic bWF4Om1heA==");
                }
                xHuc.setRequestProperty("Content-Length", "0");
                xHuc.setRequestProperty("Referer", "http://192.168.1.1/userRpm/SysRebootRpm.htm");
                xHuc.setRequestProperty("Upgrade-Insecure-Requests", "1");
                xHuc.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                xHuc.connect();
                String aa = xHuc.getResponseMessage();
                ////The following are used to get the IP address.
                InputStream in=xHuc.getInputStream();
                int chint=0;
                StringBuffer sb=new StringBuffer();
                while((chint=in.read())!=-1){
                    sb.append((char)chint);
                }
                String html=sb.toString();
                System.out.println(html);
            }
        }
    }

} 