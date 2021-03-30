package com.dvlcube.app.manager.data;

import javax.persistence.*;

import com.dvlcube.utils.interfaces.MxBean;
import com.dvlcube.utils.interfaces.Nameable;
import com.dvlcube.utils.interfaces.Presentable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class JobBean implements MxBean<Long>, Nameable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Integer max;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
}
