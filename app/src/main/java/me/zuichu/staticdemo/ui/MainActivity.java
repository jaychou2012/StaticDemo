package me.zuichu.staticdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zuichu.staticdemo.R;
import me.zuichu.staticdemo.base.BaseActivity;
import me.zuichu.staticlib.core.StaticCore;

/**
 * 统计信息和错误日志在手机文件夹的根目录
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_text)
    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
        tv_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_text:
                StaticCore.onEvent("tv_text", "跳转点击");
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
    }
}
