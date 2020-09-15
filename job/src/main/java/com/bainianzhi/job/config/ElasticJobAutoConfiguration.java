package com.bainianzhi.job.config;

import com.bainianzhi.job.annotation.ElasticSimpleJob;
import com.bainianzhi.job.listner.ElasticJobListener;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@RefreshScope
public class ElasticJobAutoConfiguration {

    @Value("${spring.application.name}")
    private String namespace;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void initElasticJob() {
        ZookeeperConfiguration config = new ZookeeperConfiguration("127.0.0.1:2181", namespace);
        config.setSessionTimeoutMilliseconds(1000 * 60 * 30);
        ZookeeperRegistryCenter regCenter = new ZookeeperRegistryCenter(config);
        regCenter.init();
        Map<String, SimpleJob> map = applicationContext.getBeansOfType(SimpleJob.class);
        for (Map.Entry<String, SimpleJob> entry : map.entrySet()) {
            SimpleJob simpleJob = entry.getValue();
            ElasticSimpleJob elasticSimpleJobAnnotation = simpleJob.getClass().getAnnotation(ElasticSimpleJob.class);

            String cron = StringUtils.defaultIfBlank(elasticSimpleJobAnnotation.cron(), elasticSimpleJobAnnotation.value());
            SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(simpleJob.getClass().getName(), cron, elasticSimpleJobAnnotation.shardingTotalCount()).shardingItemParameters(elasticSimpleJobAnnotation.shardingItemParameters()).build(), simpleJob.getClass().getCanonicalName());
            LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build();

            String dataSourceRef = elasticSimpleJobAnnotation.dataSource();
            if (StringUtils.isNotBlank(dataSourceRef)) {

                if (!applicationContext.containsBean(dataSourceRef)) {
                    throw new RuntimeException("not exist datasource [" + dataSourceRef + "] !");
                }

                DataSource dataSource = (DataSource) applicationContext.getBean(dataSourceRef);
                JobEventRdbConfiguration jobEventRdbConfiguration = new JobEventRdbConfiguration(dataSource);
                SpringJobScheduler jobScheduler = new SpringJobScheduler(simpleJob, regCenter, liteJobConfiguration, jobEventRdbConfiguration);
                jobScheduler.init();
            } else {
                SpringJobScheduler jobScheduler = new SpringJobScheduler(simpleJob, regCenter, liteJobConfiguration);
                jobScheduler.init();
            }
        }
    }

    /**
     * 设置活动监听，前提是已经设置好了监听，见下一个目录
     *
     * @return
     */
    @Bean
    public ElasticJobListener elasticJobListener() {
        return new ElasticJobListener(100, 100);
    }
}
