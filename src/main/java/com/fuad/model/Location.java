package com.fuad.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "locations")
public class Location implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "location_name")
    private String locationName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "location")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "location")
    private Status status;

}
