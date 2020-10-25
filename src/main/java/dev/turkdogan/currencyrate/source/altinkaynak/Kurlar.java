package dev.turkdogan.currencyrate.source.altinkaynak;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Kurlar {

	@JacksonXmlProperty(localName = "Kur")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Kur> kurlar;

	public List<Kur> getKurlar() {
		return kurlar;
	}

	public void setKurlar(List<Kur> kurlar) {
		this.kurlar = kurlar;
	}
}
