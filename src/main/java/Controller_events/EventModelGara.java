package Controller_events;

import Controller.Controller_Interface;

public class EventModelGara extends HorseFeverEventController {
	
	public EventModelGara(Controller_Interface cont)
	{
		super(EventModelGara.class);
		super.controller_Interface = cont;
	}

}
