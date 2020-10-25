package dev.turkdogan.currencyrate.source.altinkaynak;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import dev.turkdogan.currencyrate.service.Rate;

public class AltinKaynakUtils {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	static List<Rate> decodeRates(String currencyResult) {
		XmlMapper xmlMapper = new XmlMapper();
		List<Rate> rateList = new ArrayList<>();
		try {
			Kurlar kurlar = xmlMapper.readValue(currencyResult, Kurlar.class);
			for (Kur kur : kurlar.getKurlar()) {
				Rate rate = new Rate();
				rate.setCode(kur.getKod());
				rate.setName(kur.getAciklama());
				rate.setSell(kur.getSatis());
				rate.setBuy(kur.getAlis());
				try {
					rate.setLastUpdate(DATE_FORMAT.parse(kur.getGuncellenmeZamani()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				rateList.add(rate);
			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return rateList;
	}

	static List<Rate> decodeGold(String goldResult) {
		XmlMapper xmlMapper = new XmlMapper();
		List<Rate> rateList = new ArrayList<>();
		try {
			Kurlar kurlar = xmlMapper.readValue(goldResult, Kurlar.class);
			for (Kur kur : kurlar.getKurlar()) {
				Rate rate = new Rate();
				rate.setCode(kur.getKod());
				rate.setName(kur.getAciklama());
				rate.setSell(kur.getSatis());
				rate.setBuy(kur.getAlis());
				try {
					rate.setLastUpdate(DATE_FORMAT.parse(kur.getGuncellenmeZamani()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				rateList.add(rate);
			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return rateList;
	}

}
