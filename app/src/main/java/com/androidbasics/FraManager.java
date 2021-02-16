package com.androidbasics;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

public class FraManager extends FragmentPagerAdapter
{
    private final int tab_no;

    public FraManager(@NonNull androidx.fragment.app.FragmentManager fm, int behavior, int tab_no) {
        super(fm, behavior);
        this.tab_no = tab_no;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:return new fragment1();
            case 1:return new fragment2();
            case 2:return new fragment3();
            default:return  null;
        }
    }

    @Override
    public int getCount() {
        return tab_no;
    }
}
