package com.etisalat.sampletask.common.helper;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by monica on 5/1/2018.
 */

public class RxBusEvent {


    private static RxBusEvent rxBusEvent;
    private PublishSubject<HashMap<Integer, String>> mSubject;

    private RxBusEvent() {
        mSubject = PublishSubject.create();
    }

    public static RxBusEvent getInstance() {
        if (rxBusEvent == null)
            rxBusEvent = new RxBusEvent();
        return rxBusEvent;
    }

    public void setRxBusEvent(HashMap<Integer, String> data) {
        mSubject.onNext(data);
    }

    public Observable<HashMap<Integer, String>> getEvent() {
        return mSubject;
    }


}
