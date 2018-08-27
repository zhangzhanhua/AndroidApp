package com.example.zhang.mvp.model;

import com.example.zhang.mvp.model.bean.MainDataBean;
import com.example.zhang.mvp.model.bean.ProductBean;
import com.example.zhang.mvp.model.service.ServiceManager;
import com.example.zhang.mvp.model.service.api.MainActivityApi;
import com.example.zhang.utils.LogUtils;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainModel {
    private String TAG = MainModel.class.getSimpleName();

    /**
     * 获得创建RxJava数据
     *
     * @return
     */
    public Observable<Integer> getRxJavaCreateExampleData() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.debug(TAG, "getRxJavaCreateExampleData---:" + Thread.currentThread().getName() + "--:" + 1);
                emitter.onNext(1);

                LogUtils.debug(TAG, "getRxJavaCreateExampleData---:" + Thread.currentThread().getName() + "--:" + 2);
                emitter.onNext(2);
//                Thread.sleep(5000);
                LogUtils.debug(TAG, "getRxJavaCreateExampleData---:" + Thread.currentThread().getName() + "--:" + 3);
                emitter.onNext(3);
                emitter.onComplete();
                LogUtils.debug(TAG, "getRxJavaCreateExampleData---:" + Thread.currentThread().getName() + "--:" + 4);
                emitter.onNext(4);

            }
        });
    }

    public Observable<String> getRxJavaStringData() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                LogUtils.debug(TAG, "getRxJavaStringData---:" + Thread.currentThread().getName() + "--:A");
                emitter.onNext("A");
                LogUtils.debug(TAG, "getRxJavaStringData---:" + Thread.currentThread().getName() + "--:B");
                emitter.onNext("B");
                LogUtils.debug(TAG, "getRxJavaStringData---:" + Thread.currentThread().getName() + "--:C");
                emitter.onNext("C");
            }
        });
    }

    public Observable<Integer> getRxJavaDistinctData() {
        return Observable.just(1, 2, 2, 1, 1, 2, 3, 4, 5, 2);
    }

    public Observable<Integer> getRxJavaDebounceData() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 1);
                emitter.onNext(1);
                Thread.sleep(500);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 2);
                emitter.onNext(2);
                Thread.sleep(100);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 3);
                emitter.onNext(3);
                Thread.sleep(200);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 4);
                emitter.onNext(4);
                Thread.sleep(10);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 5);
                emitter.onNext(5);
                Thread.sleep(500);
                LogUtils.error(TAG, "getRxJavaDebounceData--:" + Thread.currentThread().getName() + "-emitter-:" + 6);
                emitter.onNext(6);
            }
        });
    }

    public Flowable<Integer> getRxJavaFlowableData() {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + 1);
                emitter.onNext(1);
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + 2);
                emitter.onNext(2);
//                Thread.sleep(5000);
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + 3);
                emitter.onNext(3);
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:onComplete");
                emitter.onComplete();
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-:" + emitter.requested());
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + 4);
                emitter.onNext(4);
            }
        }, BackpressureStrategy.ERROR);
    }

    public Flowable<Integer> getRxJavaFlowableSizeData() {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            boolean isFlag = false;

            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-start:" + emitter.requested());

                for (int i = 0; ; i++) {
                    if (!isFlag) {
                        LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-emit:" + emitter.requested());
                        if (emitter.requested() > 0) {
                            LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + i);
                            emitter.onNext(i);
                        } else {
                            isFlag = true;
                            LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-emit: end ");
                        }
                    }

                }

            }
        }, BackpressureStrategy.ERROR);
    }

    public Flowable<Integer> getRxJavaFlowableRealExample() {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 200; i++) {
                    while (emitter.requested() == 0) {
                        if (emitter.isCancelled()) {
                            break;
                        }
                    }
                    LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "-request-emit:" + emitter.requested());
                    LogUtils.debug(TAG, "getRxJavaFlowableData---:" + Thread.currentThread().getName() + "--:" + i);
                    emitter.onNext(i);
                }

            }
        }, BackpressureStrategy.ERROR);
    }

    public Flowable<Integer> getRxJavaFlowable128Data() {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 129; i++) {
                    LogUtils.debug(TAG, "getRxJavaFlowable128Data---:" + Thread.currentThread().getName() + "--:" + i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR);
    }

    public Observable<MainDataBean> getMainData() {
        return ServiceManager.getInstance().retrofit.create(MainActivityApi.class).getMainData();
    }

    /**
     * 获得首页产品数据
     *
     * @return
     */
    public Flowable<List<ProductBean>> getProductData() {
        List<ProductBean> productBeanList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ProductBean productBean = new ProductBean("productName-" + i, 100 + i);
            productBeanList.add(productBean);
        }
        return Flowable.just(productBeanList);
    }
}