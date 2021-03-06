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
        SqlMap.put("AuthLogin", "auth/login.sql");
        SqlMap.put("AuthRegister", "auth/register.sql");
        SqlMap.put("AuthResetPwd", "auth/reset_password.sql");
        SqlMap.put("AuthQueryIdentity", "auth/query_identity.sql");
        SqlMap.put("AuthQueryUserBanned", "auth/query_banned.sql");
        
        /* User 相关Map */
        SqlMap.put("UserProfile", "user/profile.sql");
        SqlMap.put("UserQueryId", "user/query_id.sql");
        SqlMap.put("UserUpdateInfo", "user/update_info.sql");
        SqlMap.put("UserQueryQuota", "user/query_quota.sql");
        SqlMap.put("UserUpdatePwd", "user/update_password.sql");
        SqlMap.put("UserSubmitAuthentication", "user/submit_authentication.sql");
        
        /* Position 相关Map */
        SqlMap.put("PositionNew", "position/new.sql");
        SqlMap.put("PositionDelete", "position/delete.sql");
        SqlMap.put("PositionFavour", "position/favour.sql");
        SqlMap.put("PositionSearch", "position/search.sql");
        SqlMap.put("PositionQueryCategory", "position/query_category.sql");
        
        /* Group 相关Map */
        SqlMap.put("GroupNew", "group/new.sql");
        SqlMap.put("GroupJoin", "group/join.sql");
        SqlMap.put("GroupSearch", "group/search.sql");
        SqlMap.put("GroupQueryInfo", "group/query_info.sql");
        SqlMap.put("GroupQueryCategory", "group/query_category.sql");
        
        /* Post 相关Map */
        SqlMap.put("PostNewPost", "post/new.sql");
        SqlMap.put("PostLikePost", "post/like.sql");
        SqlMap.put("PostComment", "post/comment.sql");
        SqlMap.put("PostDeletePost", "post/delete.sql");
        SqlMap.put("PostUpdatePost", "post/update.sql");
        SqlMap.put("PostFetchAll", "post/fetch_all.sql");
        SqlMap.put("PostFavoritePost", "post/favorite.sql");
        SqlMap.put("PostCommentReply", "post/comment_reply.sql");
        SqlMap.put("PostFetchComment", "post/fetch_comment.sql");
    
        /* Group Admin 相关Map */
        SqlMap.put("GroupAdminAuth", "group/admin/auth.sql");
        SqlMap.put("GroupAdminBanUser", "group/admin/ban.sql");
        SqlMap.put("GroupAdminPinPost", "group/admin/pin.sql");
        SqlMap.put("GroupAdminDelete", "group/admin/delete.sql");
        SqlMap.put("GroupAdminWarnUser", "group/admin/warn.sql");
    
        /* Admin 相关Map */
        SqlMap.put("AdminPinPost", "admin/pin.sql");
        SqlMap.put("AdminUserBan", "admin/ban.sql");
        SqlMap.put("AdminFetchAuth", "admin/auth.sql");
        SqlMap.put("AdminFetchUser", "admin/user.sql");
        SqlMap.put("AdminUserSuper", "admin/super.sql");
        SqlMap.put("AdminConfirmAuth", "admin/confirm.sql");
    
        /* Recommend 相关Map */
        SqlMap.put("RecommendList", "recommend/list.sql");
        SqlMap.put("RecommendSaveMap", "recommend/save_map.sql");
        SqlMap.put("RecommendSaveUser", "recommend/save_user.sql");
        SqlMap.put("RecommendLoadUser", "recommend/load_user.sql");
        SqlMap.put("RecommendLoadPosition", "recommend/load_position.sql");
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
