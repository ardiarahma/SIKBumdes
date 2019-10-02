package com.ardiarahma.sik_bumdesa.networks.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ardiarahma.sik_bumdesa.activities.dashboard.JurnalViewFragment;

/**
 * Created by Windows 10 on 8/29/2019.
 */

public class JurnalViewPagerAdapter extends FragmentStatePagerAdapter{

    public JurnalViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        JurnalViewFragment jurnalViewFragment = new JurnalViewFragment();
        Bundle bundle = new Bundle();
        position = position + 1;
        bundle.putString("message", position+ " September 2019");
        jurnalViewFragment.setArguments(bundle);

        return jurnalViewFragment;
    }

    @Override
    public int getCount() {
        return 31;
    }

//    private ArrayList<View> views = new ArrayList<View>();
//
//    @Override
//    public int getItemPosition(Object object) {
//        int index = views.indexOf(object);
//        if (index == 1){
//            return POSITION_NONE;
//        }else {
//            return index;
//        }
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        View view = views.get(position);
//        container.addView(view);
//        return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(views.get(position));
//    }
//
//    @Override
//    public int getCount() {
//        return views.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view.equals(object);
//    }
//
//    public int addView(View view){
//        return addView(view, views.size());
//    }
//
//    public int addView(View view, int position) {
//        views.add(position, view);
//        return position;
//    }
//
//    public int removeView (ViewPager pager, View v)
//    {
//        return removeView (pager, views.indexOf (v));
//    }
//
//    public int removeView(ViewPager pager, int position){
//        pager.setAdapter(null);
//        views.remove(position);
//        pager.setAdapter(this);
//        return position;
//    }
//
//    public View getView(int position){
//        return views.get(position);
//    }

    //    @Override
//    public int getCount() {
//        return jurnals.size();
//    }
//
//
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view.equals(object);
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        layoutInflater = LayoutInflater.from(context);
//        View view = layoutInflater.inflate(R.layout.jurnal_item, container, false);
//
//        TextView date;
//        TextView tv_keterangan, tv_nAkun, tv_kwitansi, tv_posisi;
//
//        date = view.findViewById(R.id.jurnal_date);
//        tv_keterangan = view.findViewById(R.id.tv_keterangan);
//        tv_nAkun = view.findViewById(R.id.tv_nAkun);
//        tv_kwitansi = view.findViewById(R.id.tv_kwitansi);
//        tv_posisi = view.findViewById(R.id.tv_posisi);
//
//        tv_keterangan.setText(jurnals.get(position).getKeterangan());
//        tv_nAkun.setText(jurnals.get(position).getNo_akun());
//        tv_kwitansi.setText(jurnals.get(position).getNo_kwitansi());
//        tv_posisi.setText(jurnals.get(position).getPosisi_normal());
//
//        container.addView(view, 0);
//
//        return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
//        container.removeView((View)object);
//    }
}
