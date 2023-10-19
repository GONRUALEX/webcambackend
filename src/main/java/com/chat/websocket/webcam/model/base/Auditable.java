package com.chat.websocket.webcam.model.base;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
	
	@Column(name="USUARIO_CREACIO")
	@CreatedBy
	protected String createdBy;

	@Column(name = "DATA_CREACIO")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;
	
	@Column(name = "DATA_MODIFICACIO")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastMofifiedDate;
	
	@Column(name="USUARI_MODIFICACIO")
	@CreatedBy
	protected String modifiedBy;
	
	@Version
	@Column(name = "VERSION")
	protected Long version;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastMofifiedDate() {
		return lastMofifiedDate;
	}

	public void setLastMofifiedDate(Date lastMofifiedDate) {
		this.lastMofifiedDate = lastMofifiedDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
}
