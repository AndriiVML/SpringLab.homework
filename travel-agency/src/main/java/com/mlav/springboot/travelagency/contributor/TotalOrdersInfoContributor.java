package com.mlav.springboot.travelagency.contributor;

import com.mlav.springboot.travelagency.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TotalOrdersInfoContributor implements InfoContributor {
    private final OrderRepository orderRepository;
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("count", orderRepository.getCount());
        builder.withDetail("orders", orderDetails);
    }
}
