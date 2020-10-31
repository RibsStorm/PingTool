package com.kusofan.demo.fivegen.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kusofan.demo.fivegen.R;
import com.kusofan.demo.fivegen.util.PingTools;

import java.util.List;

/**
 * @author 何铭
 * @data 2020/9/7
 */
public class TestAdapter extends BaseQuickAdapter<PingTools.PingResult, BaseViewHolder> {

    public TestAdapter(List<PingTools.PingResult> data) {
        super(R.layout.item_test, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PingTools.PingResult pingResult) {
        baseViewHolder.setText(R.id.tv_host, pingResult.host)
                .setText(R.id.tv_time, pingResult.times.toString())
                .setText(R.id.tv_min_time, String.format("最小:%s", pingResult.min_time))
                .setText(R.id.tv_max_time, String.format("最大:%s", pingResult.max_time))
                .setText(R.id.tv_avg_time, String.format("平均:%s", pingResult.avg_time))
                .setTextColor(R.id.tv_host, pingResult.success ? Color.parseColor("#0000ff") : Color.parseColor("#ff0000"));
    }
}
