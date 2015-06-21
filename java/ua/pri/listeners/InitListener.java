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

		

		DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/postgres" );
		LOGGER.info(ds);

		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
		ServletContext context = event.getServletContext();
		String contextPath = context.getContextPath();
		context.setAttribute("context", contextPath);
		
		ConfigApplicationService configAppService = (ConfigApplicationService) getWebApplicationContext(event).getBean(ConfigApplicationService.class);
		context.setAttribute("CSS_JS_VERSION", configAppService.getCssJsVersion());
		
		LOGGER.info("context started");
	

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		LOGGER.info("context destroyed");
	}

}
