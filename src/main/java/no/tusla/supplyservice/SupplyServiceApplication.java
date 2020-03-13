package no.tusla.supplyservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class SupplyServiceApplication implements CommandLineRunner{
	

	@Autowired
	SupplyRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(SupplyServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<?, ?>> mobiles = objectMapper.readValue(new ClassPathResource("mobiles.json").getFile(), List.class);
        List<Mobile> mobileList=new ArrayList<>();
        for(Map<?, ?> map:mobiles) {
        	Mobile mobile =new Mobile();        	
        		mobile.setCompany(map.get("company").toString());
        		mobile.setModel(map.get("model").toString());
        		mobile.setPrice(new Integer(map.get("price").toString()));
        		mobile.setRamInGb(new Integer(map.get("ram_in_gb").toString()));
        		mobile.setBatteryCapacityInMilliAmp(new Integer(map.get("battery_capacity_in_milliamps").toString()));
        		mobile.setOperatingSystem(map.get("operatingSystem").toString());
        		mobile.setStocks(new Integer(map.get("stocks").toString()));
        		mobileList.add(mobile);
        	}                
        log.info("Initializing mobile database... {}",mobileList);
        repo.saveAll(mobileList);        
        }
	}

