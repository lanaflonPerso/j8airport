package com.luxoft.j8airport;

import com.luxoft.j8airport.clients.ClientServiceTests;
import com.luxoft.j8airport.clients.ClientSupportServiceTests;
import com.luxoft.j8airport.flights.FlightsServiceTests;
import com.luxoft.j8airport.flights.TimezoneUtilsTests;
import com.luxoft.j8airport.tickets.TicketServiceTests;
import com.luxoft.j8airport.tickets.discounts.PersonalDiscountServiceTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ClientServiceTests.class,
		ClientSupportServiceTests.class,
		FlightsServiceTests.class,
		TicketServiceTests.class,
		PersonalDiscountServiceTests.class,
		TimezoneUtilsTests.class
})
public class J8airportApplicationTests {

}
