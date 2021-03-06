/*
 * Copyright (C) 2016 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit2.adapter.rxjava2;

import java.lang.reflect.Type;

import javax.annotation.Nullable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

final class RxJavaCallAdapter2<R> implements CallAdapter<R, Object> {
  private  Type responseType;
  private  @javax.annotation.Nullable
  Scheduler scheduler;
  private  boolean isAsync;
  private  boolean isResult;
  private  boolean isBody;
  private  boolean isFlowable;
  private  boolean isSingle;
  private  boolean isMaybe;
  private  boolean isCompletable;

  RxJavaCallAdapter2(Type responseType, @Nullable Scheduler scheduler, boolean isAsync,
                     boolean isResult, boolean isBody, boolean isFlowable, boolean isSingle, boolean isMaybe,
                     boolean isCompletable) {
    this.responseType = responseType;
    this.scheduler = scheduler;
    this.isAsync = isAsync;
    this.isResult = isResult;
    this.isBody = isBody;
    this.isFlowable = isFlowable;
    this.isSingle = isSingle;
    this.isMaybe = isMaybe;
    this.isCompletable = isCompletable;
  }


  @Override
  public Type responseType() {
    return responseType;
  }

  @Override
  public Object adapt(Call<R> call) {
    Observable<Response<R>> responseObservable = isAsync
            ? new CallEnqueueObservable<>(call)
            : new CallExecuteObservable<>(call);

    Observable<?> observable;
    if (isResult) {
      observable = new ResultObservable<>(responseObservable);
    } else if (isBody) {
      observable = new BodyObservable<>(responseObservable);
    } else {
      observable = responseObservable;
    }

    if (scheduler != null) {
      observable = observable.subscribeOn(scheduler);
    }

    if (isFlowable) {
      return observable.toFlowable(BackpressureStrategy.LATEST);
    }
    if (isSingle) {
      return observable.singleOrError();
    }
    if (isMaybe) {
      return observable.singleElement();
    }
    if (isCompletable) {
      return observable.ignoreElements();
    }
    return observable;
  }
}
