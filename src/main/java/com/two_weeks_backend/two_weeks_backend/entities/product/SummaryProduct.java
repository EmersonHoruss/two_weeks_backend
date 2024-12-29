package com.two_weeks_backend.two_weeks_backend.entities.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "summary_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SummaryProduct extends BaseEntity {

}
