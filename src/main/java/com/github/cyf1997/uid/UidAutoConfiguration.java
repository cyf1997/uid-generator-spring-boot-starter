package com.github.cyf1997.uid;

import com.github.cyf1997.uid.impl.CachedUidGenerator;
import com.github.cyf1997.uid.impl.DefaultUidGenerator;
import com.github.cyf1997.uid.worker.DisposableWorkerIdAssigner;
import com.github.cyf1997.uid.worker.WorkerIdAssigner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 *
 * <p>
 *     https://github.com/cyf1997/uid-generator-spring-boot-starter.git
 * </p>
 * uid 基础自动配置类
 * @author Yunfei Cheng
 * @date 2022/08/12 08:59
 */
@Configuration
@ConditionalOnClass({ DefaultUidGenerator.class, CachedUidGenerator.class })
@MapperScan({"com.github.cyf1997.uid.worker.dao"})
public class UidAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Lazy
    DefaultUidGenerator defaultUidGenerator(){
        return new DefaultUidGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    @Lazy
    CachedUidGenerator cachedUidGenerator(){
        return new CachedUidGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    WorkerIdAssigner workerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }
}
