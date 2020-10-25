package dev.turkdogan.currencyrate.service;

import java.util.Date;

public class Rate {

    private String code;

    private String name;

    // TODO consider Big Number
    private Double buy;

    private Double sell;
    
    private Date lastUpdate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBuy() {
		return buy;
	}

	public void setBuy(Double buy) {
		this.buy = buy;
	}

	public Double getSell() {
		return sell;
	}

	public void setSell(Double sell) {
		this.sell = sell;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Rate [code=" + code + ", name=" + name + ", buy=" + buy + ", sell=" + sell + ", lastUpdate="
				+ lastUpdate + "]";
	}
}