import es3.ObservableTempSensor;
import io.reactivex.schedulers.Schedulers;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        ObservableTempSensor observableTempSensor1 = new ObservableTempSensor(1000);
        ObservableTempSensor observableTempSensor2 = new ObservableTempSensor(1000);
        ObservableTempSensor observableTempSensor3 = new ObservableTempSensor(1000);
        System.out.println("init");

        observableTempSensor1.getConnectableObservable().subscribe(
                a -> {
                    System.out.println("received2: " + a);
                },
                b -> {
                    System.out.println("error2: " + b);
                },
                () -> {
                    System.out.println("completed2: ");
                },
                a -> {
                    System.out.println("subscribed2: " + a);
                });


        observableTempSensor2.getConnectableObservable().subscribeOn(Schedulers.computation()).subscribe(
                integer -> {
                    System.out.println("subcriber 2  " + integer);
                });

        observableTempSensor3.getConnectableObservable().subscribe(
                integer -> {
                    System.out.println("subcriber 3  " + integer);
                });
    }
}