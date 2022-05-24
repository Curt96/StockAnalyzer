package stockanalyzer.ui;
//TODO Exceptions von API und ctrl sollen ins UI kommen
//Link: https://github.com/Curt96/StockAnalyzer

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import stockanalyzer.ctrl.Controller;
import yahooApi.MyExceptionMaster;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1() {
		try {
			ctrl.process("NFC.DE, SPY, FQT.VI");
		}
		catch (MyExceptionMaster error) {
			error.printStackTrace();
		}
	}
	public void getDataFromCtrl2() {
		try {
			ctrl.process("AMZN, GOOG, TSLA");
		}
		catch (MyExceptionMaster myExceptionMaster) {
			myExceptionMaster.printStackTrace();
		}
	}
	public void getDataFromCtrl3(){
	try {
			ctrl.process("AAPL, FB, SNAP");
		}
		catch (MyExceptionMaster myExceptionMaster) {
			myExceptionMaster.printStackTrace();
		}
	}
	public void getDataFromCtrl4(){

	}
	public void downloadTickers() {

	}
	
	public void getDataForCustomInput(){
		try {
			Scanner scanner = new Scanner(System.in);
			ctrl.process(scanner.next());
		}
		catch (MyExceptionMaster myExceptionMaster) {
			myExceptionMaster.printStackTrace();
		}
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice 1: NFC.DE, SPY, FQT.VI", this::getDataFromCtrl1);
		menu.insert("b", "Choice 2: AMZN, GOOG, TSLA", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3: AAPL, FB, SNAP", this::getDataFromCtrl3);
		//menu.insert("d", "Choice User Imput:",this::getDataForCustomInput);
		//menu.insert("z", "Choice User Imput:",this::getDataFromCtrl4);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		ctrl.closeConnection();
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
