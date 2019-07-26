package com.ggkovalenko.johnsnow.arch;

import android.os.AsyncTask;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

final class Job<R> extends AsyncTask<Void, Void, Result<R>> {

    private static final long TIMEOUT = 5000L;

    @NonNull
    private final Set<Job<?>> activeJobs;
    @NonNull
    private final Callable<R> backgroundCallable;
    @NonNull
    private final AbstractPresenter.Callback<R> callback;
    @NonNull
    private final TimeoutCountdownTimer timeoutCountdownTimer = new TimeoutCountdownTimer(TIMEOUT);

    Job(@NonNull final Set<Job<?>> activeTasks,
        @NonNull final Callable<R> backgroundCallable,
        @NonNull final AbstractPresenter.Callback<R> callback) {
        this.activeJobs = activeTasks;
        this.backgroundCallable = backgroundCallable;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        activeJobs.add(this);
        timeoutCountdownTimer.start();
    }

    @Override
    protected Result<R> doInBackground(Void... voids) {
        try {
            final R value = backgroundCallable.call();
            return Result.success(value);
        } catch (Throwable throwable) {
            return Result.error(throwable);
        }
    }

    @Override
    protected void onPostExecute(@NonNull final Result<R> result) {
        timeoutCountdownTimer.cancel();
        activeJobs.remove(this);
        callback.onResult(result);
    }

    private final class TimeoutCountdownTimer extends CountDownTimer {

        TimeoutCountdownTimer(final long timeout) {
            super(timeout, timeout);
        }

        @Override
        public void onTick(final long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            Job.this.cancel(true);
            activeJobs.remove(Job.this);

            final Throwable timeoutThrowable = new TimeoutException("Timeout exceeded");
            callback.onResult(Result.error(timeoutThrowable));
        }
    }

}
