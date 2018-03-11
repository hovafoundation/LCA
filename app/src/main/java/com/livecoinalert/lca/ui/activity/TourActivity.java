/*
package com.dreampany.demo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.dreampany.demo.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;

import static com.ihongqiqu.util.RUtils.R;

*/
/**
 * Created by nuc on 6/13/2017.
 *//*


public class TourActivity extends IntroActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //setFullscreen(true);
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final Slide loadSlide, first, second, third;
 */
/*       loadSlide = new FragmentSlide.Builder()
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .fragment(LoadingFragment.newInstance())
                .build();*//*


        first = new SimpleSlide.Builder()
                .title(getString(R.string.title_first_tour))
                .description(getString(R.string.description_first_tour))
                .image(R.mipmap.ic_launcher_web)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .scrollable(true)
                .build();

        second = new SimpleSlide.Builder()
                .title(getString(R.string.title_second_tour))
                .description(getString(R.string.description_second_tour))
                .image(R.mipmap.ic_launcher_web)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .scrollable(true)
                .build();

        third = new SimpleSlide.Builder()
                .title(getString(R.string.title_third_tour))
                .description(getString(R.string.description_third_tour))
                .image(R.mipmap.ic_launcher_web)
                .background(R.color.colorPrimary)
                .backgroundDark(R.color.colorPrimaryDark)
                .scrollable(true)
                .build();

        addSlide(first);
        addSlide(second);
        addSlide(third);

        setButtonBackVisible(true);

*/
/*        setNavigationPolicy(new NavigationPolicy() {
            @Override
            public boolean canGoForward(int i) {
                return WordPref.onInstance(getApplication()).isLoaded();
            }

            @Override
            public boolean canGoBackward(int i) {
                return WordPref.onInstance(getApplication()).isLoaded();
            }
        });*//*



        */
/*addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //LogKit.verbose("onPageScrolled " + position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*//*


    }


}
*/
