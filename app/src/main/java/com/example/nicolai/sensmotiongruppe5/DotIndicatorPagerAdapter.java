package com.example.nicolai.sensmotiongruppe5;


import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.List;

public class DotIndicatorPagerAdapter extends PagerAdapter {

    private static final List<Item> items =
            Arrays.asList(new Item(R.color.colorAccent), new Item(R.color.colorPrimary));

    @NonNull @Override public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View item = LayoutInflater.from(container.getContext())
                .inflate(R.layout.activity_min__data, container, false);
        //CardView cardView = item.findViewById(R.id.card_view);
        //cardView.setCardBackgroundColor(
        //    ContextCompat.getColor(container.getContext(), (items.get(position).color)));
        container.addView(item);
        return item;
    }

    @Override public int getCount() {
        return items.size();
    }

    @Override public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    private static class Item {
        private final int color;

        private Item(int color) {
            this.color = color;
        }
    }
}

