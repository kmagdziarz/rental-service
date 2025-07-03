package com.acme.rental;

import java.util.ArrayList;
import java.util.List;

public final class Client
{
    private long clientId;
    private List<Long> rentedCarIds;

    public Client(long clientId)
    {
        this.clientId = clientId;
        rentedCarIds = new ArrayList<>();
    }

    public long getClientId()
    {
        return clientId;
    }

    public void setClientId(long clientId)
    {
        this.clientId = clientId;
    }

    public List<Long> getRentedCarIds()
    {
        return rentedCarIds;
    }

    public void setRentedCarIds(List<Long> rentedCarIds)
    {
        this.rentedCarIds = rentedCarIds;
    }

    @Override
    public String toString()
    {
        return "Client{" +
                "clientId=" + clientId +
                ", rentedCarIds=" + rentedCarIds +
                '}';
    }
}
