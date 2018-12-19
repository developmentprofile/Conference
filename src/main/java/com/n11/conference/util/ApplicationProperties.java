package com.n11.conference.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class ApplicationProperties {

	@Value("${confStart}")
	private String confStart;
	@Value("${confEnd}")
	private String confEnd;
	@Value("${lunchStart}")
	private String lunchStart;
	@Value("${lunchEnd}")
	private String lunchEnd;
	@Value("${networkStart}")
	private String networkStart;
	@Value("${networkEnd}")
	private String networkEnd;
	@Value("${lightning}")
	private Integer lightning;
	

	public String getConfStart() {
		return confStart;
	}

	public void setConfStart(String confStart) {
		this.confStart = confStart;
	}

	public String getConfEnd() {
		return confEnd;
	}

	public void setConfEnd(String confEnd) {
		this.confEnd = confEnd;
	}

	public String getLunchStart() {
		return lunchStart;
	}

	public void setLunchStart(String lunchStart) {
		this.lunchStart = lunchStart;
	}

	public String getLunchEnd() {
		return lunchEnd;
	}

	public void setLunchEnd(String lunchEnd) {
		this.lunchEnd = lunchEnd;
	}

	public String getNetworkStart() {
		return networkStart;
	}

	public void setNetworkStart(String networkStart) {
		this.networkStart = networkStart;
	}

	public String getNetworkEnd() {
		return networkEnd;
	}

	public void setNetworkEnd(String networkEnd) {
		this.networkEnd = networkEnd;
	}

	public Integer getLightning() {
		return lightning;
	}

	public void setLightning(Integer lightning) {
		this.lightning = lightning;
	}

	

}
