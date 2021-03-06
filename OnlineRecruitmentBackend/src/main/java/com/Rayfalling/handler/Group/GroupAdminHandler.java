package com.Rayfalling.handler.Group;

import com.Rayfalling.Shared;
import com.Rayfalling.middleware.Extensions.DataBaseExt;
import com.Rayfalling.middleware.Utils.sql.SqlQuery;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactiverse.reactivex.pgclient.Tuple;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

import static com.Rayfalling.handler.DatabaseConnection.PgConnectionSingle;

public class GroupAdminHandler {
    
    /**
     * 数据库用户删除圈子申请
     *
     * @return 包含成功的ID的 {@link JsonObject}
     * @author Rayfalling
     */
    public static Single<Integer> DatabaseDeleteGroup(@NotNull JsonObject data) {
        Tuple tuple = Tuple.of(data.getInteger("group_id"), data.getInteger("user_id"), data.getString("reason"));
        return PgConnectionSingle()
                       .flatMap(conn -> conn.rxPreparedQuery(SqlQuery.getQuery("GroupAdminDelete"), tuple))
                       .map(res -> {
                           Row row = DataBaseExt.oneOrNull(res);
                           return row != null ? row.getInteger(0) : -1;
                       })
                       .doOnError(err -> {
                           Shared.getDatabaseLogger().error(err);
                           err.printStackTrace();
                       });
    }
    
    
    /**
     * 数据库警告用户申请
     *
     * @return 包含成功的ID的 {@link JsonObject}
     * @author Rayfalling
     */
    public static Single<Integer> DatabaseGroupWarnUser(@NotNull JsonObject data) {
        Tuple tuple = Tuple.of(data.getInteger("user_id"));
        return PgConnectionSingle()
                       .flatMap(conn -> conn.rxPreparedQuery(SqlQuery.getQuery("GroupAdminWarnUser"), tuple))
                       .map(res -> {
                           Row row = DataBaseExt.oneOrNull(res);
                           return row != null ? row.getInteger(0) : -1;
                       })
                       .doOnError(err -> {
                           Shared.getDatabaseLogger().error(err);
                           err.printStackTrace();
                       });
    }
    
    /**
     * 数据库校验用户身份
     *
     * @return 包含成功的ID的 {@link JsonObject}
     * @author Rayfalling
     */
    public static Single<Boolean> DatabaseGroupAuthAdmin(@NotNull JsonObject data) {
        Tuple tuple = Tuple.of(data.getInteger("group_id"), data.getInteger("user_id"));
        return PgConnectionSingle()
                       .flatMap(conn -> conn.rxPreparedQuery(SqlQuery.getQuery("GroupAdminAuth"), tuple))
                       .map(res -> {
                           Row row = DataBaseExt.oneOrNull(res);
                           return row != null && row.getInteger(0) == 0;
                       })
                       .doOnError(err -> {
                           Shared.getDatabaseLogger().error(err);
                           err.printStackTrace();
                       });
    }
    
    /**
     * 数据库圈子拉黑用户
     *
     * @return 包含成功的ID的 {@link JsonObject}
     * @author Rayfalling
     */
    public static Single<Boolean> DatabaseGroupBanUser(@NotNull JsonObject data) {
        Tuple tuple = Tuple.of(data.getInteger("group_id"), data.getInteger("user_id"));
        return PgConnectionSingle()
                       .flatMap(conn -> conn.rxPreparedQuery(SqlQuery.getQuery("GroupAdminBanUser"), tuple))
                       .map(res -> {
                           Row row = DataBaseExt.oneOrNull(res);
                           return row != null && row.getInteger(0) > 0;
                       })
                       .doOnError(err -> {
                           Shared.getDatabaseLogger().error(err);
                           err.printStackTrace();
                       });
    }
    
    /**
     * 数据库圈子置顶动态
     *
     * @return 包含成功的ID的 {@link JsonObject}
     * @author Rayfalling
     */
    public static Single<Boolean> DatabaseGroupPinPost(@NotNull JsonObject data) {
        Tuple tuple = Tuple.of(data.getInteger("group_id"), data.getInteger("post_id"));
        return PgConnectionSingle()
                       .flatMap(conn -> conn.rxPreparedQuery(SqlQuery.getQuery("GroupAdminPinPost"), tuple))
                       .map(res -> {
                           Row row = DataBaseExt.oneOrNull(res);
                           return row != null && row.getInteger(0) > 0;
                       })
                       .doOnError(err -> {
                           Shared.getDatabaseLogger().error(err);
                           err.printStackTrace();
                       });
    }
}
