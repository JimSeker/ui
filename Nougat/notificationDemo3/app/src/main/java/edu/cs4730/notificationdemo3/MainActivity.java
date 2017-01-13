package edu.cs4730.notificationdemo3;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


/**
 * while most of the work of creating the notificaitons is in the fragment, we need to create
 * three receivers that are here for the read, delete, reply intents.
 *
 * the reply receiver also updates the notification that we have dealt with he replay message as well.
 */


public class MainActivity extends AppCompatActivity {

    MyFragment myFrag;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            myFrag = new MyFragment();
            transaction.replace(R.id.container, myFrag);
            transaction.commit();
        }
    }


    protected static final String ACTION_NOTIFICATION_DELETE = "edu.cs4730notificationdemo3.delete";
    public static final String READ_ACTION = "edu.cs4730.notification3.ACTION_MESSAGE_READ";
    public static final String REPLY_ACTION = "edu.cs4730.notification3.ACTION_MESSAGE_REPLY";
    public static final String CONVERSATION_ID = "conversation_id";
    public static final String EXTRA_REMOTE_REPLY = "extra_remote_reply";

    //for updates when a notification has been deleted.
    private BroadcastReceiver mDeleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            myFrag.updateNumberOfNotifications();
        }
    };

    //for when a message has been read
    private BroadcastReceiver mReadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceiveRead");
            int conversationId = intent.getIntExtra(CONVERSATION_ID, -1);
            if (conversationId != -1) {
                myFrag.NotificationRead(conversationId);
            }
        }
    };

    //for when a message has been replied to.
    private BroadcastReceiver mReplyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceiveReply");
            int conversationId = intent.getIntExtra(CONVERSATION_ID, -1);
            if (conversationId != -1) {
                Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
                if (remoteInput != null) {
                    String replyMessage = remoteInput.getCharSequence(EXTRA_REMOTE_REPLY).toString();
                    Log.d(TAG, "Notification " + conversationId + " reply is " + replyMessage);
                    // Update the notification to stop the progress spinner.
                    NotificationManagerCompat notificationManager =
                            NotificationManagerCompat.from(context);
                    Notification repliedNotification = new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.notification_icon)
                            .setLargeIcon(BitmapFactory.decodeResource(
                                    context.getResources(), R.mipmap.android_contact))
                            .setDeleteIntent(myFrag.mDeletePendingIntent)  //se we know if they deleted it.
                            .setContentText("Replied")
                            .build();
                    notificationManager.notify(conversationId, repliedNotification);
                    myFrag.NotificationReply(conversationId, replyMessage);
                }

            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mDeleteReceiver, new IntentFilter(ACTION_NOTIFICATION_DELETE));
        registerReceiver(mReadReceiver, new IntentFilter(READ_ACTION));
        registerReceiver(mReplyReceiver, new IntentFilter(REPLY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mDeleteReceiver);
        unregisterReceiver(mReadReceiver);
        unregisterReceiver(mReplyReceiver);
    }
}
