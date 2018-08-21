package com.spx.bottomsheetbehaviorexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomSheetListBehavior behavior;

    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NestedScrollView nestedScrollView = findViewById(R.id.bottom_sheet);
        ViewGroup.LayoutParams layoutParams = nestedScrollView.getLayoutParams();
        CoordinatorLayout.Behavior b = ((CoordinatorLayout.LayoutParams) layoutParams)
                .getBehavior();
        behavior = (BottomSheetListBehavior) b;
        behavior.setAnchorView(findViewById(R.id.list_title_tv));
        behavior.setRecyclerView((RecyclerView) findViewById(R.id.recyclerView));

        findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {

        for (int i = 0; i < 100; i++) {
            data.add("asdfasfdasdfasdf" + i);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NormalAdapter());
    }


    static class VH extends RecyclerView.ViewHolder {
        public final TextView title;

        public VH(View v) {
            super(v);
            title = v.findViewById(R.id.title_tv);
        }
    }

    class NormalAdapter extends RecyclerView.Adapter<VH> {

        public NormalAdapter() {
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.title.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new VH(v);
        }
    }
}
