package com.luoyang.llreader.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.luoyang.llreader.R;
import com.luoyang.llreader.base.MBaseActivity;
import com.luoyang.mvprxjava.IPresenter;

import java.util.Random;

public class WelcomeActivity extends MBaseActivity {

    private ImageView ivBg;
    private ImageView ivIcon;
    private TextView tvIntro;
    private ValueAnimator welAnimator;
    private int[] mArray;


    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initData() {
        //ValueAnimator就是一个数值发生器，它可以产生你想要的各种数值,其实在Android属性动画中，如何产生每一步具体实现动画效果，都是通过ValueAnimator计算出来的。
        welAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(1000);
        welAnimator.setStartDelay(1000);

    }

    @Override
    protected void bindView() {
        ivBg = findViewById(R.id.iv_bg);
        ivIcon =  findViewById(R.id.iv_icon);
        tvIntro = findViewById(R.id.tv_intro);

        int[] mArray = {
                R.drawable.bg_welcome1,
                R.drawable.bg_welcome,
                R.drawable.bg_welcome2,
                R.drawable.bg_welcome3};

        Random random = new Random();
        //nextInt(5) 是[0,5) 0,1,2,3,4五个整数
        int index = random.nextInt(mArray.length);

        //直接资源set随机图片
        ivBg.setImageResource(mArray[index]);

        //实例化Drawable类，随机设置图片
        // Drawable drawable =getContext().getResources().getDrawable(mArray[index]);
        // ivBg.setImageDrawable(drawable);
    }

    @Override
    protected void bindEvent() {
        welAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //alpha 从1f渐变为0f
                float alpha = (Float) animation.getAnimatedValue();
                ivBg.setAlpha(alpha);
                ivIcon.setAlpha(alpha);
                tvIntro.setAlpha(1f - alpha);
            }
        });

        welAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivityByAnim(new Intent(WelcomeActivity.this, MainActivity.class), android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void firstRequest() {
        welAnimator.start();
    }
}
