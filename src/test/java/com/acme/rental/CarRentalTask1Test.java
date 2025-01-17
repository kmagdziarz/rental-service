package com.acme.rental;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarRentalTask1Test
{
    private static final long CLIENT_1_ID = 1;
    private static final long CLIENT_2_ID = 2;
    private static final long CLIENT_3_ID = 405566655;
    private static final long CAR_1_ID = 1;
    private static final long CAR_20_ID = 20;
    private static final long CAR_30_ID = 30;
    public static final int WRONG_CAR_ID = 100;

    CarRentalService carRentalService;

    @BeforeEach
    void setUp()
    {
        carRentalService = new CarRentalServiceImpl(IntStream.rangeClosed(1, 50).mapToObj(Car::new).toList());
    }


    @Test
    void shouldRentCar()
    {
        Car car = carRentalService.rentCar(1, CLIENT_1_ID);

        assertTrue(carRentalService.isCarRented(1));
        assertTrue(carRentalService.getAllRentedCarsByClient(CLIENT_1_ID).contains(car));
    }

    @Test
    void shouldReturnCar()
    {
        carRentalService.rentCar(CAR_1_ID, CLIENT_1_ID);
        assertTrue(carRentalService.isCarRented(CAR_1_ID));

        assertNotNull(carRentalService.returnCar(CAR_1_ID, CLIENT_1_ID));

        assertFalse(carRentalService.isCarRented(CAR_1_ID));
        assertTrue(carRentalService.getAllRentedCarsByClient(CLIENT_1_ID).isEmpty());
    }

    @Test
    void cannotRentSameCarTwice()
    {
        carRentalService.rentCar(CAR_1_ID, CLIENT_1_ID);
        assertNull(carRentalService.rentCar(CAR_1_ID, CLIENT_1_ID));
    }

    @Test
    void cannotReturnCarNotRented()
    {
        assertNull(carRentalService.returnCar(CAR_1_ID, CLIENT_1_ID));
    }

    @Test
    void shouldGetAvailableCars()
    {
        carRentalService.rentCar(CAR_1_ID, CLIENT_1_ID);
        carRentalService.rentCar(CAR_20_ID, CLIENT_2_ID);
        carRentalService.rentCar(CAR_30_ID, CLIENT_3_ID);

        assertEquals(47, carRentalService.getAvailableCars().size());
    }

    @Test
    void shouldGetAllRentedCarsByClient()
    {
        carRentalService.rentCar(CAR_1_ID, CLIENT_1_ID);
        carRentalService.rentCar(CAR_20_ID, CLIENT_1_ID);
        carRentalService.rentCar(CAR_30_ID, CLIENT_1_ID);

        assertEquals(3, carRentalService.getAllRentedCarsByClient(CLIENT_1_ID).size());
    }

    @Test
    void shouldHandleWrongCarId()
    {
        assertNull(carRentalService.rentCar(WRONG_CAR_ID, CLIENT_1_ID));
        assertNull(carRentalService.returnCar(WRONG_CAR_ID, CLIENT_1_ID));
        assertFalse(carRentalService.isCarRented(WRONG_CAR_ID));
    }
}
