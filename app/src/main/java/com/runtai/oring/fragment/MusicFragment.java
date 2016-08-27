package com.runtai.oring.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.runtai.oring.R;

public class MusicFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment, container, false);
        Log.e("onCreateView", "再次选中");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView music_tv = (TextView) getActivity().findViewById(R.id.music_tv);
        music_tv.setText(getArguments().getString("ARGS"));
        Log.e("onActivityCreated", "再次选中");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("测试","是我先退出吗？");
    }

    public static int num = 0;
    public static int getNum(){
        return num;
    }

    public static MusicFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
