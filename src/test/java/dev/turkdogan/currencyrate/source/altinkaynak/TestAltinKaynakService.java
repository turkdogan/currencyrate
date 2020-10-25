package dev.turkdogan.currencyrate.source.altinkaynak;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import dev.turkdogan.currencyrate.service.Rate;

public class TestAltinKaynakService {

	@Test
	public void contextLoads() {
		String s = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<Kurlar xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
				+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
				+ "<Kur><Kod>USD</Kod><Aciklama>Amerikan Doları</Aciklama><Alis>7.9600</Alis><Satis>7.9700</Satis><GuncellenmeZamani>24.10.2020 14:36:34</GuncellenmeZamani>"
				+ "</Kur><Kur><Kod>EUR</Kod><Aciklama>Avrupa Para Birimi</Aciklama><Alis>9.4150</Alis><Satis>9.4350</Satis><GuncellenmeZamani>24.10.2020 14:36:34</GuncellenmeZamani></Kur>"
				+ "</Kurlar>\n";
		List<Rate> rates = AltinKaynakUtils.decodeRates(s);
		assertEquals(2, rates.size());
		assertEquals("USD", rates.get(0).getCode());
		assertEquals("EUR", rates.get(1).getCode());
	}

}
