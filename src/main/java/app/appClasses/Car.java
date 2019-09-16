package app.appClasses;

public class Car {
    private String make;
    private String model;
    private String year;
    private String engine = "";
    private String transmission = "";
    private String fullName;

    public Car(String make, String model, String year) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fullName = String.format("%s %s %s", year, make, model);
    }

    public Car(String fullName) {
        this.fullName = fullName;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public boolean areEngineAndTransSaved() {
        return !(engine.equals("") && transmission.equals(""));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return getFullName().equals(car.getFullName()) &&
            (car.engine.contains(engine) || engine.contains(car.engine)) &&
            (car.transmission.contains(transmission) || transmission.contains(car.transmission));
    }

    public String getFullName() {
        return fullName;
    }
}
