package vn.lucifer.demothi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private int pages=1;
    private RecyclerView recyclerView;
    private List<Datum> datumList;
    private ViewAdapter viewAdapter;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rvView);

        setLayoutManager();
        Get(pages);

    }
    private void Get (int pages){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        retrofitService.getData(pages).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                datumList=new ArrayList<>();
                datumList.addAll(response.body().getData());
                Toast.makeText(MainActivity.this,datumList.size(),Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                // trả về lỗi
                Log.e("A", t.getMessage());
            }
        });
    }
    void setLayoutManager() {

        viewAdapter = new ViewAdapter(MainActivity.this, datumList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(viewAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        //staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}