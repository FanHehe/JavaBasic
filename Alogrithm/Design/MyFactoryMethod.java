package Alogrithm.Design;

import java.util.Optional;
import java.lang.reflect.InvocationTargetException;

public class MyFactoryMethod {

    abstract class Product {

        public Product() {}

        public abstract void method();
    }

    class ConcreteProduct1 extends Product {

        public ConcreteProduct1() {
            super();
        }

        public void method() {
            System.out.println("fanhehe");
        }
    }

    class ConcreteProduct2 extends Product {

        public ConcreteProduct2() {}

        public void method() {}
    }

    public abstract class Creator {
        public abstract Product create(Class<? extends Product> c);
    }

    public class ConcreteCreator extends Creator {
        // PECS
        @Override
        public Product create(Class<? extends Product> c) {

            System.out.println(c);

            Product product = null;

            try {
                product = (Product)Class
                .forName(c.getName())
                .getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            return product;
        }
    }


    public static void main(String[] args) {
        MyFactoryMethod sample = new MyFactoryMethod();

        ConcreteCreator creator = sample.new ConcreteCreator();

        Product product = creator.create((sample.new ConcreteProduct1()).getClass());

        Optional<Product> p = Optional.ofNullable(product);

        p.map(i -> {
            i.method();
            return i;
        }).orElse(null);

    }
}

