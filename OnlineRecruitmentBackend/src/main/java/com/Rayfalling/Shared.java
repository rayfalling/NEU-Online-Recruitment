package com.Rayfalling;

import io.reactiverse.reactivex.pgclient.PgPool;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Shared {
    private static final Logger RouterLogger = LogManager.getLogger("Router");
    private static final Logger DatabaseLogger = LogManager.getLogger("Database");
    private static final Logger logger = LogManager.getLogger("Main");
    private static final Vertx vertx = Vertx.vertx();
    private static HttpServer httpServer;
    /**
     * 工程 Postgresql 数据库连接池对象
     */
    private static PgPool pgPool = null;
    
    public static Logger getDatabaseLogger() {
        return DatabaseLogger;
    }
    
    public static Logger getLogger() {
        return logger;
    }
    
    public static Logger getRouterLogger() {
        return RouterLogger;
    }
    
    public static Vertx getVertx() {
        return vertx;
    }
    
    public static PgPool getPgPool() {
        return pgPool;
    }
    
    public static void setPgPool(PgPool pgPool) {
        Shared.pgPool = pgPool;
    }
    
    public static HttpServer getHttpServer() {
        return httpServer;
    }
    
    public static void setHttpServer(HttpServer httpServer) {
        Shared.httpServer = httpServer;
    }
}
