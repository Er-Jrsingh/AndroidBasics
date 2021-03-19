package com.androidbasics.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.androidbasics.R;
import com.androidbasics.data.MyDataAdapter;
import com.androidbasics.model.CityDataItem;
import com.androidbasics.sample.SampleDataProvider;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mytag";
    private List<CityDataItem> mDataList;
    private RecyclerView mRecyclerView;
    private MyDataAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataList = SampleDataProvider.cityDataItemList;
        mRecyclerView = findViewById(R.id.list_city);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        mDataAdapter = new MyDataAdapter(mDataList, this);
        mRecyclerView.setAdapter(mDataAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.app_bar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mDataAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
