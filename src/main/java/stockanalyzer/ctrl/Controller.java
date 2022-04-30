package stockanalyzer.ctrl;

import stockanalyzer.ui.UserInterface;
import yahooApi.MyExceptionMaster;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.Result;

import java.util.*;

public class Controller {

	public void process (String ticker) throws MyExceptionMaster {
		System.out.println ("Start process");

		try {
			QuoteResponse quoteResponse = (QuoteResponse) getData (ticker);
			System.out.println ("Highest Ask: " +
					quoteResponse.getResult ()
					.stream ()
					.mapToDouble (Result::getAsk)
					.max ()
					.orElseThrow (() -> new MyExceptionMaster(("There isnt a Highest Ask which will be found"))));
			System.out.println ("Average Ask: " +
					quoteResponse.getResult ()
					.stream ().mapToDouble (Result::getAsk)
					.average ().orElseThrow (() -> new MyExceptionMaster ("There isnt a average Ask which will be found")));
			System.out.println("Amount of Asks: " +
					quoteResponse.getResult ()
					.stream ().mapToDouble (Result::getAsk).count());
		} catch (MyExceptionMaster | NullPointerException myExceptionMaster) {
			throw new MyExceptionMaster("Unlovely Error " + myExceptionMaster.getMessage());
		}
		new UserInterface ( );
	}

	public Object getData (String searchString) throws MyExceptionMaster {

		List<String> tickers = Arrays.asList(searchString.split(", "));
		YahooFinance yahooFinance = new YahooFinance();

		return yahooFinance.getCurrentData(tickers).getQuoteResponse();
	}

	public void closeConnection () {

	}
}
