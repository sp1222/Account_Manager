package controller;
import model.Model;
import view.View;

public class AbstractController implements Controller{
	private Model model;
	private View view;

	public void setModel(Model model) {	this.model = model; }
	public Model getModel() {	return model; }
	public void setView(View view) {	this.view = view; }
	public View getView() {	return view; }	
}