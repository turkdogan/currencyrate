package dev.turkdogan.currencyrate.source.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import dev.turkdogan.currencyrate.service.IRateService;
import dev.turkdogan.currencyrate.service.Rate;
import dev.turkdogan.currencyrate.service.RateResult;

@Service("mock")
public class MockRateService implements IRateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MockRateService.class);

	@Override
	public RateResult getCurrencyRates() {
		List<Rate> rateList = new ArrayList<>();
		Rate rate = new Rate();
		rate.setCode("USD");
		rate.setName("US Dollars");
		rate.setLastUpdate(new Date());
		rate.setBuy(7.9680);
		rate.setSell(7.9580);
		rateList.add(rate);

		rate = new Rate();
		rate.setCode("EUR");
		rate.setName("Euro");
		rate.setLastUpdate(new Date());
		rate.setBuy(8.9680);
		rate.setSell(8.9580);
		rateList.add(rate);
		RateResult rateResult = new RateResult();
		rateResult.setRateList(rateList);
		rateResult.setLastUpdated(new Date());
		return rateResult;
	}

	@Override
	public RateResult getElementRates() {
		List<Rate> rateList = new ArrayList<>();
		Rate rate = new Rate();
		rate.setCode("AU");
		rate.setName("Gold");
		rate.setLastUpdate(new Date());
		rate.setBuy(70.9680);
		rate.setSell(7.09580);
		rateList.add(rate);

		RateResult rateResult = new RateResult();
		rateResult.setRateList(rateList);
		rateResult.setLastUpdated(new Date());
		return rateResult;
	}
}
