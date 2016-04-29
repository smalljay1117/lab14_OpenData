package com.example.android.lab14_opendata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.lab14_opendata.Observer.Observer;
import com.example.android.lab14_opendata.util.TaipeiOpenDataUtil;

public class MainActivity extends AppCompatActivity implements Observer {

    private static final String TAG = "MainActivity";
    private RecyclerView m_rv_attractions;
    private AttractionsRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        TaipeiOpenDataUtil.loadTaipeiAttractions(this);
    }

    public void init() {
        m_rv_attractions = (RecyclerView) findViewById(R.id.rv_attractions);
        m_rv_attractions.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        m_rv_attractions.setLayoutManager(llm);

        mAdapter = new AttractionsRecyclerViewAdapter();
        m_rv_attractions.setAdapter(mAdapter);
    }

    @Override
    public void OnCompleted() {
        Log.d(TAG, "OnCompleted");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnError(String message) {
        Log.d(TAG, "OnError");
        Log.d(TAG, message);
    }
}
