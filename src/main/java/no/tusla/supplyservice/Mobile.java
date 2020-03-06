package no.tusla.supplyservice;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mobiles")
@Data
public class Mobile {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(nullable = false)
	    private Integer id;
	 	@Column(name = "company", nullable = false)
	 	private String company;
	 	@Column(name = "model", nullable = false)
		private String model;
		@Column(name = "price", nullable = false)
		private int price;
		@Column(name = "ram_in_gb", nullable = false)
		private int ramInGb;
		@Column(name = "battery_capacity_in_milliamps", nullable = false)
		private int batteryCapacityInMilliAmp;
		@Column(name = "operatingSystem", nullable = false)
		private String operatingSystem;
		@Column(name = "stocks", nullable = false)
		private int stocks;

}
