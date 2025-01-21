package com.acme.rental;

import java.util.List;

public class CarRentalServiceImpl implements CarRentalService
{
    private final List<Car> cars;

    public CarRentalServiceImpl(List<Car> cars)
    {
        this.cars = cars;
    }

    @Override
    public Car rentCar(long carId, long clientId)
    {
        //TODO: Implement this method
        return null;
    }

    @Override
    public Car returnCar(long carId, long clientId)
    {
        //TODO: Implement this method
        return null;
    }

    @Override
    public List<Car> getAvailableCars()
    {
        //TODO: Implement this method
        return List.of();
    }

    @Override
    public List<Car> getAllRentedCarsByClient(long clientId)
    {
        //TODO: Implement this method
        return List.of();
    }

    @Override
    public boolean isCarRented(long carId)
    {
        //TODO: Implement this method
        return false;
    }

    @Override
    public List<Client> getAllClients()
    {
        //TODO: Implement this method
        return List.of();
    }

}
