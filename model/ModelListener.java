package model;

import model.ModelEvent;

public interface ModelListener {
	public void modelChanged(ModelEvent me);
}
