package com.training.jpa.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "ADDRESS", schema="LOCAL")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_ID_SEQ_GEN")
    @SequenceGenerator(name = "ADDRESS_ID_SEQ_GEN", sequenceName = "ADDRESS_ID_SEQ", allocationSize = 1)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "AREA")
    private String area;
    
    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private User user;

}

