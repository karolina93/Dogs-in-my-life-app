package pl.dogsinmylife.breed.breedList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.dogsinmylife.R;
import pl.dogsinmylife.models.Breed;


public class BreedListFragment extends Fragment implements BreedListContract.View, BreedItemListener {
    public BreedListContract.Presenter presenter;

    private RecyclerView recyclerView;
    private TextView noBreedsTv;

    private BreedListAdapter adapter;

    public static BreedListFragment newInstance() {
        return new BreedListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breed_list, container, false);

        recyclerView = view.findViewById(R.id.breed_list_recycler);
        noBreedsTv = view.findViewById(R.id.breed_list_no_breeds);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BreedListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }


    @Override
    public void setNoBreeds(boolean noBreeds) {
        if (noBreedsTv != null) {
            noBreedsTv.setVisibility(noBreeds ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void loadBreeds(List<Breed> breeds) {
        setNoBreeds(breeds == null || breeds.size() == 0);
        if (adapter != null) {
            adapter.updateData(breeds);
        }
    }

    @Override
    public void updateBreeds(List<Breed> breeds) {
        setNoBreeds(breeds == null || breeds.size() == 0);
        if (adapter != null) {
            adapter.updateData(breeds);
        }
    }

    @Override
    public void setPresenter(BreedListContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }

    @Override
    public void onItemButtonClicked(Breed breed) {
        presenter.updateBreed(breed, !breed.isLiked());
    }
}
