package no.tusla.supplyservice;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "supply")
public class SupplyController {
 
  @Autowired
  SupplyService supplyService;

	@GetMapping(value = "/{company}/{model}")
	public  ResponseEntity<?> checkAvailableMobiles(
			@PathVariable("company") String company,
			@PathVariable("model") String model){
		log.info("~ In checkAvailableMobiles ~");
		log.info("company:{} model:{}",company,model);
		Mobile mobile = supplyService.checkDeliveryForMobile(company, model);
		log.info("mobile:{}",mobile);
		if(mobile==null)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(mobile);
	}
	
	@PostMapping()
	public ResponseEntity<?> placeSupplyOrderForMobile(@Valid @RequestBody MobileSupplyDto dto){

		log.info("~ In placeSupplyOrderForMobile ~");
		log.info("MobileSupplyDto:{}",dto);
		supplyService.placeSupplyOrder(dto);
		return ResponseEntity.accepted().build();
		
	}
	
}
