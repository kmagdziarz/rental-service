package com.acme.rental;

public class Car
{
    private long carId;
    private CarStatus status;

    public Car(long carId)
    {
        this.carId = carId;
        this.status = CarStatus.AVAILABLE;
    }

    public long getCarId()
    {
        return carId;
    }

    public void setCarId(long carId)
    {
        this.carId = carId;
    }

    public CarStatus getStatus()
    {
        return status;
    }

    public void setStatus(CarStatus status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Car{" +
                "carId=" + carId +
                ", status=" + status +
                '}';
    }
}
