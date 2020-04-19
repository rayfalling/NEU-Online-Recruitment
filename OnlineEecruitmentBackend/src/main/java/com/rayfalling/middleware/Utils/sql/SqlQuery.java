package com.Rayfalling.middleware.Utils.sql;

import com.Rayfalling.Shared;
import com.Rayfalling.middleware.Utils.Common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class SqlQuery {
    //映射表
    static HashMap<String, String> SqlMap = new HashMap<String, String>();
    
    static {
        /* Auth 相关Map */
        SqlMap.put("AuthRegister", "auth/register.sql");
        SqlMap.put("AuthLogin", "auth/login.sql");
        SqlMap.put("AuthResetPwd", "auth/reset_password.sql");
    
        /* User 相关Map */
        SqlMap.put("UserQueryId", "user/query_id.sql");
        SqlMap.put("UserUpdateInfo", "user/update_info.sql");
        SqlMap.put("UserUpdatePwd", "user/update_password.sql");
        SqlMap.put("UserSubmitAuthentication", "user/submit_authentication.sql");
    }
    
    /**
     * 从本地资源加载sql查询语句
     */
    public static String getQuery(String name) {
        String path = SqlMap.get(name);
        InputStream inputStream = Common.LoadResource("sql/" + path);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while (true) {
            try {
                if ((length = inputStream.read(buffer)) == -1) break;
            } catch (IOException e) {
                Shared.getLogger().error(e.getMessage());
                e.printStackTrace();
            }
            result.write(buffer, 0, length);
        }
        try {
            return result.toString(StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            Shared.getLogger().error(e.getMessage());
            e.printStackTrace();
            return result.toString();
        }
    }
}