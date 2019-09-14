package np.com.tourguide.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import np.com.tourguide.R;

public class DialogUtils {
    public static void showSuccessDialog(Context context, String titleText, String message) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_success);
        dialog.setCanceledOnTouchOutside(false);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvMessage = dialog.findViewById(R.id.tvMessage);
        Button btnOk = dialog.findViewById(R.id.btnOK);
        tvTitle.setText(titleText);
        tvMessage.setText(message);
        btnOk.setOnClickListener(v -> dialog.dismiss());
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lWindowParams);
    }

    public static void showFailedDialog(Context context, String titleText, String message) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_failed);
        dialog.setCanceledOnTouchOutside(false);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvMessage = dialog.findViewById(R.id.tvMessage);
        Button btnOk = dialog.findViewById(R.id.btnOK);
        tvTitle.setText(titleText);
        tvMessage.setText(message);
        btnOk.setOnClickListener(v -> dialog.dismiss());
        WindowManager.LayoutParams lWindowParams = new WindowManager.LayoutParams();
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lWindowParams);
    }
}
