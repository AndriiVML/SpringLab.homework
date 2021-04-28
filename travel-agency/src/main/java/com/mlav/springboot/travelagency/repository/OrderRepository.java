package com.mlav.springboot.travelagency.repository;

import com.mlav.springboot.travelagency.model.Status;
import com.mlav.springboot.travelagency.model.entity.TourPurchase;
import com.mlav.springboot.travelagency.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<TourPurchase, Long> {
    default int getCount() {
        return findAll().size();
    }

    List<TourPurchase> findAllByUser(User user);

}
