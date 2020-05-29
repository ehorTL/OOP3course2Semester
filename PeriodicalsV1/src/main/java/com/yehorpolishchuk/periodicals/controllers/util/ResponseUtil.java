package com.yehorpolishchuk.periodicals.controllers.util;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
    public static void setCORSEnabledForAll(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
    }
}
