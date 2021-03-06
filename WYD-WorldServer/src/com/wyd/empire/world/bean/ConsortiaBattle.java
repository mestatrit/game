package com.wyd.empire.world.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the tab_playersinconsortia database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name = "log_consortiabattle")
public class ConsortiaBattle implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer playerId;
	private Integer conId;
	private Integer status;
	private Date time;
	private Integer vsType;

	public ConsortiaBattle() {
	}

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Basic()
	@Column(name = "playerId", precision = 10)
	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	@Basic()
	@Column(name = "conId", precision = 10)
	public Integer getConId() {
		return conId;
	}

	public void setConId(Integer conId) {
		this.conId = conId;
	}

	@Basic()
	@Column(name = "status", precision = 10)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Basic()
	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Basic()
	@Column(name = "vsType", precision = 10)
	public Integer getVsType() {
		return vsType;
	}

	public void setVsType(Integer vsType) {
		this.vsType = vsType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ConsortiaBattle)) {
			return false;
		}
		ConsortiaBattle castOther = (ConsortiaBattle) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
}