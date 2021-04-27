package com.mlav.springboot.travelagency.model.entity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    @Column(name = "role_id")
    private int roleId;

}
