package com.example.noarah.botPress.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.noarah.botPress.Model.Messages;
import com.example.noarah.botPress.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;


public class MessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private List<Messages> data;
    private Context mContext;



    // constructor
    public MessageListAdapter(List<Messages> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;


        if (viewType == VIEW_TYPE_MESSAGE_SENT) {


            return new SentMessageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_message, parent, false));
        } else {


            return new RecvdMessageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_message, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);

        Messages messages = data.get(position);



        if (type == VIEW_TYPE_MESSAGE_SENT) {


            SentMessageHolder sentMessageHolder = (SentMessageHolder) holder;
            sentMessageHolder.messageBody.setText(messages.getText());


        } else {


            RecvdMessageHolder recvdMessageHolder = (RecvdMessageHolder) holder;
            recvdMessageHolder.messageBody.setText(messages.getText());
        }
    }

    public int getItemViewType(int position) {

        Messages messages = (Messages) data.get(position);


        if (messages.getRecipientId().equals("default")) {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        } else
            return VIEW_TYPE_MESSAGE_SENT;

    }

    @Override
    public int getItemCount() {


        if (data != null) {
            return data.size();

        } else return 0;
    }

    class SentMessageHolder extends RecyclerView.ViewHolder {

        public TextView messageBody;




        public SentMessageHolder(View itemView) {
            super(itemView);

            messageBody = (TextView) itemView.findViewById(R.id.text_message_body);


        }

    }

    class RecvdMessageHolder extends RecyclerView.ViewHolder {

        public TextView messageBody;

        public RecvdMessageHolder(View itemView) {
            super(itemView);

            messageBody = (TextView) itemView.findViewById(R.id.text_message_body);

        }

    }
}
