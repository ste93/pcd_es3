package es3;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pcd.exam.TempSensor;


public class ObservableTempSensor {
    private int frequencyInMilliseconds;
    TempSensor tempSensor;
    //observable or flowable??
    Observable<Double> observableTempSensor;
    PublishSubject<Double> publishSubject;

    public ObservableTempSensor(int frequencyInMilliseconds) {
        this.frequencyInMilliseconds = frequencyInMilliseconds;
        createObservable();
    }

    private void createObservable() {
        tempSensor = new TempSensor(50, 75, 0.01);
        this.observableTempSensor = Observable.create(emitter -> {
            while(true) {
                emitter.onNext(tempSensor.getCurrentValue());
                Thread.sleep(this.frequencyInMilliseconds);
            }
        });
        //transforming cold observable/flowable into hot
        this.publishSubject = PublishSubject.create();
        new Thread(() -> {
            this.observableTempSensor.subscribe(this.publishSubject);
        }).start();
    }

/*
    //not sure if it is necessary
    public void SubscribeToObservable(Observer<Double> onNext) {
        this.connectableObservableTempSensor.subscribe(onNext);
    }
 */

    public PublishSubject<Double> getConnectableObservable() {
        return this.publishSubject;
    }
}
