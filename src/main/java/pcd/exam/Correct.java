package pcd.exam;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.concurrent.ExecutorService;

public class Correct {
    public static void main(String[] args) throws InterruptedException {

        TempSensor tempSensor = new TempSensor(50, 75, 0.01);
        //Cold observable or cold flowable is the right way to start
        Observable<Double> obs = Observable.create(emitter -> {
            while(true) {
                    emitter.onNext(tempSensor.getCurrentValue());
                    Thread.sleep(1000);


            }
        });
/*
        //publish subject seems better
        PublishSubject<Double> publishSubject = PublishSubject.
                create();
        new Thread(() -> {
            publishSubject.subscribe(a -> {
                        System.out.println("received: " + a);
                    },
                    b -> {
                        System.out.println("error: " + b);
                    },
                    () -> {
                        System.out.println("completed: ");
                    },
                    a -> {
                        System.out.println("subscribed: " + a);
                    });
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishSubject.subscribe(a -> {
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
        }).start();
        //this is blocking, after this nothing is executed
        obs.subscribe(publishSubject);



 */

        ConnectableObservable<Double> connectable = obs.publish();

        connectable.subscribe(System.out::println);


        new Thread(() -> {
            System.out.println("connecting...");
            try{
                Thread.sleep(5000);
            } catch(Exception e) {
                e.printStackTrace();
            }

            connectable.subscribe(integer -> {
                System.out.println("subcriber 1" + integer);
            });
        }).start();
        connectable.connect();

    }
}
