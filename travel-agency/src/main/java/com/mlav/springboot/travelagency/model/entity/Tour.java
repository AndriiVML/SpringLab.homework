package com.mlav.springboot.travelagency.model.entity;

import com.mlav.springboot.travelagency.model.HotelType;
import com.mlav.springboot.travelagency.model.TourType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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
}
