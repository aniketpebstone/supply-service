package no.tusla.supplyservice;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SupplyService 
{
	@Autowired
	SupplyRepo repo;
	
	@Autowired
	RestTemplate restTemplate;
		
		
	public Mobile checkDeliveryForMobile(String company,String model) {
		log.info("~ In checkDeliveryForMobile ~");		
		return repo.findOneByCompanyAndModel(company,model);
	}
	
	@Transactional
	public void placeSupplyOrder(MobileSupplyDto dto) {
		log.info("~ In placeSupplyOrder ~");
		final String uri = "http://localhost:8083/stock";
		Mobile mobile=checkDeliveryForMobile(dto.getCompany(),dto.getModel());
		log.info("mobile:{}",mobile);
		int diff=mobile.getStocks() - dto.getQuantity();
		log.info("diff:{}",diff);
		if(diff < 0 )
		{
			log.info("Calling stock service....");
			ResponseEntity<?> stockResp=restTemplate.postForEntity(uri,dto, StockReqDto.class);
			log.info("Response from stock service:{}",stockResp);
			throw new OutOfStockException(dto.getCompany()+" "+dto.getModel()+" are currently out of stock!");
		}
		else if(diff < 10)
		{
			log.info("Calling stock service....");
			ResponseEntity<?> stockResp=restTemplate.postForEntity(uri,dto, StockReqDto.class);
			log.info("Response from stock service:{}",stockResp);
			mobile.setStocks(diff);
			repo.save(mobile);
		}
		else
		{
			mobile.setStocks(diff);
			repo.save(mobile);
		}
		
	}

}
