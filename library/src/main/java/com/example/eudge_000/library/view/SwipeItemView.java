package com.example.eudge_000.library.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SwipeItemView extends RelativeLayout implements GestureDetector.OnGestureListener {

    private View mTopView;
    private View mBottomView;

    private GestureDetectorCompat mGestureDetectorCompat;

    public SwipeItemView(Context context) {
        super(context);
        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), this);
    }

    public SwipeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTopView = new View(getContext());
        mBottomView = new View(getContext());

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
        mTopView.setLayoutParams(params);
        mBottomView.setLayoutParams(params);

        ViewCompat.setBackground(mTopView, new ColorDrawable(Color.RED));
        ViewCompat.setBackground(mBottomView, new ColorDrawable(Color.GREEN));

        addView(mBottomView);
        addView(mTopView);

        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), this);
    }

    private boolean isLeftDirection;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetectorCompat.onTouchEvent(ev);

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (isLeftDirection) {
                goToLeft();
            } else goToRight();
        }

        return super.dispatchTouchEvent(ev);
    }

    private void goToRight() {
        mTopView.animate().x(getMeasuredWidth() / 2).setDuration(300).start();
    }

    private void goToLeft() {
        mTopView.animate().x(0).setDuration(300).start();
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(SwipeItemView.class.getSimpleName(), "down");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent1.getX() < (mTopView.getMeasuredWidth() / 2)) {
            mTopView.setX(motionEvent1.getX());
        }

        if(motionEvent1.getX() > (mTopView.getMeasuredWidth() / 4))
            isLeftDirection = false;
        else
            isLeftDirection = true;
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
