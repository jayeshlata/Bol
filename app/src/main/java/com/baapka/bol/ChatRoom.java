package com.baapka.bol;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class ChatRoom extends AppCompatActivity {

    private static final String TAG = "ChatRoom";

    private static MessageArrayAdapter messageArrayAdapter;
    private ListView messageListView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        FragmentManager fragmentManager = getFragmentManager();

        if (savedInstanceState == null) {
            Log.d(TAG, "saved instance state is null");
            messageArrayAdapter = new MessageArrayAdapter(getApplicationContext());
        }
        messageListView = (ListView) findViewById(R.id.chat_listView);
        messageListView.setAdapter(messageArrayAdapter);

        editText = (EditText) findViewById(R.id.chat_editText);
        editText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String userMessage = editText.getText().toString();
                    messageArrayAdapter.add(new Message(false, userMessage));
                    editText.setText("");

                    // TODO v1: write a service to use this message and get a reply
                    // TODO v2: personalized for a user
                    String botReply = "bhak " + userMessage;
                    messageArrayAdapter.add(new Message(true, botReply));
                    messageArrayAdapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
