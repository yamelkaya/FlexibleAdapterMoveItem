package io.centaurea.topsnappedsmoothscrolling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;

public class MainActivity extends AppCompatActivity {
    RecyclerView list;
    NumberPicker numberPicker;
    private List<ListItem> items;
    FlexibleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = generateItems();
        list = (RecyclerView) findViewById(R.id.list);
        adapter = new FlexibleAdapter(items);
        adapter.setDisplayHeadersAtStartUp(true)
                .setAnimationOnScrolling(true);
        adapter.enableLogs(true);
        list.setAdapter(adapter);
        list.setLayoutManager(new SmoothScrollLinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
//        numberPicker = ((NumberPicker)findViewById(R.id.position));
//        numberPicker.setMinValue(0);
//        numberPicker.setMaxValue(list.getAdapter().getItemCount() - 1);

//        findViewById(R.id.scroll_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = numberPicker.getValue();
//                scrollToPosition(position);
//            }
//        });

        findViewById(R.id.move_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move();
            }
        });
    }

    private void scrollToPosition(final int position){
        list.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.getLayoutManager().smoothScrollToPosition(list,null,position);
            }
        },200);
    }

    private void move(){
        int firstPosition = ((LinearLayoutManager) list.getLayoutManager()).findFirstVisibleItemPosition();
        adapter.moveItem(firstPosition,firstPosition + 3);
    }

    private List<ListItem> generateItems(){
        List<ListItem> items = new ArrayList<ListItem>();
        for (int i=0; i<20; i++){
            ListHeader header = new ListHeader(i, false);
            for (int j=0; j<5; j++){
                ListItem item = new ListItem(header,j, false);
                items.add(item);
            }
        }

        return items;
    }
}
