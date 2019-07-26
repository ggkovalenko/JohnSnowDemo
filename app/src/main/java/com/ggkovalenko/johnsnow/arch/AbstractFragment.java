package com.ggkovalenko.johnsnow.arch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class AbstractFragment<P extends AbstractPresenter> extends Fragment {

    @NonNull
    private P presenter;

    @LayoutRes
    protected abstract int getLayoutId();

    @NonNull
    protected abstract P createPresenter();

    @NonNull
    protected final P getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.create();
    }

    @NonNull
    @Override
    public final View onCreateView(@NonNull final LayoutInflater inflater,
                                   @Nullable final ViewGroup container,
                                   @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    @CallSuper
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
