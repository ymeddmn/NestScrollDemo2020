package com.mage.nestscrollelm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.mage.nestscrollelm.R;
import com.mage.nestscrollelm.behovior.CustomNestBehavior;

/**
 * 使用Behavior实现Nest机制的滑动嵌套
 */
public class BehaviorNestActivity extends AppCompatActivity {

    private RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_nest);
        recycle = findViewById(R.id.recycle);
        CheckBox cb = findViewById(R.id.cb_behavior_nest);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        recycle.setAdapter(new RecyclerView.Adapter<BehaviorHolder>() {

            @NonNull
            @Override
            public BehaviorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Button btn = new Button(BehaviorNestActivity.this);
                BehaviorHolder holder = new BehaviorHolder(btn);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull BehaviorHolder holder, int position) {
                holder.btn.setText(position+"");
            }

            @Override
            public int getItemCount() {
                return 50;
            }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CustomNestBehavior.isHeaderFollowRecycle=isChecked;
            }
        });
    }

    class BehaviorHolder extends RecyclerView.ViewHolder {
        public Button btn;

        public BehaviorHolder(@NonNull View itemView) {
            super(itemView);
            btn = (Button) itemView;
        }
    }
}