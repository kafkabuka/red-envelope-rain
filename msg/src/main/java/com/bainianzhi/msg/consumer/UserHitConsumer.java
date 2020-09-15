package com.bainianzhi.msg.consumer;

import com.bainianzhi.common.config.RabbitKeys;
import com.bainianzhi.common.entity.UserHitEntity;
import com.bainianzhi.msg.feign.ApiServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户中奖记录消费者
 */
@Component
@RabbitListener(queues = RabbitKeys.QUEUE_HIT)
@Slf4j
public class UserHitConsumer {

    @Autowired
    private ApiServiceFeign apiServiceFeign;

    @RabbitHandler
    public void processMessage(UserHitEntity userHitEntity) {
        log.info("用户中奖记录：{}号用户参与了{}号活动，并在{}中奖了，奖品id为{}",userHitEntity.getUserid(),userHitEntity.getActivityid(),
                userHitEntity.getHittime(),userHitEntity.getPrizesid());
        apiServiceFeign.saveUserHit(userHitEntity);
    }

}
