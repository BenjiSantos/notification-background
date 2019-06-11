package supervision.qw.gob.pe.testing.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import supervision.qw.gob.pe.testing.Bottom_navigation;
import supervision.qw.gob.pe.testing.MainActivity;
import supervision.qw.gob.pe.testing.R;

public class NewMessageNotification {
    private static final String NOTIFICATION_TAG = "NewMessage";

    public static void notify(final Context context, final String titleNotification,
                              final String partNumberNotification, final int number, final String dateEmergencie, final String addressNotification,
                              final String stateNotification, final String channelId) {
        final Resources res = context.getResources();

        //@TODO Add true image firefighters
        final Bitmap picture = BitmapFactory.decodeResource(res, R.drawable.logo);


        final String title = titleNotification;
        final String ticker = "Notificación Bomberos Perú";
        final String text = "N° Parte: " + partNumberNotification + " - " + addressNotification + " - " + stateNotification;

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_stat_new_message)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLargeIcon(picture)

                // Set ticker text (preview) information for this notification.
                .setTicker(ticker)

                //@TODO we are to use many notifications with order?
                // Show a number. This is useful when stacking notifications of
                // a single type.
                .setNumber(number)

                // Set the pending intent to be initiated when the user touches
                // the notification.
                .setContentIntent(
                        PendingIntent.getActivity(context, 0,  new Intent(context, Bottom_navigation.class), 0))

                // Show expanded text content on devices running Android 4.1 or
                // later.
                //@TODO Add true text firefighters
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text)
                        .setBigContentTitle(title)
                        .setSummaryText(dateEmergencie))

                // Automatically dismiss the notification when it is touched.
                .setAutoCancel(true);

        notify(context, builder.build());
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final Notification notification) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, 0, notification);
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification);
        }
    }

    /**
     * Cancels any notifications of this type previously shown using
     * {@link #notify(Context, String, int)}.
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0);
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode());
        }
    }

}
