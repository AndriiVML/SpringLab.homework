package com.mlav.springboot.travelagency.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
public class User {

    @Id
    @Column(name = "account_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "is_blocked")
    private Boolean isBlocked;
    @Column(unique = true)
    private String email;
    private Integer discount;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public void setId(Long id) {
        this.id = id;
        account.setId(id);
    }
}
