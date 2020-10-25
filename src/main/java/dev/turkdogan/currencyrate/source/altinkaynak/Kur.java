package dev.turkdogan.currencyrate.source.altinkaynak;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Kur {

	@JacksonXmlProperty(localName = "Kod")
	private String kod;

	@JacksonXmlProperty(localName = "Aciklama")
	private String aciklama;

	@JacksonXmlProperty(localName = "Alis")
	private Double alis;

	@JacksonXmlProperty(localName = "Satis")
	private Double satis;

	@JacksonXmlProperty(localName = "GuncellenmeZamani")
	private String guncellenmeZamani;

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Double getAlis() {
		return alis;
	}

	public void setAlis(Double alis) {
		this.alis = alis;
	}

	public Double getSatis() {
		return satis;
	}

	public void setSatis(Double satis) {
		this.satis = satis;
	}

	public String getGuncellenmeZamani() {
		return guncellenmeZamani;
	}

	public void setGuncellenmeZamani(String guncellenmeZamani) {
		this.guncellenmeZamani = guncellenmeZamani;
	}

	@Override
	public String toString() {
		return "Kur [kod=" + kod + ", aciklama=" + aciklama + ", alis=" + alis + ", satis=" + satis
				+ ", guncellemeTarihi=" + guncellenmeZamani + "]";
	}
}
