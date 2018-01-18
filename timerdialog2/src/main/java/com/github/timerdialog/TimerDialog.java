package  mehdi.timerdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mehdi Haghgoo on 1/18/18.
 */

/**
 * My custom alert dialog class that is very simple and easy to work with
 */
public class TimerDialog extends AlertDialog {

    private String mMessage = "message";
    private AlertDialog.Builder mBuilder;
    private Context mContext;
    private AlertDialog mDialog;
    /**
     * This is the {@link TimerTask} object that will have the task code
     */
    private TimerTask mTask;
    private TextView mTimeLabel;
    /**
     * Timeout for the dialog. This can be set via {@code setTimeout(int)}
     */
    private static int mTimeout = 10;
    /**
     * Timer object used across the class
     */
    private Timer mTimer;

    public TimerDialog(Context context, String message) {
        super(context);
        mContext = context;
        mMessage = message;
        init();
    }
    /**
     *
     * @param context Context used to create the dialog
     * @param message Message string resource
     * @param timeout timeout that will override the default timeout
     */
    public TimerDialog(Context context,@StringRes  int message, int timeout) {
        super(context);
        mContext = context;
        mMessage = mContext.getString(message);
        mTimeout = timeout;
        init();
    }

    /**
     *
     * @param context Context used to create the dialog
     * @param message Message string
     * @param timeout timeout that will override the default timeout
     */
    public TimerDialog(Context context, String message, int timeout) {
        super(context);
        mContext = context;
        mMessage = message;
        init();
    }

    private void init() {
        // Init the label that will be used as inner view
//        mBuilder = new AlertDialog.Builder(mContext, android.R.style.Widget_Material_ButtonBar_AlertDialog)
//                .setMessage(mMessage)
//                .setIcon(R.drawable.zood_icon)
//                .setTitle(R.string.message);
//        mDialog = mBuilder.create();
//        addContentView(mTimeLabel, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ViewGroup content = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.custom_dialog, null);
        this.setView(content);
        mTimeLabel = (TextView) LayoutInflater.from(mContext).inflate(R.layout.counter_view, null);
        setMessage(mMessage);
        setTitle(R.string.message);
        addContentView(mTimeLabel, null);


        //Init timer
        mTimer = new Timer();

        //Init the task
        mTask = new TimerTask() {
            @Override
            public void run() {
                //Cancel if the countdown is finished
                if(mTimeout <= 0) {
                    //Cancel the timer and dismiss the dialog
                    cancel();
                    dismiss();
                }
                else {
                    mTimeLabel.setText(String.valueOf(mTimeout--));
                }
            }
        };


    }



    @Override
    public void show() {
        super.show();
        //Here the timer should start counting down
        mTimer.schedule(mTask,1000, 1000);
    }


}
