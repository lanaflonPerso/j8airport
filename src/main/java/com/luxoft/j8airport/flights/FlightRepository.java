package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>
{

}
