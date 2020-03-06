package no.tusla.supplyservice;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
public class StockReqDto {
    private Integer id;
 	private String company;
	private String model;
	private int quantity;


}