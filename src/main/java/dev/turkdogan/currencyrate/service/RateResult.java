package dev.turkdogan.currencyrate.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RateResult {

	private Date lastUpdated;

	private List<Rate> rateList;

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<Rate> getRateList() {
		return this.rateList;
	}

	public void setRateList(List<Rate> rateList) {
		this.rateList = new ArrayList<Rate>(rateList);
	}

	@Override
	public String toString() {
		return "RateResult [lastUpdated=" + lastUpdated + ", rateList=" + Arrays.asList(rateList.toArray()) + "]";
	}
}
