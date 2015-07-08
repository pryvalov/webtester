package ua.pri.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;


//************************************************//
//TODO: Currently not used stub for exc resolving //
//************************************************//

@Component
public class DefaultExceptionResolver extends AbstractHandlerExceptionResolver{
	
	private static final Logger LOGGER = LogManager.getLogger(DefaultExceptionResolver.class);
	
	public DefaultExceptionResolver() {
		setOrder(0);
	}
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

/*			Throwable causeException = ex.getCause() == null ? ex : ex.getCause();
			if(!(causeException instanceof InvalidUserInputException)&&!(causeException instanceof RegistrationException)){
			ModelAndView mav = new ModelAndView("redirect:/error");
			mav.addObject("error", ex.getMessage());
			return mav;
			}*/
			LOGGER.warn("Exception "+ex+" msg: "+ex.getMessage()+" Handler: "+handler+" ");
			for(StackTraceElement st : ex.getStackTrace())
			LOGGER.debug(st.toString());
			Throwable causeException = ex.getCause() == null ? null : ex.getCause();
			while(causeException!=null){
				LOGGER.warn("###############Exception "+causeException+" msg: "+causeException.getMessage());
				for(StackTraceElement st : causeException.getStackTrace())
					LOGGER.debug(st.toString());
				causeException = causeException.getCause() == null ? null : causeException.getCause();
			}
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("error", ex+" cause: "+ex.getCause()+" msg "+ex.getMessage());
			return mav;
		
	}

}
