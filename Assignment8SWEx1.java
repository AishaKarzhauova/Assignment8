package assignment8SW;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String symbol, double price);
}

class Stock {
    private String symbol;
    private double price;
    private List<Observer> investors;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
        this.investors = new ArrayList<>();
    }

    public void registerInvestor(Observer investor) {
        investors.add(investor);
    }

    public void unregisterInvestor(Observer investor) {
        investors.remove(investor);
    }

    public void updatePrice(double price) {
        this.price = price;
        notifyInvestors();
    }

    private void notifyInvestors() {
        for (Observer investor : investors) {
            investor.update(symbol, price);
        }
    }
}

class Investor implements Observer {
    private String name;
    private List<Stock> stocks;

    public Investor(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    public void invest(Stock stock) {
        stock.registerInvestor(this);
        stocks.add(stock);
    }

    @Override
    public void update(String symbol, double price) {
        System.out.println(name + " received update for stock " + symbol + ": $" + price);
    }
}

public class Assignment8SWEx1 {
    public static void main(String[] args) {
        Stock stock1 = new Stock("AAPL", 150.0);
        Stock stock2 = new Stock("GOOGL", 2500.0);

        Investor investor1 = new Investor("John");
        Investor investor2 = new Investor("Alice");

        investor1.invest(stock1);
        investor2.invest(stock1);
        investor2.invest(stock2);

        stock1.updatePrice(155.0);
        stock2.updatePrice(2600.0);

        stock1.unregisterInvestor(investor2);

        stock1.updatePrice(160.0);
    }
}

// code demonstrates the observer pattern where the Stock class is the subject and the Investor class is the observer.
// Each investor is notified when the price of a stock they have invested in changes.

