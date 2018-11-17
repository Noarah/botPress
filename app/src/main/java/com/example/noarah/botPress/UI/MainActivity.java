package com.example.noarah.botPress.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.noarah.botPress.Model.Messages;
import com.example.noarah.botPress.R;
import com.example.noarah.botPress.Service.APIService;
import com.example.noarah.botPress.Service.RetrofitClient;
import com.example.noarah.botPress.adapters.MessageListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    private EditText editText;
    static TextView messageBody;
    private RecyclerView mMessageRecycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter MessageListAdapter;
    List<Messages> data = new ArrayList<>();;



    //init client
    Retrofit retrofit = RetrofitClient.getRetrofitClient();
    //contact with the interface
    APIService apiService = retrofit.create(APIService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.my_msg);




        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        MessageListAdapter = new MessageListAdapter(data, this);
        //messageListAdapter.notifyDataSetChanged();
        mMessageRecycler.setAdapter(MessageListAdapter);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));


        mMessageRecycler.setHasFixedSize(true);




    }

    public void clickedbtn(View view) {

        fetchMessages();

    }

    private void fetchMessages() {


        Call<List<Messages>> res = apiService.getBotResponse(editText.getText().toString());


            res.enqueue(new Callback<List<Messages>>() {
                @Override
                public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {


                    List<Messages> messages = response.body();


                    //Init the Adpater here!!
                    MessageListAdapter = new MessageListAdapter(data, MainActivity.this);
                    //SetAdapter here!!
                    mMessageRecycler.setAdapter(MessageListAdapter);



                    data.addAll(messages);
                    MessageListAdapter.notifyDataSetChanged();

                    mMessageRecycler.smoothScrollToPosition(messages.size()-1);
                    //clear box





                }

                @Override
                public void onFailure(Call<List<Messages>> call, Throwable t) {


                }
            });
        }
}

