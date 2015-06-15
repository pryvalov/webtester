package ua.pri.services.impl;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ua.pri.services.ConfigApplicationService;


@Service
public class ConfigApplicationServiceImpl implements ConfigApplicationService {

	@Value("${ua.pri.supportEmailAddress}")
	private String supportEmailAddress;
	
	@Value("${ua.pri.cssJsVersion}")
	private String cssJsVersion;
	
	@Override
	public String getSupportEmailAddress() {
		return supportEmailAddress;
	}

	@Override
	public String getCssJsVersion() {
		return cssJsVersion;
	}

}
