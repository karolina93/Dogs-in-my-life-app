package pl.dogsinmylife.breed.viewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<FragmentTabTitle> fragmentTitleList;

    public ViewPagerAdapter(FragmentManager manager, List<FragmentTabTitle> fragmentTitleList) {
        super(manager);
        if (fragmentTitleList == null) {
            fragmentTitleList = new ArrayList<>();
        }
        this.fragmentTitleList = fragmentTitleList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentTitleList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragmentTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position).getTitle();
    }
}
