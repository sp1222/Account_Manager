package model;

import model.ModelEvent;

public interface Model {
	public void notifyChanged(ModelEvent me);
}
