package com.smt.weather;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/****************************************************************************
 * <b>Title</b>: CoordinateTest.java
 * <b>Project</b>: SpaceLibs-Java
 * <b>Description: </b> Tests the weather coordinates class
 * <b>Copyright:</b> Copyright (c) 2021
 * <b>Company:</b> Silicon Mountain Technologies
 * 
 * @author James Camire
 * @version 3.0
 * @since Jan 22, 2021
 * @updates:
 ****************************************************************************/
public class CoordinateTest {

	/**
	 * Test method for {@link com.smt.weather.Coordinate#Coordinate(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testCoordinateStringString() {
		Coordinate c = new Coordinate("100.123", "-34.5678");
		assertEquals(BigDecimal.valueOf(100.123), c.getLatitude());
		assertEquals(BigDecimal.valueOf(-34.5678), c.getLongitude());
	}

	/**
	 * Test method for {@link com.smt.weather.Coordinate#Coordinate(double, double)}.
	 */
	@Test
	void testCoordinateDoubleDouble() {
		Coordinate c = new Coordinate(100.123, -34.5678);
		assertEquals(BigDecimal.valueOf(100.123), c.getLatitude());
		assertEquals(BigDecimal.valueOf(-34.5678), c.getLongitude());
	}

	/**
	 * Test method for {@link com.smt.weather.Coordinate#setLocation(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testSetLocationStringString() {
		Coordinate c = new Coordinate("0", "0");
		c.setLocation("100.123", "-34.5678");
		assertEquals(BigDecimal.valueOf(100.123), c.getLatitude());
		assertEquals(BigDecimal.valueOf(-34.5678), c.getLongitude());
	}

	/**
	 * Test method for {@link com.smt.weather.Coordinate#setLocation(double, double)}.
	 */
	@Test
	void testSetLocationDoubleDouble() {
		Coordinate c = new Coordinate(0,0);
		c.setLocation(100.123, -34.5678);
		assertEquals(BigDecimal.valueOf(100.123), c.getLatitude());
		assertEquals(BigDecimal.valueOf(-34.5678), c.getLongitude());
	}

}