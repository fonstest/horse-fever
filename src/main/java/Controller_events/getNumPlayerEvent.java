package Controller_events;

import Controller.Controller_Interface;

public class getNumPlayerEvent extends HorseFeverEventController {
	
	public getNumPlayerEvent(Controller_Interface cont) {
		super(getNumPlayerEvent.class);
		super.controller_Interface=cont;
	}

}
