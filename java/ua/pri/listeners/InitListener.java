package ua.pri.listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import ua.pri.services.ConfigApplicationService;

public class InitListener implements ServletContextListener {
	private static final Logger LOGGER = LogManager
			.getLogger(InitListener.class);

	//private Scheduler scheduler = null;

	protected WebApplicationContext getWebApplicationContext(
			ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		return (WebApplicationContext) servletContext
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			InitialContext cxt = new InitialContext();

			DataSource ds = (DataSource) cxt
					.lookup("java:/comp/env/jdbc/postgres");
			LOGGER.info(ds);

		} catch (Exception e) {

			e.printStackTrace();

		}
		ServletContext context = event.getServletContext();
		String contextPath = context.getContextPath();
		context.setAttribute("context", contextPath);

		ConfigApplicationService configAppService = (ConfigApplicationService) getWebApplicationContext(
				event).getBean(ConfigApplicationService.class);
		context.setAttribute("CSS_JS_VERSION",
				configAppService.getCssJsVersion());

/*		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("cleanUpTrigger", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(20).repeatForever())
				.build();

		JobDetail job = JobBuilder.newJob(AccVerificationJob.class)
				.withIdentity("cleanUpJob", "group1").build();

		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			LOGGER.error("schefuler fail " + e.getMessage());
		}*/

		LOGGER.info("context started");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	/*	if (scheduler != null)
			try {
				scheduler.shutdown(false);
				LOGGER.info("scheduler shutdowned");
			} catch (SchedulerException e) {
				LOGGER.error("Error shutdowning scheduler " + e.getMessage());
			}*/
		LOGGER.info("context destroyed");
	}

}
