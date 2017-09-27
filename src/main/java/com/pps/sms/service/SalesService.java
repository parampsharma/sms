package com.pps.sms.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pps.sms.domain.Sales;
import com.pps.sms.repository.SalesRepository;
import com.pps.sms.service.dto.SalesDTO;

/**
 * Service class for managing sites.
 */
@Service
@Transactional
public class SalesService {

    private final Logger log = LoggerFactory.getLogger(SalesService.class);
    
    @Inject
    private SalesRepository salesRepository;

    public SalesDTO createSite(SalesDTO salesDTO) {
    	
    	
    	try{
        Sales newSite = new Sales();
        //newSite.setProductCode(salesDTO.getProductCode());
        newSite.setProductName(salesDTO.getProductName());
        newSite.setId(salesDTO.getId());
        newSite = salesRepository.save(newSite);
        salesDTO.setId(newSite.getId());
        log.debug("Created Information for Site: {}", newSite);
        
    	}catch(Exception e){
    		
    		log.debug("Exception while createSite "+ e.getMessage());
    		
    	}
    	return salesDTO;
    }
    
    
/*    public SiteDTO updateSite(SiteDTO siteDTO) {
		log.debug("REST request to update site : {}", siteDTO);
		Site sites = siteRepository.findOne(siteDTO.getId());
		sites.setId(siteDTO.getId());
		sites.setSiteName(siteDTO.getSiteName());
		sites.setSiteIpAddress(siteDTO.getSiteIpAddress());
		sites.setSiteId(siteDTO.getSiteId());
		sites.setFtpPasswrod(siteDTO.getFtpPasswrod());
		sites.setFtpUserName(siteDTO.getFtpUserName());
		sites.setFtpPath(siteDTO.getFtpPath());
		sites.setFtpPathDrop(siteDTO.getFtpPathDrop());
		sites.setKisFile(siteDTO.getKisFile());
		Site siteUpdated  = siteRepository.save(sites);
		log.debug("Update Information for Site: {}", siteUpdated);
		siteDTO.setId(siteUpdated.getId());
		return siteDTO;
	}*/
    
 /*  public  SiteDTO getSite( Long id){
	   Site sites = siteRepository.findOne(id);

		SiteDTO dto = new SiteDTO();
		     dto.setId(sites.getId());
		     dto.setSiteName(sites.getSiteName());
		     dto.setSiteIpAddress(sites.getSiteIpAddress());
		     dto.setSiteId(sites.getSiteId());
		     dto.setFtpPasswrod(sites.getFtpPasswrod());
		     dto.setFtpUserName(sites.getFtpUserName());
		     dto.setFtpPath(sites.getFtpPath());
		     dto.setFtpPathDrop(sites.getFtpPathDrop());
		     dto.setKisFile(sites.getKisFile());
		     
	     return dto;
    	
    }*/
    
}
