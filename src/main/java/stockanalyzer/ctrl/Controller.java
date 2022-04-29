package stockanalyzer.ctrl;

import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
	YahooFinance yahooFinance = new YahooFinance();
	public void process(String ticker) {
		System.out.println("Start process");

		//TODO implement Error handling 

		//TODO implement methods for
		//1) Daten laden
		//2) Daten Analyse


		switch (ticker) {
			case ("NFC.DE") : {
				getData("NFC.DE");
			}
			case ("SPY") : {
				getData("SPY");
			}
			case ("FQT.VI") : {
				getData("FQT.VI");
			}
			default:
				System.out.println("Something went wrong. Try again!");
		}

	}
	

	public Object getData(String searchString) {

		List<String> tickers = Arrays.asList(searchString);
		YahooResponse yahooResponse = yahooFinance.getCurrentData(tickers);
		QuoteResponse quoteResponse = yahooResponse.getQuoteResponse();
		quoteResponse.getResult().stream().forEach(result -> System.out.println(result.getFiftyTwoWeekHigh() + "," + result.getAverageDailyVolume10Day() + ", Anzahl der Datensätze"));
		
		return null;
	}


	public void closeConnection() {
		
	}
}
