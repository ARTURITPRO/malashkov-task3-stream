package by.sologub;

import by.sologub.model.*;
import by.sologub.util.Util;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) throws IOException {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
//        task13();
//        task14();
//        task15();
        task16();
    }

    private static void task1() throws IOException {
        List<Animal> animals = Util.getAnimals();

         animals.stream().filter(s -> s.getAge() >= 10 && s.getAge() <= 20).sorted(Comparator.comparing(Animal::getAge))
                 .skip(14).limit(7).forEach(System.out::println);

    }

    private static void task2() throws IOException {
        List<Animal> animals = Util.getAnimals();
               animals.stream().filter(s->s.getOrigin().equals("Japanese")).filter(s->s.getGender().equals("Female")).
                       map(Animal::getBread).map(String::toUpperCase).forEach(System.out::println);

    }

    private static void task3() throws IOException {
        List<Animal> animals = Util.getAnimals();
                animals.stream().filter(s->s.getAge()>30).map(Animal::getOrigin).distinct()
                .filter(s->s.matches("A.+?")).forEach(System.out::println);
    }

    private static void task4() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().filter(s->s.getGender().equals("Female")).count());
    }

    private static void task5() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().filter(s -> s.getAge() >= 20 && s.getAge() <= 30).
        map(Animal::getOrigin).anyMatch(s->s.equals("Hungarian")));
    }

    private static void task6() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().map(Animal::getGender).anyMatch(s->!(s.equals("Female")|s.equals("Male"))));
    }

    private static void task7() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().map(Animal::getOrigin).anyMatch(s->s.equals("Oceania")));
    }

    private static void task8() throws IOException {
        List<Animal> animals = Util.getAnimals();
                animals.stream().sorted(Comparator.comparing(Animal::getBread)).limit(100)
                        .sorted(Comparator.comparingInt(Animal::getAge)).skip(99).forEach(System.out::println);
    }

    private static void task9() throws IOException {
        List<Animal> animals = Util.getAnimals();
                animals.stream().map(Animal::getBread).map(String::toCharArray)
                        .sorted(Comparator.comparingInt(Array::getLength)).limit(1)
                        .forEach(s->System.out.println(s.length));
    }

    private static void task10() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().map(Animal::getAge).reduce(Integer::sum).get());
    }

    private static void task11() throws IOException {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().filter(x->x.getOrigin().equals("Indonesian")).map(Animal::getAge)
        .mapToInt(s->s).average().getAsDouble());
    }

    private static void task12() throws IOException {
        List<Person> people = Util.getPersons();
            people.stream().filter(s->s.getGender().equals("Male"))
                    .filter(s->LocalDate.now().getYear()-s.getDateOfBirth().getYear()>18
                            &&LocalDate.now().getYear()-s.getDateOfBirth().getYear()<27)
                    .sorted(Comparator.comparing(Person::getRecruitmentGroup)).limit(200)
                    .forEach(System.out::println);
    }

    private static void task13() throws IOException {
        List<House> houses = Util.getHouses();
        List<Person> people = new ArrayList<>();
       houses.stream().filter(s->s.getBuildingType().equals("Hospital")).map(House::getPersonList).flatMap(Collection::stream).forEach(people::add);
        houses.stream().filter(s->!s.getBuildingType().equals("Hospital"))
                .map(House::getPersonList).flatMap(Collection::stream)
                .filter(d-> LocalDate.now().getYear()-d.getDateOfBirth().getYear()<18||LocalDate.now().getYear()-d.getDateOfBirth().getYear()>=63&&d.getGender().equals("Male")||
                        LocalDate.now().getYear()-d.getDateOfBirth().getYear()>=58&&d.getGender().equals("Female"))
                .limit(500-people.size()).forEach(people::add);
        people.forEach(System.out::println);
        System.out.println(people.size());
    }
   static Double summCar(List<Car> cars){
       return cars.stream().map(Car::getMass).map(x->x/1000*7.14).reduce(Double::sum).orElse(0.0);
    }
    static Integer revenue(List<Car> cars){
        return cars.stream().map(Car::getPrice).reduce(Integer::sum).orElse(0);
    }
    private static void task14() throws IOException {

        List<Car> cars = Util.getCars();
        List <Car> turkmenistan  = new ArrayList<>();
        List <Car> uzbekistan  = new ArrayList<>();
        List <Car> kazahstan  = new ArrayList<>();
        List <Car> kirgizstan  = new ArrayList<>();
        List <Car> russia  = new ArrayList<>();
        List <Car> mongolia  = new ArrayList<>();

        cars.stream().filter(s->s.getColor().equals("White")&&s.getCarMake().equals("Jaguar")).forEach(turkmenistan::add);
        cars.stream().filter(s->s.getMass()<=1500&&(s.getCarMake().equals("BMW")||s.getCarMake().equals("Lexus")||s
                .getCarMake().equals("Chrysler")||s.getCarMake().equals("Toyota"))).forEachOrdered(uzbekistan::add);
        cars.stream().filter(s->s.getMass()>=1500&&(s.getCarMake().equals("GMC")||s.getCarMake().equals("Dodge")))
                .forEachOrdered(kazahstan::add);
        cars.stream().filter(s->s.getReleaseYear()<1982&&(s.getCarModel().equals("Civic")||s.getCarModel().equals("Cherokee")))
                .forEachOrdered(kirgizstan::add);
        cars.stream().filter(s->(!s.getColor().equals("Yellow")||s.getColor().equals("Red")||s.getColor().equals("Green")
                ||s.getColor().equals("Blue"))&&s.getPrice()>40000).forEachOrdered(russia::add);
       cars.stream().filter(s->s.getVin().matches("\\w*59\\w*")).forEach(mongolia::add);


        System.out.println("Транспортные расходы из-за массы машин Казахстана = " +summCar(turkmenistan));
        System.out.println("Транспортные расходы из-за массы машин Узбекистана = " + summCar(uzbekistan));
        System.out.println("Транспортные расходы из-за массы машин Казахстана = " + summCar(kazahstan));
        System.out.println("Транспортные расходы из-за массы машин Киргизстана = " + summCar(kirgizstan));
        System.out.println("Транспортные расходы из-за массы машин России = " + summCar(russia));
        System.out.println("Транспортные расходы из-за массы машин Монголии = " + summCar(mongolia));

        double expenses = DoubleStream.of(summCar(turkmenistan),summCar(turkmenistan), summCar(kazahstan), summCar(kirgizstan),
                summCar(russia), summCar(mongolia)).reduce(Double::sum).orElse(0.0);
        System.out.println("Общие транспортные расходы компании = "+ expenses + "$");

        int profit = (int) (revenue(turkmenistan) + revenue(uzbekistan) + revenue(kazahstan) +revenue(kirgizstan) +revenue(russia) +
                        revenue(mongolia) - expenses);
        System.out.print("Общий доход компании = "+ profit + "$");
    }

    private static void task15() throws IOException {
        List<Flower> flowers = Util.getFlowers();
        List<Flower> myflowers = new LinkedList<>();
        double summProfit = flowers.stream().sorted(Comparator.comparing(Flower::getOrigin).reversed())
                .sorted(Comparator.comparing(Flower::getPrice).reversed())
                .sorted(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed())
                .filter(s->s.getCommonName().matches("^[C-X]\\w*")).filter(Flower::isShadePreferred)
                .filter(s->s.getFlowerVaseMaterial().contains("Glass")||s.getFlowerVaseMaterial().contains("Aluminum")||s.getFlowerVaseMaterial().contains("Steel"))
                .mapToDouble(s->s.getWaterConsumptionPerDay()/1000*365*1.39*5).sum();
        System.out.println("Затраты на потребление = " + summProfit);
    }

    /**
     * Цунами затопило всю территорию и потом ещё случился взрыв вулкана и землятрясение. Погибли все оставшиеся. Вывести список.
     *
     */
    private static void task16() throws IOException {
        List<House> houses = Util.getHouses();
        List<Person> people = new ArrayList<>();
        houses.stream().filter(s->s.getBuildingType().equals("Hospital")).map(House::getPersonList).flatMap(Collection::stream).forEach(people::add);
        houses.stream().filter(s->!s.getBuildingType().equals("Hospital"))
                .map(House::getPersonList).flatMap(Collection::stream)
                .filter(d-> LocalDate.now().getYear()-d.getDateOfBirth().getYear()<18||LocalDate.now().getYear()-d.getDateOfBirth().getYear()>=63&&d.getGender().equals("Male")||
                        LocalDate.now().getYear()-d.getDateOfBirth().getYear()>=58&&d.getGender().equals("Female"))
                .skip(500-people.size()).forEach(people::add);
        people.forEach(System.out::println);
        System.out.println(people.size());
    }
}