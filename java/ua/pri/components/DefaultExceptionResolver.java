package ua.pri.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import ua.pri.exceptions.InvalidUserInputException;

public class DefaultExceptionResolver extends AbstractHandlerExceptionResolver{
	
	private static final Logger LOGGER = LogManager.getLogger(DefaultExceptionResolver.class);
	
	public DefaultExceptionResolver() {
		setOrder(0);
	}
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

			Throwable causeException = ex.getCause() == null ? ex : ex.getCause();
			ModelAndView mav = new ModelAndView("redirect:/error");
			mav.addObject("error", ex.getMessage());
			return mav;
		
		
	}

}
