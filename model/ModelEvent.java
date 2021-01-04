package model;

import java.math.BigDecimal;


/* Note: for this assignment, we do not need to implement the commented items */

public class ModelEvent {
	private BigDecimal balance;
	
	public ModelEvent(BigDecimal balance){
		this.balance = balance;
	}
	public BigDecimal getBalance(){
		return balance;
	}
}

