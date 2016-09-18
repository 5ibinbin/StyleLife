package com.example.star.utils.timecount;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.star.R;

/**
 * Created by：Cral-Gates on 16/9/12 23:24
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/12
 * Description:
 */
public class TimeCountUtils extends CountDownTimer{

    private TextView mTextView;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCountUtils(TextView mTextView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = mTextView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false);
        mTextView.setText(millisUntilFinished/1000 + "");
        mTextView.setBackgroundResource(R.drawable.txtgraycircle);

//        SpannableString spannableString = new SpannableString(mTextView.getText().toString());
//        ForegroundColorSpan span = new ForegroundColorSpan(Color.GREEN);
//        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setClickable(true);
        mTextView.setText("重新获取验证码");
        mTextView.setBackgroundResource(R.drawable.txtgreencircle);
    }
}

