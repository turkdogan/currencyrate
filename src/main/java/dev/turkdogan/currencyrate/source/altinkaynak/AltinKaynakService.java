package dev.turkdogan.currencyrate.source.altinkaynak;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.turkdogan.currencyrate.service.IRateService;
import dev.turkdogan.currencyrate.service.Rate;
import dev.turkdogan.currencyrate.service.RateResult;
import dev.turkdogan.currencyrate.source.altinkaynak.ws.GetCurrencyResponse;
import dev.turkdogan.currencyrate.source.altinkaynak.ws.GetGoldResponse;

@Service("altinkaynak")
public class AltinKaynakService implements IRateService {

	@Autowired
	private AltinKaynakClient altinKaynakClient;

	@Override
	public RateResult getCurrencyRates() {
		GetCurrencyResponse currency = altinKaynakClient.getCurrency();
		List<Rate> rateList = AltinKaynakUtils.decodeRates(currency.getGetCurrencyResult());
		RateResult rateResult = new RateResult();
		rateResult.setLastUpdated(new Date());
		rateResult.setRateList(rateList);
		return rateResult;
	}

	@Override
	public RateResult getElementRates() {
		GetGoldResponse gold = altinKaynakClient.getGold();
		List<Rate> rateList = AltinKaynakUtils.decodeGold(gold.getGetGoldResult());
		rateList.addAll(rateList);

		RateResult rateResult = new RateResult();
		rateResult.setLastUpdated(new Date());
		rateResult.setRateList(rateList);
		return rateResult;
	}

}
