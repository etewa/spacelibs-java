package com.smt.weather;

// JDK 11.x
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

// Spaceforce Libs 1.x
import com.smt.data.format.DateFormat;
import com.smt.data.format.DateFormat.DatePattern;
import com.smt.data.parser.BeanDataVO;
import com.smt.data.text.StringUtil;


/****************************************************************************
 * <b>Title</b>: SunTimeVO.java
 * <b>Project</b>: WC_Custom
 * <b>Description: </b> This VO will hold two date objects representing approximate 
 * sun rise and sun set
 * <b>Copyright:</b> Copyright (c) 2019
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author ryan
 * @version 3.0
 * @since Feb 15, 2019
 * @updates:
 ****************************************************************************/
public class SunTimeVO extends BeanDataVO{ 
	
	private static final long serialVersionUID = -53123328524258786L;
	private Date sunsetDate;
	private Date sunriseDate;
	private String sunriseTime;
	private String sunsetTime;
	private double longitudeNumber;
	private double latitudeNumber;
	private String timeZoneName;
	private Date sourceDate;

	/**
	 * 
	 */
	public SunTimeVO() {
		super();
	}

	/**
	 * 
	 * @param sunSetDate
	 * @param sunRiseDate
	 */
	public SunTimeVO(Date sunSetDate, Date sunRiseDate ) {
		super();
		setSunriseDate(sunRiseDate);
		setSunsetDate(sunSetDate);
	}
	/**
	 * 
	 * @param req
	 */
	public SunTimeVO(HttpServletRequest req) {
		super(req);
	}
	
	/**
	 * @return the sunSiteDate
	 */
	public Date getSunSetDate() {
		return sunsetDate;
	}

	/**
	 * @param sunSiteDate the sunSiteDate to set
	 */
	public void setSunsetDate(Date sunSetDate) {
		this.sunsetDate = sunSetDate;
	}

	/**
	 * @return the sunRiseDate
	 */
	public Date getSunriseDate() {
		return sunriseDate;
	}

	/**
	 * @param sunRiseDate the sunRiseDate to set
	 */
	public void setSunriseDate(Date sunriseDate) {
		this.sunriseDate = sunriseDate;
	}

	/**
	 * @return the longitudeNumber
	 */
	public double getLongitudeNumber() {
		return longitudeNumber;
	}

	/**
	 * @param longitudeNumber the longitudeNumber to set
	 */
	public void setLongitudeNumber(double longitudeNumber) {
		this.longitudeNumber = longitudeNumber;
	}

	/**
	 * @return the latitudeNumber
	 */
	public double getLatitudeNumber() {
		return latitudeNumber;
	}

	/**
	 * @param latitudeNumber the latitudeNumber to set
	 */
	public void setLatitudeNumber(double latitudeNumber) {
		this.latitudeNumber = latitudeNumber;
	}

	/**
	 * @return the timeZoneName
	 */
	public String getTimeZoneName() {
		return timeZoneName;
	}

	/**
	 * @param timeZoneName the timeZoneName to set
	 */
	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	/**
	 * @return the sourceDate
	 */
	public Date getSourceDate() {
		return sourceDate;
	}

	/**
	 * @param sourceDate the sourceDate to set
	 */
	public void setSourceDate(Date sourceDate) {
		this.sourceDate = sourceDate;
	}

	/**
	 * @return the sunriseTime
	 */
	public String getSunriseTime() {
		return sunriseTime;
	}

	/**
	 * @param sunriseTime the sunriseTime to set
	 */
	public void setSunriseTime(String sunriseTime) {
		this.sunriseTime = sunriseTime;
		if (StringUtil.isEmpty(this.sunriseTime)) return;
		if (sourceDate == null) sourceDate = new Date();
		
		String d = DateFormat.toFormattedString(DatePattern.DATE_DASH, sourceDate);
		sunriseDate = DateFormat.formatDate(DatePattern.DATE_DASH, d + " " + sunriseTime.trim() + ":00");
	}

	/**
	 * @return the sunsetTime
	 */
	public String getSunsetTime() {
		return sunsetTime;
	}

	/**
	 * @param sunsetTime the sunsetTime to set
	 */
	public void setSunsetTime(String sunsetTime) {
		this.sunsetTime = sunsetTime;
		if (StringUtil.isEmpty(sunsetTime)) return;
		if (sourceDate == null) sourceDate = new Date();
		
		String d = DateFormat.toFormattedString(DatePattern.DATE_DASH, sourceDate);
		sunsetDate = DateFormat.formatDate(DatePattern.DATE_DASH, d + " " + sunsetTime.trim() + ":00");
	}
}