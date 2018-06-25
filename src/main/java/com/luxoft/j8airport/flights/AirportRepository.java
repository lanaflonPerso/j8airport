package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.flights.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>
{

}
