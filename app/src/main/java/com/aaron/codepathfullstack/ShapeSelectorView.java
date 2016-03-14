package com.aaron.codepathfullstack;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Aaron on 2016/3/14.
 */
public class ShapeSelectorView extends View {
    private static final String TAG = ShapeSelectorView.class.getSimpleName();
    private static final String INSTANTSTATE = "instant_state";
    private static final String CURRENT_SHAPE_INDEX = "current_shape_index";
    private static final String CURRENT_SHAPE_COLOR = "current_shape_color";

    private String[] mShapeValues = {"Square", "Circle", "Triangle"};
    private int mCurrentShapeIndex = 0;
    private int mShapeColor;
    private boolean mDisplayShapeName;
    private int mShapeWidth = 100;
    private int mShapeHeight = 100;
    private Paint mPaint;
    private int mTextXOffset = 0;
    private int mTextYOffset = 30;

    public ShapeSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Log.d(TAG, "ShapeSelectorView constucted.");

        setupAttrs(attrs);
        setupPaint();
    }

    private void setupPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mShapeColor);
        mPaint.setTextSize(30);
    }

    private void setupAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ShapeSelectorView,
                0, 0);
        mShapeColor = a.getColor(R.styleable.ShapeSelectorView_shapeColor, Color.BLACK);
        mDisplayShapeName = a.getBoolean(R.styleable.ShapeSelectorView_displayShapeName, false);
        a.recycle();
    }

    public void setShapeColor(int shapeColor) {
        mShapeColor = shapeColor;

        mPaint.setColor(mShapeColor);
        invalidate();
        requestLayout();
    }

    public int getShapeColor() {
        return mShapeColor;
    }

    public String getSelectedShape() {
        return mShapeValues[mCurrentShapeIndex];
    }

    public void setDisplayShapeName(boolean displayShapeName) {
        mDisplayShapeName = displayShapeName;
        invalidate();
        requestLayout();
    }

    @Override
    public Display getDisplay() {
        return super.getDisplay();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure");

        int textPadding = 10;
        int contentWidth = mShapeWidth;

        int minW = contentWidth + getPaddingLeft() + getPaddingRight();
        int w = resolveSizeAndState(minW, widthMeasureSpec, 0);

        int minH = mShapeHeight + getPaddingBottom() + getPaddingTop();
        if (mDisplayShapeName) {
            minH += mTextYOffset + textPadding;
        }
        int h = resolveSizeAndState(minH, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "onDraw, current shape index: " + mCurrentShapeIndex);

        switch (mCurrentShapeIndex) {
            case 0:
                canvas.drawRect(0, 0, mShapeWidth, mShapeHeight, mPaint);
                mTextXOffset = 0;
                break;
            case 1:
                canvas.drawCircle(mShapeWidth / 2, mShapeHeight / 2, mShapeWidth / 2, mPaint);
                mTextXOffset = 15;
                break;
            case 2:
                canvas.drawPath(getTrianglePath(), mPaint);
                mTextXOffset = 0;
                break;
        }

        if (mDisplayShapeName) {
            canvas.drawText(mShapeValues[mCurrentShapeIndex], mTextXOffset, mShapeHeight + mTextYOffset,
                    mPaint);
        }
    }

    private Path getTrianglePath() {
        Point p1 = new Point(0, mShapeHeight), p2 = null, p3 = null;
        p2 = new Point(p1.x + mShapeWidth, p1.y);
        p3 = new Point(p1.x + (mShapeWidth / 2), p1.y - mShapeHeight);
        Path path = new Path();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        return path;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mCurrentShapeIndex++;
                mCurrentShapeIndex = mCurrentShapeIndex % mShapeValues.length;
                invalidate();
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_SHAPE_INDEX, mCurrentShapeIndex);
        bundle.putInt(CURRENT_SHAPE_COLOR, mShapeColor);

        bundle.putParcelable(INSTANTSTATE, super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle savedInstance = (Bundle) state;
        mCurrentShapeIndex = savedInstance.getInt(CURRENT_SHAPE_INDEX, 0);
        mShapeColor = savedInstance.getInt(CURRENT_SHAPE_COLOR);
        mPaint.setColor(mShapeColor);

        state = savedInstance.getParcelable(INSTANTSTATE);
        super.onRestoreInstanceState(state);
    }
}
