package com.example.hoatran.polyshop.View.SanPham.LoaiSanPham;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.hoatran.polyshop.Adapters.AdapterLoaiSp;
import com.example.hoatran.polyshop.Model.Banner.ModelBanner;
import com.example.hoatran.polyshop.Object.Banner;
import com.example.hoatran.polyshop.Object.LoaiSanPham;
import com.example.hoatran.polyshop.Presenter.SanPham.PresenterLogicLoaiSp;
import com.example.hoatran.polyshop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoaiSanPhamFragment extends Fragment implements ViewLoaiSp {

    RecyclerView rvLoaiSP;
    PresenterLogicLoaiSp presenterLogicLoaiSp;
    AdapterLoaiSp adapter;
    SliderLayout sliderLayout;
    List<Banner> listBanner;
    ModelBanner modelBanner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.loai_san_pham_fragment, container, false);
        rvLoaiSP=view.findViewById(R.id.rv_LoaiSp);
        rvLoaiSP.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLoaiSP.setHasFixedSize(true);
        sliderLayout=view.findViewById(R.id.slider);
        presenterLogicLoaiSp=new PresenterLogicLoaiSp(this);
        presenterLogicLoaiSp.GetLoaisp();

        listBanner=new ArrayList<>();
        modelBanner=new ModelBanner();
        listBanner=modelBanner.LoadBanner();
        Slider();
        return view;
    }

    private void Slider() {
        HashMap<String,String> bannerMap=new HashMap<>();
        for (Banner item:listBanner)
        {
            bannerMap.put(item.getName(),item.getImage());
        }
        for (String name:bannerMap.keySet())
        {
            TextSliderView textSliderView=new TextSliderView(getContext());
            textSliderView.description(name)
                    .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);

        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(5000);
    }

    @Override
    public void LoadLoaiSp(List<LoaiSanPham> list) {
        adapter=new AdapterLoaiSp(list,getContext());

        rvLoaiSP.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
