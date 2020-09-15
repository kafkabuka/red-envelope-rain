package com.bainianzhi.msg.consumer;

import com.bainianzhi.common.config.RabbitKeys;
import com.bainianzhi.common.entity.UserActivityEntity;
import com.bainianzhi.msg.feign.ApiServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户参与活动消费者
 */
@Component
@RabbitListener(queues = RabbitKeys.QUEUE_PLAY)
@Slf4j
public class UserActivityConsumer {

    @Autowired
    private ApiServiceFeign apiServiceFeign;

    @RabbitHandler
    public void processMessage(UserActivityEntity userActivityEntity) {
        log.info("用户参与抽奖记录：{}号用户在{}参与了{}号活动",userActivityEntity.getUserid(),
                userActivityEntity.getCreatetime(),userActivityEntity.getActivityid());
        apiServiceFeign.saveUserActivity(userActivityEntity);
    }


}
