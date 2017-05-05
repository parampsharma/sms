package com.pps.sms.service.dto;

import java.time.ZonedDateTime;

import com.pps.sms.domain.AbstractAuditingEntity;
import com.pps.sms.domain.Sales;

/**
 * A DTO representing a site.
 */
public class SalesDTO extends AbstractAuditingEntity {

	private Long id;
	private String productCode;
	private String productName;
    private ZonedDateTime createdDate;
	private String createdBy;


    public SalesDTO() {
    }

    public SalesDTO(Sales sales) {
        this(sales.getId(),sales.getProductCode(),sales.getProductName(),sales.getCreatedDate(),sales.getCreatedBy());
    }

    public SalesDTO(Long id ,String productCode, String productName ,ZonedDateTime createdDate,String createdBy) {

    	this.id = id;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "SalesDTO [id=" + id + ", productCode=" + productCode + ", productName=" + productName + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + "]";
	}
    


}
