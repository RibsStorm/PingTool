package com.kusofan.demo.fivegen.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kusofan.demo.fivegen.R;
import com.kusofan.demo.fivegen.adapter.TestAdapter;
import com.kusofan.demo.fivegen.util.PingTools;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {


    private String BAIDU_URL = "www.baidu.com";

    private List<String> hosts;
    private List<PingTools.PingResult> result = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String count = getIntent().getExtras().getString("PING_COUNT");

        getHost();
        initView();
        startTest(count);
    }

    private void initView() {
        mAdapter = new TestAdapter(result);
        mRecyclerView = ((RecyclerView) findViewById(R.id.recycler));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<String> getHost() {
        hosts = new ArrayList<>();
        //可以自己添加不同的Host.
        hosts.add(BAIDU_URL);
        return hosts;
    }

    private void startTest(final String count) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String host : hosts) {
                    PingTools.PingResult pingResult = PingTools.ping(host, count);
                    result.add(pingResult);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PingTools.recycler();
    }
}