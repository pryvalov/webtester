package ua.pri.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class TestListener implements ApplicationListener<ApplicationEvent> {
	private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		//LOGGER.info(event.toString());
		
	}

}
