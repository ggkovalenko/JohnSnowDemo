package com.ggkovalenko.johnsnow.arch;

import android.os.AsyncTask;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public abstract class AbstractPresenter<V extends AbstractView> {

    @NonNull
    private final V view;
    @NonNull
    private final Set<Job<?>> activeJobs = new HashSet<>();
    private boolean isResumed = false;

    protected AbstractPresenter(@NonNull final V view) {
        this.view = view;
    }

    @NonNull
    protected final V getView() {
        return view;
    }

    protected boolean isResumed() {
        return isResumed;
    }

    @CallSuper
    protected void create() {
    }

    @CallSuper
    protected void resume() {
        isResumed = true;
    }

    @CallSuper
    protected void pause() {
        isResumed = false;
    }

    @CallSuper
    protected void destroy() {
        for (final Job<?> job : activeJobs) {
            job.cancel(true);
            activeJobs.remove(job);
        }
    }

    protected <R> void invoke(@NonNull final Callable<R> backgroundCallable,
                              @NonNull final Callback<R> callback) {
        final Job<R> task = new Job<>(activeJobs, backgroundCallable, callback);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public interface Callback<R> {
        void onResult(@NonNull final Result<R> result);
    }

}
