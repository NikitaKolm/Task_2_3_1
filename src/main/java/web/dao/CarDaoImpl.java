package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    private final List<Car> cars;

    public CarDaoImpl() {
        this.cars = new ArrayList<>();
        cars.add(new Car("Maker1", "model1", 1));
        cars.add(new Car("Maker2", "model2", 2));
        cars.add(new Car("Maker3", "model3", 3));
        cars.add(new Car("Maker4", "model4", 4));
        cars.add(new Car("Maker5", "model5", 5));
    }

    @Override
    public List<Car> getCarList(Integer count) {
        if(count == null || count >= 5) {
            return cars;
        }
        return cars.subList(0, count);
    }
}
