package com.springboot.homework4.contributor;

import com.springboot.homework4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TotalUsersInfoContributor implements InfoContributor {
    private final UserRepository userRepository;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> userDetails = new HashMap<>();
        userDetails.put("count", userRepository.getCount());
        builder.withDetail("users", userDetails);
    }
}
