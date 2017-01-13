package edu.cs4730.notificationdemo3;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    private NotificationManager mNotificationManager;
    private NotificationManagerCompat mNotificationManagerCompat;

    String TAG = "myFrag";

    private PendingIntent mDeletePendingIntent;
    private static final int REQUEST_CODE = 2323;

    TextView mNumberOfNotifications , logger;
    Button addbutton;

    int NotificationNum = 1;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateNumberOfNotifications();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //for the number of active notifications
        mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        //to send notifications and everything else.
        mNotificationManagerCompat = NotificationManagerCompat.from(getActivity().getApplicationContext());


        // Create a PendingIntent to be fired upon deletion of a Notification.
        Intent deleteIntent = new Intent(MainActivity.ACTION_NOTIFICATION_DELETE);
        mDeletePendingIntent = PendingIntent.getBroadcast(getActivity(),
                REQUEST_CODE, deleteIntent, 0);


        mNumberOfNotifications = (TextView) view.findViewById(R.id.numNoti);

        logger = (TextView) view.findViewById(R.id.logger);

        // Supply actions to the button that is displayed on screen.
        view.findViewById(R.id.addbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });
    }


    /**
     * Requests the current number of notifications from the {@link NotificationManager} and
     * display them to the user.
     */
    protected void updateNumberOfNotifications() {
        //get getting length, but getActiveNotifications() return an array of the notifications, which you can find out the info about the notifications.
        int numberOfNotifications = mNotificationManager.getActiveNotifications().length;

        mNumberOfNotifications.setText("Number of Active notifications is: " + numberOfNotifications);
        Log.i(TAG, "Number of Active notifications is: " + numberOfNotifications);
    }




    // Creates an intent that will be triggered when a message is marked as read.
    private Intent getMessageReadIntent(int id) {
        return new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction(MainActivity.READ_ACTION)
                .putExtra(MainActivity.CONVERSATION_ID, id);
    }

    // Creates an Intent that will be triggered when a voice reply is received.
    private Intent getMessageReplyIntent(int conversationId) {
        return new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction(MainActivity.REPLY_ACTION)
                .putExtra(MainActivity.CONVERSATION_ID, conversationId);
    }

    void createNotification() {
        // A pending Intent for reads
        PendingIntent readPendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(),
                NotificationNum,
                getMessageReadIntent(NotificationNum),
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Build a RemoteInput for receiving voice input in a Car Notification or text input on
        // devices that support text input (like devices on Android N and above).
        RemoteInput remoteInput = new RemoteInput.Builder(MainActivity.EXTRA_REMOTE_REPLY)
                .setLabel("Reply")
                .build();

        // Building a Pending Intent for the reply action to trigger
        PendingIntent replyIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(),
                NotificationNum,
                getMessageReplyIntent(NotificationNum),
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Build an Android N compatible Remote Input enabled action.
        NotificationCompat.Action actionReplyByRemoteInput = new NotificationCompat.Action.Builder(
                R.mipmap.notification_icon, "Reply", replyIntent)
                .addRemoteInput(remoteInput)
                .build();

        // Create the UnreadConversation and populate it with the participant name,
        // read and reply intents.
        NotificationCompat.CarExtender.UnreadConversation.Builder unreadConvBuilder =
                new NotificationCompat.CarExtender.UnreadConversation.Builder("Jim ")
                        .setLatestTimestamp(System.currentTimeMillis())
                        .setReadPendingIntent(readPendingIntent)
                        .setReplyAction(replyIntent, remoteInput);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getApplicationContext())
                .setSmallIcon(R.mipmap.notification_icon)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getActivity().getApplicationContext().getResources(), R.mipmap.android_contact))
                .setContentText("Are you working?")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Jim ")
                .setContentIntent(readPendingIntent)
                .setDeleteIntent(mDeletePendingIntent)
                .extend(new NotificationCompat.CarExtender()
                        .setUnreadConversation(unreadConvBuilder.build())
                        .setColor(getActivity().getApplicationContext().getResources()
                                .getColor(R.color.default_color_light)))
                .addAction(actionReplyByRemoteInput);

       logger.append("Sending notification " +NotificationNum + "\n");

        mNotificationManager.notify( NotificationNum, builder.build());
        NotificationNum++;
        //update the number of notifications.
        updateNumberOfNotifications();
    }


    public void NotificationRead(int id) {
        logger.append("Notification " +id + "has been read\n");
    }

    public void NotificationReply(int id, String message) {
        logger.append("Notification " +id + ": reply is "+ message+"\n");
    }
}
