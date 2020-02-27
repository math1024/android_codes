package com.android.lyc.ui.navi

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.lyc.R
import kotlinx.android.synthetic.main.deeplink_fragment.*

/**
 * @author rosetta
 * @date 2020/02/27
 */
class NavigationDeepLinkFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.deeplink_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        deep_link_text.text = arguments?.getString("myarg")
        deep_link_send_notification_button.setOnClickListener {
            val args = Bundle()
            args.putString("myarg", deep_link_args_edit_text.text.toString())


            val deepLink = findNavController().createDeepLink()
                .setDestination(R.id.deeplink_dest)
                .setArguments(args)
                .createPendingIntent()

            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        "deeplink",
                        "Deep Links",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
            }

            val builder = NotificationCompat.Builder(
                context!!, "deeplink"
            )
                .setContentTitle("Navigation")
                .setContentText("Deep link to Android")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentIntent(deepLink)
                .setAutoCancel(true)
            notificationManager.notify(0, builder.build())
        }
    }
}