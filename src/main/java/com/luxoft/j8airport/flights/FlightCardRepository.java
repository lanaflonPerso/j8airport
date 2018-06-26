package com.luxoft.j8airport.flights;

import com.luxoft.j8airport.domain.FlightCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightCardRepository extends JpaRepository<FlightCard, Long>
{

}
