package com.acme.rental;

import java.util.List;

public class CarRentalServiceImpl implements CarRentalService
{
    @Override
    public Car rentCar(long carId, long clientId)
    {
        return null;
    }

    @Override
    public List<Car> getAvailableCars()
    {
        return List.of();
    }

    @Override
    public List<Car> getAllRentedCarsByClient(long clientId)
    {
        return List.of();
    }

    @Override
    public void returnCar(long carId, long clientId)
    {

    }

    @Override
    public boolean isCarRented(long carId)
    {
        return false;
    }
}
