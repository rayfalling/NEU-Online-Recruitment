package com.Rayfalling.router.Admin;

import com.Rayfalling.Shared;
import io.vertx.reactivex.ext.web.Route;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.jetbrains.annotations.NotNull;

/**
 * 管理员相关路由
 *
 * @author Rayfalling
 */
public class AdminRouter {
    private static final Router router = Router.router(Shared.getVertx());
    
    //静态初始化块
    static {
        String prefix = "/api/admin";
        
        router.get("/").handler(AdminRouter::PostIndex);
        
        for (Route route : router.getRoutes()) {
            if (route.getPath() != null) {
                Shared.getRouterLogger().info(prefix + route.getPath() + " mounted succeed");
            }
        }
    }
    
    public static Router getRouter() {
        return router;
    }
    
    private static void PostIndex(@NotNull RoutingContext context) {
        context.response().end(("This is the index page of admin router.").trim());
    }
}
