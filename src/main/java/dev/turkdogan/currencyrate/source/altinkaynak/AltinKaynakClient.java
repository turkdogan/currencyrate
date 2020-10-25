package dev.turkdogan.currencyrate.source.altinkaynak;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringSource;

import dev.turkdogan.currencyrate.source.altinkaynak.ws.GetCurrency;
import dev.turkdogan.currencyrate.source.altinkaynak.ws.GetCurrencyResponse;
import dev.turkdogan.currencyrate.source.altinkaynak.ws.GetGold;
import dev.turkdogan.currencyrate.source.altinkaynak.ws.GetGoldResponse;

public class AltinKaynakClient extends WebServiceGatewaySupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(AltinKaynakClient.class);

	public GetCurrencyResponse getCurrency() {
		GetCurrency request = new GetCurrency();
		GetCurrencyResponse response = (GetCurrencyResponse) callServiceAndGetResponse(request,
				"http://data.altinkaynak.com/GetCurrency");
		return response;
	}

	public GetGoldResponse getGold() {
		GetGold request = new GetGold();
		GetGoldResponse response = (GetGoldResponse) callServiceAndGetResponse(request,
				"http://data.altinkaynak.com/GetGold");
		return response;
	}

	private Object callServiceAndGetResponse(Object request, String action) {
		return getWebServiceTemplate().marshalSendAndReceive(request, new WebServiceMessageCallback() {

			@Override
			public void doWithMessage(WebServiceMessage message) {
				try {
					SoapMessage soapMessage = (SoapMessage) message;
					soapMessage.setSoapAction(action);

					SoapHeader header = soapMessage.getSoapHeader();
					String sb = "      <data:AuthHeader xmlns:data=\"http://data.altinkaynak.com/\">"
							+ "         <data:Username>AltinkaynakWebServis</data:Username>"
							+ "         <data:Password>AltinkaynakWebServis</data:Password>"
							+ "      </data:AuthHeader>";
					StringSource headerSource = new StringSource(sb);
					Transformer transformer = TransformerFactory.newInstance().newTransformer();
					transformer.transform(headerSource, header.getResult());
				} catch (Exception e) {
					LOGGER.error("", e);
				}
			}
		});

	}

}