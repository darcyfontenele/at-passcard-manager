package tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import entities.PassCard;
import entities.Station;
import enums.FareEnum;
import enums.ZoneEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/buy-ticket.feature" },
                 glue = {"tests"})
public class PassServiceTest {

    @Test
    public void ChargeFareAZoneUniqueTicket() {
        PassCard passCard = new PassCard(100);
        assertTrue(passCard.chargeFare(FareEnum.AZONEUNIQUE));
    }

    @Test
    public void FailChargeFareAZoneUniqueTicket() {
        PassCard passCard = new PassCard(4);
        assertFalse(passCard.chargeFare(FareEnum.AZONEUNIQUE));
    }

    // ENTER UNIQUE A ZONE STATION

    @Test
    public void EnterAZoneStationUsingUniqueTicket() {
        PassCard passCard = new PassCard(100);
        Station a1 = new Station(ZoneEnum.A);
        assertTrue(a1.enter(passCard, FareEnum.AZONEUNIQUE));
    }

    @Test
    public void EnterAZoneStationUsingDailyTicket() {
        PassCard passCard = new PassCard(100);
        Station a1 = new Station(ZoneEnum.A);
        assertTrue(a1.enter(passCard, FareEnum.AZONEDAILY));
    }

    @Test
    public void EnterAZoneStationUsingWeeklyTicket() {
        PassCard passCard = new PassCard(100);
        Station a1 = new Station(ZoneEnum.A);
        assertTrue(a1.enter(passCard, FareEnum.AZONEWEEKLY));
    }

    @Test
    public void EnterAZoneStationUsingMonthlyTicket() {
        PassCard passCard = new PassCard(150);
        Station a1 = new Station(ZoneEnum.A);
        assertTrue(a1.enter(passCard, FareEnum.AZONEMONTHLY));
    }

    // ENTER MULTIPLE A ZONE STATIONS

    @Test
    public void EnterMultipleAZoneStationsUsingUniqueTicket() {
        PassCard passCard = new PassCard(100);
        Station a1 = new Station(ZoneEnum.A);
        Station a2 = new Station(ZoneEnum.A);
        a1.enter(passCard, FareEnum.AZONEUNIQUE);
        a2.enter(passCard, FareEnum.AZONEUNIQUE);
        assertEquals(88, passCard.getBalance());
    }

    @Test
    public void EnterMultipleAZoneStationsUsingDailyTicket() {
        PassCard passCard = new PassCard(100);
        Station a1 = new Station(ZoneEnum.A);
        Station a2 = new Station(ZoneEnum.A);
        a1.enter(passCard, FareEnum.AZONEDAILY);
        a2.enter(passCard, FareEnum.AZONEDAILY);
        assertEquals(90, passCard.getBalance());
    }

    @Test
    public void EnterMultipleAZoneStationsUsingWeeklyTicket() {
        PassCard passCard = new PassCard(100);
        Station a1 = new Station(ZoneEnum.A);
        Station a2 = new Station(ZoneEnum.A);
        a1.enter(passCard, FareEnum.AZONEWEEKLY);
        a2.enter(passCard, FareEnum.AZONEWEEKLY);
        assertEquals(70, passCard.getBalance());
    }

    @Test
    public void EnterMultipleAZoneStationsUsingMonthlyTicket() {
        PassCard passCard = new PassCard(150);
        Station a1 = new Station(ZoneEnum.A);
        Station a2 = new Station(ZoneEnum.A);
        a1.enter(passCard, FareEnum.AZONEMONTHLY);
        a2.enter(passCard, FareEnum.AZONEMONTHLY);
        assertEquals(20, passCard.getBalance());
    }

    // ENTER UNIQUE B ZONE STATION

