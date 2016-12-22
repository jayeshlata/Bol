package com.baapka.bol;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jayesh on 12/28/2016.
 */
public class MessageArrayAdapter extends ArrayAdapter<Message> {

    private static final int LEFT_MESSAGE = -1;
    private static final int RIGHT_MESSAGE = 1;

    private TextView messageTextView;
    private List<Message> chatMessages = new ArrayList<Message>();
    private LinearLayout wrapper;

    @Override
    public void add(Message object) {
        chatMessages.add(object);
        super.add(object);
    }

    public MessageArrayAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }

    public int getCount() {
        return this.chatMessages.size();
    }

    public Message getItem(int index) {
        return this.chatMessages.get(index);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (chatMessages.get(position).left ? LEFT_MESSAGE : RIGHT_MESSAGE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Message comment = getItem(position);

        int type = getItemViewType(position);

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(type==LEFT_MESSAGE){
                row = inflater.inflate(R.layout.chat_listitem_left, parent, false);
            }
            if(type==RIGHT_MESSAGE){
                row = inflater.inflate(R.layout.chat_listitem_right, parent, false);
            }
        }

        wrapper = (LinearLayout) row.findViewById(R.id.wrapper);

        messageTextView = (TextView) row.findViewById(R.id.text);
        messageTextView.setText(comment.text);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}