package com.pps.sms.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.pps.sms.service.SalesService;
import com.pps.sms.service.dto.SalesDTO;
import com.pps.sms.web.rest.util.HeaderUtil;
import com.pps.sms.web.rest.util.PaginationUtil;

/**
 * REST controller for managing sites.
 */
@RestController
@RequestMapping("/api")
public class SalesResource {

    private final Logger log = LoggerFactory.getLogger(SalesResource.class);

    @Inject
    private SalesService salesService;
  
    private HttpStatus httpStatus  					= HttpStatus.OK;
    

    /**
     * POST  /site  : Creates a new site.
     */
    @PostMapping("/sales")
    @Timed
    public ResponseEntity<?> createSite(@RequestBody SalesDTO salesDTO) throws URISyntaxException {
    	log.debug("REST request to save Site : {}", salesDTO);
    	
    	//Locale locale = appUtil.getLocale();
        String      msg        =  null;
		HttpHeaders headers    = null;
		headers	= new HttpHeaders();
		
		SalesDTO sales = salesService.createSite(salesDTO);
            //msg = site.getSiteName()+" : "+ZDHResourceBundle.getResourceMap(locale,ZDHExceptionConstant.MsgSuccess);
            
            headers.add("responseHeaderMessage", msg);
    		return new ResponseEntity<>(sales, headers, httpStatus);
            //return new ResponseEntity<>(site, HttpStatus.OK);
        }
    
    /**
     * this method  get all sites.
     * @param pageable
     * @return the ResponseEntity with status 200 (OK)
     * @throws URISyntaxException
     */
    
    /*@RequestMapping(value = "/site",
            method   = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<SiteDTO>> getSites(Pageable pageable)
            throws URISyntaxException {
        List<SiteDTO> siteDTOs = new ArrayList<SiteDTO>();
        log.debug("REST request to get a page of sites");
        Page<Site> page = siteRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/site");
        
        List<Site> siteList = page.getContent().stream().collect(Collectors.toList());
        
        siteList.forEach(site ->{
        	SiteDTO dto = new SiteDTO();
        	BeanUtils.copyProperties(site, dto);
        	siteDTOs.add(dto);
        });
        return new ResponseEntity<>(siteDTOs, headers, HttpStatus.OK);
    } 
    */
    /**
     * this method use for single site.
     * @param id
     * @return the ResponseEntity with status 200 (OK)
     * @throws URISyntaxException
     */

	/*@RequestMapping(value = "/site/{id}",
			method   = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<SiteDTO> getSite(@PathVariable Long id)
			throws URISyntaxException {
		     log.debug("Entering in get site");
		     SiteDTO result = siteService.getSite(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    */
    /**
     * this method use for update record
     * @param siteDTO
     * @return the ResponseEntity with status 200 (OK)
     * @throws URISyntaxException
     */
    
	/*@RequestMapping(value = "/site",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public  ResponseEntity<SiteDTO> updateSite(@RequestBody SiteDTO siteDTO) throws URISyntaxException {
		log.debug("REST request to update site : {}", siteDTO);
		
    	Locale locale = appUtil.getLocale();
        String      msg        =  null;
		HttpHeaders headers    = null;
		headers	= new HttpHeaders();
		
		SiteDTO result = siteService.updateSite(siteDTO);
		
		msg = result.getSiteName()+" : "+ZDHResourceBundle.getResourceMap(locale,ZDHExceptionConstant.MsgSuccessUpdate);
        headers.add("responseHeaderMessage", msg);
		return new ResponseEntity<>(result, headers, httpStatus);
		
		
		//return new ResponseEntity<>(result, HttpStatus.OK);
	}*/
	
	/**
	 * DELETE  /sites/:id : delete the "id" site.
	 *
	 * @param id the id of the SiteDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	/*@RequestMapping(value = "/site/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
		log.debug("REST request to delete site : {}", id);
		siteRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("Site", id.toString())).build();
	}*/
	
	/**
	 * Validate site name
	 */
	/*@RequestMapping(value = "/site-name/{siteName}/{fieldName}/{id}",
			method = RequestMethod.GET,
			produces = MediaType.TEXT_PLAIN_VALUE)
	@Timed
	public ResponseEntity<String> validateSiteName(@PathVariable String siteName, @PathVariable String fieldName,  @PathVariable String id) {
		log.debug("REST request to validate site name: {}", siteName);
		int count =  0;
		if(fieldName.equalsIgnoreCase("siteName")){

			count = siteRepository.countBySiteNameIgnoreCase(siteName);
		}else if(fieldName.equalsIgnoreCase("siteIpAddress")){

			List<Site>  siteList= siteRepository.findBySiteIpAddress(siteName);
			
			if(siteList.size() == 1){
				if(((Site)siteList.get(0)).getId() == Long.parseLong(id)){
					count = 0;
				} else if(   ((Site)siteList.get(0)).getId() != Long.parseLong(id)){
					count = 1;
				}
			
			}
			
		}
		else if(fieldName.equalsIgnoreCase("siteId")&& siteName.matches("[0-9]+")){

			count = siteRepository.countBySiteId(Integer.parseInt(siteName));
		}

		if(count>0){	
			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
}

	
    
