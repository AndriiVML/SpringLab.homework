package com.mlav.springboot.travelagency.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tour_name")
    private String tourName;
    private BigDecimal price;
    @Column(name = "number_of_tours")
    private Integer numberOfTours;
    @Column(name = "number_of_participants")
    private Integer numberOfParticipants;
    @Column(name = "is_hot")
    private Boolean isHot;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "hotel_type_id")
    private Integer hotelTypeId;
    @Column(name = "tour_type_id")
    private Integer tourTypeId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tour", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TourPurchase> orders;
}
