package com.mlav.springboot.travelagency.repository;

import com.mlav.springboot.travelagency.model.entity.Tour;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    default int getCountOfDisabledTours() {
        return findAllByIsDeletedTrue().size();
    }

    default int getCountOfActiveTours() {
        return findAllByIsDeletedFalse().size();
    }

    default int getCount() {
        return findAll().size();
    }

    Optional<Tour> findByIdAndIsDeletedFalse(Long aLong);

    List<Tour> findAllByIsDeletedFalse();

    List<Tour> findAllByIsDeletedFalse(Sort sort);

    @Query("SELECT t FROM Tour t  WHERE t.isDeleted = true")
    List<Tour> findAllByIsDeletedTrue();
}
