package pl.dogsinmylife.breed;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pl.dogsinmylife.App;
import pl.dogsinmylife.R;
import pl.dogsinmylife.breed.breedList.BreedListPresenter;
import pl.dogsinmylife.breed.viewPager.FragmentTabTitle;
import pl.dogsinmylife.breed.viewPager.ViewPagerAdapter;
import pl.dogsinmylife.breed.breedList.BreedListFragment;
import pl.dogsinmylife.breed.repository.BreedRepository;
import pl.dogsinmylife.rest.RestApi;
import pl.dogsinmylife.utils.SharedUtils;

public class BreedActivity extends AppCompatActivity implements BreedActivityContract.View {
    @Inject
    RestApi restApi;

    BreedActivityPresenter activityPresenter;
    private BreedListPresenter breedPresenterLiked;
    private BreedListPresenter breedPresenterNotLiked;
    private BreedRepository breedRepository;
    private ViewPager viewPager;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed);
        ((App) getApplication()).getNetComponent().inject(this);

        TabLayout tabLayout = findViewById(R.id.activity_breed_tab);
        viewPager = findViewById(R.id.activity_breed_view_pager);
        progressBar = findViewById(R.id.activity_breed_progress);
        tabLayout.setupWithViewPager(viewPager);

        breedRepository = new BreedRepository(restApi, SharedUtils.getPreferences(this));
        initTabs();
        activityPresenter = new BreedActivityPresenter(this, breedRepository);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityPresenter.stop();
    }


    @Override
    public void progressView(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    @Override
    public void showToastServerError() {
        Toast.makeText(this, getString(R.string.server_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToastNoNetwork() {
        Toast.makeText(this, getString(R.string.no_network), Toast.LENGTH_LONG).show();
    }

    @Override
    public void refreshTabs() {
        breedPresenterNotLiked.refresh();
        breedPresenterLiked.refresh();
    }

    private void initTabs() {
        BreedListFragment fragmentLiked = BreedListFragment.newInstance();
        BreedListFragment fragmentNotLiked = BreedListFragment.newInstance();

        breedPresenterLiked = new BreedListPresenter(fragmentLiked, breedRepository, true);
        breedPresenterNotLiked = new BreedListPresenter(fragmentNotLiked, breedRepository, false);

        List<FragmentTabTitle> fragmentTabTitleList = new ArrayList<>();
        fragmentTabTitleList.add(new FragmentTabTitle(fragmentLiked, getString(R.string.liked_dogs)));
        fragmentTabTitleList.add(new FragmentTabTitle(fragmentNotLiked, getString(R.string.not_liked_dogs)));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentTabTitleList);
        viewPager.setOffscreenPageLimit(fragmentTabTitleList.size());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setPresenter(BreedActivityContract.Presenter presenter) {

    }
}
