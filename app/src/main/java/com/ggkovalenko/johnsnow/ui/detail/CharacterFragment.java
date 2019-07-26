package com.ggkovalenko.johnsnow.ui.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ggkovalenko.johnsnow.JohnSnowApplication;
import com.ggkovalenko.johnsnow.R;
import com.ggkovalenko.johnsnow.arch.AbstractFragment;
import com.ggkovalenko.johnsnow.data.ExternalDatasource;
import com.ggkovalenko.johnsnow.data.model.CharacterDetails;
import com.ggkovalenko.johnsnow.presentation.detail.CharacterPresenter;
import com.ggkovalenko.johnsnow.presentation.detail.CharacterView;
import com.ggkovalenko.johnsnow.presentation.detail.Detail;

import java.util.List;

public final class CharacterFragment extends AbstractFragment<CharacterPresenter> implements CharacterView {

    public static final String TAG = CharacterFragment.class.getSimpleName();
    private static final String EXTRA_CHARACTER_URL = "character_url";

    @NonNull
    private Toolbar toolbar;
    @NonNull
    private ProgressBar progressBar;
    @NonNull
    private RecyclerView recyclerView;

    @NonNull
    private final DetailsAdapter adapter = new DetailsAdapter();

    public static CharacterFragment newInstance(@NonNull final String characterUrl) {
        final Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CHARACTER_URL, characterUrl);

        final CharacterFragment fragment = new CharacterFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @NonNull
    @Override
    protected CharacterPresenter createPresenter() {
        final JohnSnowApplication application = (JohnSnowApplication) getContext().getApplicationContext();
        final ExternalDatasource datasource = application.getExternalDatasource();

        final String characterUrl = getArguments().getString(EXTRA_CHARACTER_URL);

        return new CharacterPresenter(this, datasource, characterUrl);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_character;
    }

    @Override
    public void onViewCreated(@NonNull final View view,
                              @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        progressBar = view.findViewById(R.id.progress_bar);

        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoading(final boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showTitle(@NonNull final String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void showDetails(@NonNull final List<Detail> details) {
        adapter.swap(details);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(@NonNull final Throwable throwable) {
        Log.e(TAG, "error loading character", throwable);
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
