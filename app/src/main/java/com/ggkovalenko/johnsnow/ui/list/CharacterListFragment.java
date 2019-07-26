package com.ggkovalenko.johnsnow.ui.list;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ggkovalenko.johnsnow.JohnSnowApplication;
import com.ggkovalenko.johnsnow.R;
import com.ggkovalenko.johnsnow.arch.AbstractFragment;
import com.ggkovalenko.johnsnow.data.ExternalDatasource;
import com.ggkovalenko.johnsnow.data.model.CharacterShort;
import com.ggkovalenko.johnsnow.presentation.list.CharacterListPresenter;
import com.ggkovalenko.johnsnow.presentation.list.CharacterListView;
import com.ggkovalenko.johnsnow.ui.detail.CharacterFragment;
import com.ggkovalenko.johnsnow.util.PageScrollListener;

import java.util.List;

public final class CharacterListFragment extends AbstractFragment<CharacterListPresenter> implements
        CharacterListView, CharacterListAdapter.OnItemClickListener, PageScrollListener.Callbacks {

    public static final String TAG = CharacterListFragment.class.getSimpleName();
    private static final int PAGE_OFFSET = 50;

    @NonNull
    private final CharacterListAdapter adapter = new CharacterListAdapter(this);
    @NonNull
    private final PageScrollListener pageScrollListener = new PageScrollListener(PAGE_OFFSET, this);
    @NonNull
    private RecyclerView recyclerView;

    public static CharacterListFragment newInstance() {
        return new CharacterListFragment();
    }

    @NonNull
    @Override
    protected CharacterListPresenter createPresenter() {
        final JohnSnowApplication application = (JohnSnowApplication) getContext().getApplicationContext();
        final ExternalDatasource datasource = application.getExternalDatasource();

        return new CharacterListPresenter(this, datasource);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_character_list;
    }

    @Override
    public void onViewCreated(@NonNull final View view,
                              @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showCharacters(@NonNull final List<CharacterShort> characters) {
        // recyclerView.removeOnScrollListener(pageScrollListener);
        adapter.swap(characters);
        adapter.notifyDataSetChanged();
        recyclerView.addOnScrollListener(pageScrollListener);
    }

    @Override
    public void showCharactersError(@NonNull final Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        recyclerView.addOnScrollListener(pageScrollListener);
    }

    @Override
    public void openCharacter(@NonNull final String characterUrl) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, CharacterFragment.newInstance(characterUrl), CharacterFragment.TAG)
                .addToBackStack(CharacterFragment.TAG)
                .commit();
    }

    @Override
    public void onNextPageScrolled() {
        getPresenter().onNextPageScrolled();
    }

    @Override
    public void onItemClick(@NonNull CharacterShort character) {
        getPresenter().onItemClick(character);
    }

}
