package com.example.dell_pc.qinyongcongyuekao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell_pc.qinyongcongyuekao.Bean;
import com.example.dell_pc.qinyongcongyuekao.R;
import com.example.dell_pc.qinyongcongyuekao.utif.Heple;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/*
 * 作者：秦永聪
 *日期：2018/9/25
 * */public class Fragmentone extends Fragment {

     private XListView mxlistview;
     private MyAdpater myAdpater;
     private List<Bean.DataBeanX.DataBean> list=new ArrayList<>();
 int page=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, container, false);
        mxlistview=view.findViewById(R.id.mxlistview);
        dohttp(page);
       myAdpater=new MyAdpater();

        mxlistview.setPullLoadEnable(true);
        mxlistview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page=1;
                list.clear();
              dohttp(page);
            }

            @Override
            public void onLoadMore() {
               page=1;
               dohttp(page);

            }
        });
        mxlistview.setAdapter(myAdpater);
        return view;
    }

    private void dohttp(int page) {
        String url="http://365jia.cn/news/api3/365jia/news/categories/hotnews?page="+page;
        new Heple().get(url).Hd(new Heple.Cr() {
            @Override
            public void success(String mm) {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(mm, Bean.class);
                List<Bean.DataBeanX.DataBean> data = bean.getData().getData();
              //  Toast.makeText(getActivity(),""+data,Toast.LENGTH_LONG).show();
               // list.clear();
                 list.addAll(data);
                 myAdpater.notifyDataSetChanged();
                 mxlistview.stopRefresh();
                 mxlistview.stopLoadMore();
            }
        });
    }

    class MyAdpater extends BaseAdapter{
        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            List<String> pics = list.get(position).getPics();
            int s=0;
            if(pics==null&&pics.size()==0){
                s=0;
            }
            else{
                s=1;
            }
            return s;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            int type = getItemViewType(i);
            switch (type){
                case 0:
                    Viewhodel viewhodel;
                    if(view==null){
                        viewhodel=new Viewhodel();
                        view=View.inflate(getActivity(),R.layout.item,null);
                        viewhodel.t1= view.findViewById(R.id.t1);
                        view.setTag(viewhodel);
                    }
                    else{
                        viewhodel= (Viewhodel) view.getTag();
                    }
                    viewhodel.t1.setText(list.get(i).getTitle());
                    break;
                case 1:
                    Viewhode2 viewhode2;
                    if(view==null){
                        viewhode2=new Viewhode2();
                        view=View.inflate(getActivity(),R.layout.item1,null);
                        viewhode2.t2= view.findViewById(R.id.t2);
                        view.setTag(viewhode2);
                    }
                    else{
                        viewhode2= (Viewhode2) view.getTag();
                    }
                    viewhode2.t2.setText(list.get(i).getTitle());
                   // ImageLoader.getInstance().displayImage(list.get(i).getPics().get(0),viewhode2.img);
                    break;

            }

            return view;
        }
    }
    class Viewhodel{
        TextView t1;
    }
    class Viewhode2{
        TextView t2;
        ImageView img;
    }
}
