package com.acme.rental;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class CarRentalTask2Test
{
    public static final int WRONG_CAR_ID = 100;
    private static final long NUMBER_OF_THREADS = 10;
    private static final long CAR_1_ID = 1;
    private static final long CAR_20_ID = 20;
    private static final long CAR_30_ID = 30;

    @Test
    public void shouldScoreTheGamesInThreadSafeManner() throws InterruptedException
    {
        assertSingleThreadResultIsTheSameAsMultithreadedOne(1);
        assertSingleThreadResultIsTheSameAsMultithreadedOne(10);
        assertSingleThreadResultIsTheSameAsMultithreadedOne(100);
        assertSingleThreadResultIsTheSameAsMultithreadedOne(1000);
        assertSingleThreadResultIsTheSameAsMultithreadedOne(10_000);
        assertSingleThreadResultIsTheSameAsMultithreadedOne(100_000);
        assertSingleThreadResultIsTheSameAsMultithreadedOne(1_000_000);
    }

    private static List<Long> getAllReservedCars(Supplier<ExecutorService> executorSupplier, Collection<Consumer<CarRentalService>> tasks) throws InterruptedException
    {
        final ExecutorService executor = executorSupplier.get();
        final CarRentalService carRentalService = new CarRentalServiceImpl(IntStream.rangeClosed(1, 50).mapToObj(Car::new).toList());

        List<Future<?>> futures = tasks.stream()
                .map(t -> (Runnable) () -> t.accept(carRentalService))
                .map(executor::submit)
                .collect(toUnmodifiableList());

        executor.shutdown();
        boolean terminated = executor.awaitTermination(NUMBER_OF_THREADS, TimeUnit.SECONDS);
        assertThat(terminated).withFailMessage("Renting hasn't finished in 10 seconds.")
                .isTrue();

        futures.forEach(f -> assertThatNoException().isThrownBy(f::get));

        var allClients = carRentalService.getAllClients();
        var allReservedCars = allClients.stream().map(Client::getRentedCarIds).flatMap(Collection::stream).toList();

        assertThat(allReservedCars).isNotNull();
        assertThat(allReservedCars.size()).isEqualTo(3);

        return allReservedCars;
    }

    private static Collection<Consumer<CarRentalService>> generateTasks(int numberOfTasks)
    {
        return LongStream.range(0, numberOfTasks)
                .mapToObj(CarRentalTask2Test::rentCar)
                .collect(toList());
    }

    private static Consumer<CarRentalService> rentCar(long clientId)
    {
        return carRentalService -> {
            carRentalService.rentCar(CAR_1_ID, clientId);
            carRentalService.rentCar(CAR_20_ID, clientId);
            carRentalService.rentCar(CAR_30_ID, clientId);
            carRentalService.rentCar(WRONG_CAR_ID, clientId);
        };
    }

    private void assertSingleThreadResultIsTheSameAsMultithreadedOne(int numberOfTasks) throws InterruptedException
    {
        Collection<Consumer<CarRentalService>> allTasks = generateTasks(numberOfTasks);

        var singleThreadResult = getAllReservedCars(Executors::newSingleThreadExecutor, allTasks);
        var multiThreadedResult = getAllReservedCars(() -> Executors.newFixedThreadPool(10), allTasks);

        assertThat(singleThreadResult).isEqualTo(multiThreadedResult);
    }

}
