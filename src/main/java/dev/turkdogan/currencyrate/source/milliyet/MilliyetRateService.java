package dev.turkdogan.currencyrate.source.milliyet;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.turkdogan.currencyrate.service.IRateService;
import dev.turkdogan.currencyrate.service.Rate;
import dev.turkdogan.currencyrate.service.RateResult;

@Service("milliyet")
@Primary
public class MilliyetRateService implements IRateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MilliyetRateService.class);

	@Override
	public RateResult getCurrencyRates() {
		List<Rate> rateList = new ArrayList<>();
		NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
		Document doc;
		try {
			doc = Jsoup.connect("https://uzmanpara.milliyet.com.tr/altin-fiyatlari").get();
			Elements elements = doc.select("#usd_header_son_data");
			Rate rate = new Rate();
			rate.setCode("USD");
			rate.setName("US Dollars");
			rate.setSell(nf.parse(elements.first().text().replace(".", "")).doubleValue());
			rate.setBuy(rate.getSell());
			rateList.add(rate);

			elements = doc.select("#eur_header_son_data");
			rate = new Rate();
			rate.setCode("EUR");
			rate.setName("Euro");
			rate.setSell(nf.parse(elements.first().text().replace(".", "")).doubleValue());
			rate.setBuy(rate.getSell());
			rateList.add(rate);

			elements = doc.select("#gld_header_son_data");
			rate = new Rate();
			rate.setCode("GOLD");
			rate.setName("Gold");
			rate.setSell(nf.parse(elements.first().text().replace(".", "")).doubleValue());
			rate.setBuy(rate.getSell());
			rateList.add(rate);

		} catch (IOException | ParseException e) {
			LOGGER.error("", e);
		}
		RateResult rateResult = new RateResult();
		rateResult.setLastUpdated(new Date());
		rateResult.setRateList(rateList);
		return rateResult;
	}

	@Override
	public RateResult getElementRates() {
		List<Rate> rateList = new ArrayList<>();

		try {
			Document doc = Jsoup.connect("https://uzmanpara.milliyet.com.tr/altin-fiyatlari").get();
			Elements elements = doc.select(".detL table tbody tr td");

			ListIterator<Element> iterator = elements.listIterator();
			Rate rate = null;
			int index = 0;
			NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
			while (iterator.hasNext()) {
				Element e = iterator.next();
				if (index % 6 == 0) {
					if (rate != null) {
						rateList.add(rate);
					}
					rate = new Rate();
				} else if (index % 6 == 1) {
					rate.setName(e.text());
				} else if (index % 6 == 2) {
					try {
						rate.setBuy(nf.parse(e.text().replace(".", "")).doubleValue());
					} catch (ParseException e1) {
						LOGGER.error(e.text(), e1);
					}
				} else if (index % 6 == 3) {
					try {
						rate.setSell(nf.parse(e.text().replace(".", "")).doubleValue());
					} catch (ParseException e1) {
						LOGGER.error(e.text(), e1);
					}
				} else if (index % 6 == 4) {
					// skip degisim value
				} else if (index % 6 == 5) {
					// consider java-8 time api
					Calendar cal = Calendar.getInstance();
					String[] splitted = e.text().split(":");
					cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitted[0]));
					cal.set(Calendar.MINUTE, Integer.parseInt(splitted[1]));
					rate.setLastUpdate(cal.getTime());
				}
				index++;
			}
		} catch (IOException e) {
			LOGGER.error("", e);
		}
		RateResult rateResult = new RateResult();
		rateResult.setLastUpdated(new Date());
		rateResult.setRateList(rateList);
		return rateResult;
	}
}