    @Test
    public void EnterBZoneStationUsingUniqueTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        assertTrue(b1.enter(passCard, FareEnum.BZONEUNIQUE));
    }

    @Test
    public void EnterBZoneStationUsingDailyTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        assertTrue(b1.enter(passCard, FareEnum.BZONEDAILY));
    }

    @Test
    public void EnterBZoneStationUsingWeeklyTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        assertTrue(b1.enter(passCard, FareEnum.BZONEWEEKLY));
    }

    @Test
    public void EnterBZoneStationUsingMonthlyTicket() {
        PassCard passCard = new PassCard(200);
        Station b1 = new Station(ZoneEnum.B);
        assertTrue(b1.enter(passCard, FareEnum.BZONEMONTHLY));
    }

    // ENTER MULTIPLE B ZONE STATIONS

    @Test
    public void EnterMultipleBZoneStationsUsingUniqueTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        Station b2 = new Station(ZoneEnum.B);
        b1.enter(passCard, FareEnum.BZONEUNIQUE);
        b2.enter(passCard, FareEnum.BZONEUNIQUE);
        assertEquals(86, passCard.getBalance());
    }

    @Test
    public void EnterMultipleBZoneStationsUsingDailyTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        Station b2 = new Station(ZoneEnum.B);
        b1.enter(passCard, FareEnum.BZONEDAILY);
        b2.enter(passCard, FareEnum.BZONEDAILY);
        assertEquals(88, passCard.getBalance());
    }

    @Test
    public void EnterMultipleBZoneStationsUsingWeeklyTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        Station b2 = new Station(ZoneEnum.B);
        b1.enter(passCard, FareEnum.BZONEWEEKLY);
        b2.enter(passCard, FareEnum.BZONEWEEKLY);
        assertEquals(55, passCard.getBalance());
    }

    @Test
    public void EnterMultipleBZoneStationsUsingMonthlyTicket() {
        PassCard passCard = new PassCard(200);
        Station b1 = new Station(ZoneEnum.B);
        Station b2 = new Station(ZoneEnum.B);
        b1.enter(passCard, FareEnum.BZONEMONTHLY);
        b2.enter(passCard, FareEnum.BZONEMONTHLY);
        assertEquals(30, passCard.getBalance());
    }
    
    // ENTER STATION AFTER VALIDATION DATE EXPIRE

    @Test
    public void EnterStationUsingUniqueTicketAfterDailyValidationExpire() {
        PassCard passCard = new PassCard(100, FareEnum.AZONEDAILY,
            LocalDate.of(2021, 4, 20));
        Station a1 = new Station(ZoneEnum.A);
        a1.enter(passCard, FareEnum.AZONEUNIQUE);
        assertEquals(94, passCard.getBalance());
    }

    // ENTER MULTIPLE STATIONS AFTER FIRST VALIDATION DATE EXPIRE

    @Test
    public void EnterMultipleStationsUsingUniqueTicketAfterFirstValidationExpire() {
        PassCard passCard = new PassCard(100, FareEnum.AZONEDAILY,
                LocalDate.of(2021, 4, 20));
        Station a1 = new Station(ZoneEnum.A);
        a1.enter(passCard, FareEnum.AZONEUNIQUE);
        a1.enter(passCard, FareEnum.AZONEUNIQUE);
        assertEquals(88, passCard.getBalance());
    }

    @Test
    public void EnterMultipleStationsUsingDailyTicketAfterFirstValidationExpire() {
        PassCard passCard = new PassCard(100, FareEnum.AZONEDAILY,
                LocalDate.of(2021, 4, 20));
        Station a1 = new Station(ZoneEnum.A);
        a1.enter(passCard, FareEnum.AZONEDAILY);
        a1.enter(passCard, FareEnum.AZONEDAILY);
        assertEquals(90, passCard.getBalance());
    }

    // ENTER MULTIPLE STATIONS USING CRESCENT TICKETS TYPES

    @Test
    public void EnterMultipleStationsUsingDailyAZoneAndWeeklyBZoneTickets() {
        PassCard passCard = new PassCard(100);
        Station a1 = new Station(ZoneEnum.A);
        Station b1 = new Station(ZoneEnum.B);
        a1.enter(passCard, FareEnum.AZONEDAILY);
        b1.enter(passCard, FareEnum.BZONEWEEKLY);
        assertEquals(45, passCard.getBalance());
    }

    // ENTER MULTIPLE STATIONS USING DECREMENT TICKETS TYPES

    @Test
    public void EnterMultipleStationsUsingWeeklyBZoneAndWeeklyAZoneTickets() {
        PassCard passCard = new PassCard(100);
        Station a1 = new Station(ZoneEnum.A);
        Station b1 = new Station(ZoneEnum.B);
        b1.enter(passCard, FareEnum.BZONEWEEKLY);
        a1.enter(passCard, FareEnum.AZONEWEEKLY);
        assertEquals(55, passCard.getBalance());
    }

    // ENTER B ZONE STATION USING A ZONE TICKET

    @Test
    public void EnterBZoneStationUsingUniqueAZoneTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        assertFalse(b1.enter(passCard, FareEnum.AZONEUNIQUE));
    }

    @Test
    public void EnterBZoneStationUsingDailyAZoneTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        assertFalse(b1.enter(passCard, FareEnum.AZONEDAILY));
    }

    @Test
    public void EnterBZoneStationUsingWeeklyAZoneTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        assertFalse(b1.enter(passCard, FareEnum.AZONEWEEKLY));
    }

    @Test
    public void EnterBZoneStationUsingMonthlyAZoneTicket() {
        PassCard passCard = new PassCard(100);
        Station b1 = new Station(ZoneEnum.B);
        assertFalse(b1.enter(passCard, FareEnum.AZONEMONTHLY));
    }

}
