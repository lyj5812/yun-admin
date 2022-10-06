package com.lyj.gen.config;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.StringUtils;
import com.lyj.gen.dto.DataSourceDto;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JdbcDataSource extends DruidDataSource {

    private int maxActive=10;

    private int initialSize=5;

    private int minIdle=3;

    private int maxWait=30000;

    private int timeBetweenEvictionRunsMillis=60000;

    private int minEvictableIdleTimeMillis=300000;

    private boolean testWhileIdle=true;

    private boolean testOnBorrow=true;

    private boolean testOnReturn=true;

    private String filters="stat,wall";

    private boolean breakAfterAcquireFailure=true;

    private int connectionErrorRetryAttempts=0;

    private static volatile Map<String, DruidDataSource> map = new HashMap<>();

    public synchronized void removeDatasource(String jdbcUrl, String username, String password) {
        String key = getKey(jdbcUrl, username, password);
        if (map.containsKey(key)) {
            map.remove(key);
        }
    }

    public synchronized DruidDataSource getDataSource(DataSourceDto dataSource) {
        String jdbcUrl = "jdbc:mysql://" + dataSource.getHost() + ":" + dataSource.getPort() + "/" + dataSource.getDbName() + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC&useSSL=false";
        String username = dataSource.getUserName();
        String password = dataSource.getPassword();
        String key = getKey(jdbcUrl, username, password);
        if (!map.containsKey(key) || null == map.get(key)) {
            DruidDataSource instance = new JdbcDataSource();
            String className = null;
            try {
                className = DriverManager.getDriver(jdbcUrl.trim()).getClass().getName();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Assert.notNull(className,"Driver Class Not null: DbId=" + dataSource.getSourceId());
            instance.setDriverClassName(className);
            instance.setUrl(jdbcUrl);
            instance.setUsername(username);
            instance.setPassword(password);
            instance.setInitialSize(initialSize);
            instance.setMinIdle(minIdle);
            instance.setMaxActive(maxActive);
            instance.setMaxWait(maxWait);
            instance.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            instance.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            instance.setTestOnBorrow(testOnBorrow);
            instance.setTestOnReturn(testOnReturn);
            instance.setTestWhileIdle(testWhileIdle);
            try {
                instance.setFilters(filters);
            } catch (SQLException ex) {
                log.error("druid configuration initialization filter", ex);
            }
            instance.setConnectionErrorRetryAttempts(connectionErrorRetryAttempts);
            instance.setBreakAfterAcquireFailure(breakAfterAcquireFailure);
            try {
                instance.init();
            } catch (Exception e) {
                log.error("Exception during pool initialization", e);
                throw new IllegalArgumentException(e.getMessage());
            }
            map.put(key, instance);
        }
        return map.get(key);
    }

    private String getKey(String jdbcUrl, String username, String password) {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(username)) {
            sb.append(username);
        }
        if (!StringUtils.isEmpty(password)) {
            sb.append(":").append(password);
        }
        sb.append("@").append(jdbcUrl.trim());
        return SecureUtil.md5(sb.toString());
    }
}
