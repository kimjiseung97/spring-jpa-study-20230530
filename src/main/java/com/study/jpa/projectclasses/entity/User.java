package com.study.jpa.projectclasses.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@ToString
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_email", unique = true, nullable = false)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "user_birth", nullable = false)
    private Date userBirth;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_phone", nullable = false)
    private String userPhone;

    @Column(name = "user_address", nullable = false)
    private String userAddress;

    @Column(name = "user_full_address", nullable = false)
    private String userFullAddress;

    @Column(name = "user_grade", nullable = false, columnDefinition = "varchar(20) default 'user'")
    private String userGrade;

    @Column(name = "user_point", nullable = false)
    private int userPoint;

    @Column(name = "user_image")
    private String userImage;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Ship ship;
}
