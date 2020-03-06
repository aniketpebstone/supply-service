package no.tusla.supplyservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepo extends JpaRepository<Mobile, Integer> {
	public List<Mobile> findByCompany(String company);
	public Mobile findOneByCompanyAndModel(String company,String model);
}
