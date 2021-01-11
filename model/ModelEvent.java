package model;

import java.math.BigDecimal;

public class ModelEvent {
	private BigDecimal balance;
	
	public ModelEvent(BigDecimal balance){
		this.balance = balance;
	}
	public BigDecimal getBalance(){
		return balance;
	}
}

