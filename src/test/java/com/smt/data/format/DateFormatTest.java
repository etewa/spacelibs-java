package com.smt.data.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;

import com.smt.data.format.DateFormat.DatePattern;

/****************************************************************************
 * <b>Title</b>: DateFormatTest.java
 * <b>Project</b>: SpaceLibs-Java
 * <b>Description: </b> Test of the DateFormat class.  This test ensures
 * the formatting and parsing of dates/strings, different date type objects conversions,
 * retrieving various date related information like current day, date, year, month,
 * getting start, end and start of next date.
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author Bala Gayatri Bugatha
 * @version 1.0
 * @since Jan 22, 2021
 * @updates:
 ****************************************************************************/
class DateFormatTest {
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#zoneDateToDate(ZonedDateTime)}
	 */
	@Test
	void ZoneDateToDate_test() throws ParseException {
		ZonedDateTime dateTime5 = ZonedDateTime.of(LocalDateTime.of(2021, 01, 22, 07, 00),
	            ZoneId.of("UTC"));
		assertEquals("Fri Jan 22 07:00:00 MST 2021",DateFormat.zoneDateToDate(dateTime5).toString());
	}

	/**
	 * Test method for {@link com.smt.data.format.DateFormat#parseUnknownPattern(String)}
	 */
	@Test 
	void parseUnknownPattern_testForNull() {
		assertEquals(null,DateFormat.parseUnknownPattern(null));
		assertEquals(null,DateFormat.parseUnknownPattern("10$28$123"));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#parseUnknownPattern(String)}
	 */
	@Test 
	void parseUnknownPattern_testForLessLength() {
		assertEquals(null,DateFormat.parseUnknownPattern("10/"));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#parseUnknownPattern(String)}
	 */
	@Test 
	void parseUnknownPattern_testForNotNull() {
		Date date = new GregorianCalendar(1995, Calendar.OCTOBER, 28).getTime();
		assertEquals(date,DateFormat.parseUnknownPattern("10/28/1995"));
	}
    
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#formatDate(DatePattern, String)}
	 */
	@Test
	void formatDate_test() {
		assertEquals(null,DateFormat.formatDate(DatePattern.DATE_SLASH, null));
		assertEquals("Sat Oct 28 00:00:00 MDT 1995",DateFormat.formatDate(DatePattern.DATE_SLASH,"10/28/1995").toString());
	}
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#dateToPattern(DatePattern, Date)}
	 */
	@Test
	void dateToPattern_test() {
		Date date = new GregorianCalendar(1995, Calendar.OCTOBER, 28).getTime();
		assertEquals("951028",DateFormat.dateToPattern(DatePattern.DATE_SHORT_NOSPACE,date));
		assertEquals(null,DateFormat.dateToPattern(DatePattern.DATE_SHORT_NOSPACE,null));
		assertEquals(null,DateFormat.dateToPattern(null,date));
		assertEquals(null,DateFormat.dateToPattern(null,null));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#adjustDate(Date, int, int)}
	 */
	@Test
	void formatDateAmount_test() {
		Date date_old = new GregorianCalendar(1995, Calendar.OCTOBER, 28).getTime();
		Date date_new = new GregorianCalendar(1995, Calendar.AUGUST, 28).getTime();
		assertEquals(date_new,DateFormat.adjustDate(date_old, Calendar.MONTH, -2));
		assertEquals(null,DateFormat.adjustDate(null, Calendar.MONTH, 5));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#isDate(String)}
	 */
	@Test
	void isDateString_test() {
		assertEquals(true,DateFormat.isDate("10/28/1995"));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#isDate(String)}
	 */
	@Test
	void isDateNullString_test() {
		assertEquals(false,DateFormat.isDate("10/"));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#isDate(Object)}
	 */
	@Test
	void isDateObject_test() {
		assertEquals(true,DateFormat.isDate((Object)("10/28/1995")));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#isDate(Object)}
	 */
	@Test
	void isDateNullObject_test() {
		assertEquals(false,DateFormat.isDate((Object)null));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#formatSQLDate(java.util.Date)}
	 * Test method for {@link com.smt.data.format.DateFormat#formatSQLDate(java.util.Date, boolean)}
	 * Test method for {@link com.smt.data.format.DateFormat#formatSQLDate(String, String)}
	 * Test method for {@link com.smt.data.format.DateFormat#formatSQLDate(String)}
	 */
	@Test
	void formatSQLDate_test() {
		Date ud = new GregorianCalendar(1995, Calendar.OCTOBER, 28).getTime();
		java.sql.Date sd=new java.sql.Date(ud.getTime());    
		assertEquals(sd,DateFormat.formatSQLDate(ud));
		assertEquals(sd,DateFormat.formatSQLDate(ud, false));
		assertEquals(new java.sql.Date(new Date().getTime()),DateFormat.formatSQLDate(null, true));
		assertEquals(null,DateFormat.formatSQLDate(null, false));
		assertEquals(sd,DateFormat.formatSQLDate(DatePattern.DATE_SLASH,"10/28/1995"));
		assertEquals(sd,DateFormat.formatSQLDate("10/28/1995"));
	}

	/**
	 * Test method for {@link com.smt.data.format.DateFormat#formatTimestamp(String, String)}.
	 * Test method for {@link com.smt.data.format.DateFormat#formatTimestamp(Date)}
	 */
	
	@Test
	public void formatTimestamp_test() {
		Date ud = new GregorianCalendar(1995, Calendar.OCTOBER, 28).getTime();
		java.sql.Timestamp ts =java.sql.Timestamp.valueOf(
		        java.time.LocalDate.of(1995,10,28).atStartOfDay()
		);
		assertEquals(ts,DateFormat.formatTimestamp(DatePattern.DATE_SLASH,"10/28/1995"));
		assertNotEquals(ts,DateFormat.formatTimestamp(DatePattern.DATE_SLASH,"10"));
		assertEquals(ts,DateFormat.formatTimestamp(ud));
		assertNotEquals(ts,DateFormat.formatTimestamp(null));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#getCurrentMonth()}
	 * Test method for {@link com.smt.data.format.DateFormat#getCurrentYear()}
	 * Test method for {@link com.smt.data.format.DateFormat#getCurrentDayOfWeek()}
	 * Test method for {@link com.smt.data.format.DateFormat#getCurrentDayOfMonth()}
	 * Test method for {@link com.smt.data.format.DateFormat#getCurrentDate()}
	 */
	
	@Test
	public void getCurrent_test() {
		assertEquals(Calendar.getInstance().get(Calendar.MONTH) + 1,DateFormat.getCurrentMonth());
		assertEquals(Calendar.getInstance().get(Calendar.YEAR),DateFormat.getCurrentYear() );
		assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_WEEK),DateFormat.getCurrentDayOfWeek());
		assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_MONTH),DateFormat.getCurrentDayOfMonth());
		assertEquals(Calendar.getInstance().get(Calendar.DATE),DateFormat.getCurrentDate());
		}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#getEndOfDay(String)}
	 * Test method for {@link com.smt.data.format.DateFormat#getEndOfDaY(Date)}
	 */
	@Test
	public void getEndDate_test() {
		Date ud = new GregorianCalendar(1995, Calendar.OCTOBER, 28).getTime();
		Date ud_new = new GregorianCalendar(1995, Calendar.OCTOBER, 28,23,59,59).getTime();
		assertEquals(ud_new.toInstant(),DateFormat.getEndOfDay("10/28/1995").toInstant());
		assertEquals(ud_new.toInstant(),DateFormat.getEndOfDay(ud).toInstant());
		assertNotEquals(new Date(),DateFormat.getEndOfDay((Date)null));
	}
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#getStartOfDate(String)}
	 * Test method for {@link com.smt.data.format.DateFormat#getStartOfDate(Date)}
	 */
	@Test
	public void getStartDate_test() {
		Date ud = new GregorianCalendar(1995, Calendar.OCTOBER, 28).getTime();
		Date ud_new = new GregorianCalendar(1995, Calendar.OCTOBER, 28,0,0,0).getTime();
		assertEquals(ud_new.getTime(),DateFormat.getStartOfDay("10/28/1995").getTime());
		assertEquals(ud_new.getTime(),DateFormat.getStartOfDay(ud).getTime());
		assertNotEquals(new Date(),DateFormat.getStartOfDay((Date)null));
	}
	
	
	/**
	 * Test method for {@link com.smt.data.format.DateFormat#anyZoneToUTC(String, String)}
	 * Test method for {@link com.smt.data.format.DateFormat#anyZoneToUTC(Date, String)}
	 */
	@Test
	public void anyZoneToUTC_test() {
		Date d = new GregorianCalendar(1995, Calendar.OCTOBER, 29,0,0,0).getTime();
		ZonedDateTime then = DateFormat.anyZoneToUTC(d,TimeZone.getDefault().getID().toString());
		ZonedDateTime then2 = DateFormat.anyZoneToUTC("10/29/1995",TimeZone.getDefault().getID().toString());
		assertEquals("1995-10-29T06:00Z", then.toString());
		assertEquals("1995-10-29T06:00Z", then2.toString());
	}
}