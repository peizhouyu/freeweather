package com.free.freeweather.util;

import android.app.Activity;
import android.content.Context;

/**
 * Created by 裴周宇 on 2017/2/15.
 */

public class MyProgressDialog {
    /**
     * 显示进度对话框
     */
    public static void showProgressDialog(Context context, CharSequence message, android.app.ProgressDialog dialogName) {
        if (dialogName == null) {
            dialogName = new android.app.ProgressDialog(context);
            dialogName.setMessage(message);
            dialogName.setCanceledOnTouchOutside(false);
        }
        dialogName.show();
    }


    /**
     * 关闭进度对话框
     */
    public static void closeProgressDialog(android.app.ProgressDialog dialogName) {
        if (dialogName != null) {
            dialogName.dismiss();
        }
    }
}
