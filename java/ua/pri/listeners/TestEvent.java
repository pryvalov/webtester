package ua.pri.listeners;

import org.springframework.context.ApplicationEvent;

public class TestEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7644856599410521211L;

	public TestEvent(Object source) {
		super(source);
	
	}


	@Override
	public String toString() {
		return "Test event occured!";
	}

}
