package com.study.jpa.projectclasses.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@ToString
@Table(name = "ship")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ship_id")
    private int shipId;

    @Column(name = "ship_name", nullable = false)
    private String shipName;

    @Column(name = "ship_location", nullable = false)
    private String shipLocation;

    @Column(name = "ship_description", nullable = false, length = 2000)
    private String shipDescription;

    @Column(name = "ship_serial", nullable = false)
    private String shipSerial;

    @Column(name = "ship_like_count", nullable = false)
    private int shipLikeCount;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
