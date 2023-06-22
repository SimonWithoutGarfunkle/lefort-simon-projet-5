package com.safetynetjson.safetynetjson.model;

import jakarta.validation.constraints.NotBlank;

public class Firestation {	

	@NotBlank(message="Address is required")
	private String address;
    
    private Long station;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getStation() {
		return station;
	}

	public void setStation(Long station) {
		this.station = station;
	}

}
