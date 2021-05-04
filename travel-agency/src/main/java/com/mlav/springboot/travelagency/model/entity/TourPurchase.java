package com.mlav.springboot.travelagency.model.entity;

import com.mlav.springboot.travelagency.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "tour_purchase")
//AllArgsConstructor and NoArgsConstructor is for swagger 2
@AllArgsConstructor
@NoArgsConstructor
public class TourPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    private Tour tour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_user_id", referencedColumnName = "account_id")
    private User user;

    @Column(name = "price")
    private BigDecimal actualPrice;
    @Column(name = "number_of_tours")
    private int numberOfTours;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name = "date_of_purchase")
    private LocalDateTime dateTimeOfPurchase;

    //for future https://stackoverflow.com/questions/48526820/convert-a-locale-date-into-mysql-datetime-format
}
