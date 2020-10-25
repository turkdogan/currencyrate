package dev.turkdogan.currencyrate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.turkdogan.currencyrate.service.IRateService;
import dev.turkdogan.currencyrate.service.Rate;
import dev.turkdogan.currencyrate.service.RateResult;

@Component
@ViewScoped
public class RateBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(RateBean.class);

	@Autowired
	@Qualifier("altinkaynak")
	private IRateService rateService;

	@Autowired
	@Qualifier("milliyet")
	private IRateService milliyetRateService;

	@Autowired
	@Qualifier("mock")
	private IRateService mocktRateService;

	private RateResult rateResult;

	private RateResult milliyetRateResult;

	public RateResult getRates() {
		if (rateResult == null) {
			refresh();
		}
		return rateResult;
	}

	public void refresh() {
		LOGGER.debug("Refreshing...");
		try {
			RateResult currencyRates = rateService.getCurrencyRates();
			RateResult elementRates = rateService.getElementRates();
			RateResult rateResult = new RateResult();
			rateResult.setLastUpdated(new Date());
			List<Rate> rateList = new ArrayList<>();
			rateList.addAll(currencyRates.getRateList());
			rateList.addAll(elementRates.getRateList());
			rateResult.setRateList(rateList);
			this.rateResult = rateResult;
		} catch (Exception e) {
			LOGGER.error("Refresh error", e);
		}
	}

	public RateResult getMilliyetRates() {
		if (milliyetRateResult == null) {
			refreshMilliyet();
		}
		return milliyetRateResult;
	}

	public void refreshMilliyet() {
		LOGGER.debug("Refreshing...");
		try {
			RateResult currencyRates = milliyetRateService.getCurrencyRates();
			RateResult elementRates = milliyetRateService.getElementRates();
			RateResult rateResult = new RateResult();
			rateResult.setLastUpdated(new Date());
			List<Rate> rateList = new ArrayList<>();
			rateList.addAll(currencyRates.getRateList());
			rateList.addAll(elementRates.getRateList());
			rateResult.setRateList(rateList);
			this.milliyetRateResult = rateResult;
		} catch (Exception e) {
			LOGGER.error("Refresh error", e);
		}
	}

}