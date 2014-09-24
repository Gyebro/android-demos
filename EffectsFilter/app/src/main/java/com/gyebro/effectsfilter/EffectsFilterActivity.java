package com.gyebro.effectsfilter;

/*
 * Created by Gyebro
 * Based on https://github.com/Grishu/ImageEffects
 * For http://stackoverflow.com/questions/25763160/image-effects-with-rotation-and-pinch-to-zoom-using-glsurfaceview-android
 */

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class EffectsFilterActivity extends Activity {

    private TouchGLView mEffectView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEffectView = new TouchGLView(this);
        mEffectView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(mEffectView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reset) {
            mEffectView.resetEffect();
        }
        else {
            // Apply effect on TouchGLView, which forwards to TextureRenderer
            mEffectView.setCurrentEffect(item.getItemId());
        }
        return true;
    }

    // Extend GLSurfaceView to receive touch events.
    private class TouchGLView extends GLSurfaceView
            implements GestureDetector.OnGestureListener,
            ScaleGestureDetector.OnScaleGestureListener {
        private TextureRenderer mRenderer;
        private GestureDetector mTapDetector;
        private ScaleGestureDetector mScaleDetector;
        private float mLastSpan = 0;
        private long mLastNonTapTouchEventTimeNS = 0;

        TouchGLView(Context c) {
            super(c);
            // Use Android's built-in gesture detectors to detect
            // which touch event the user is doing.
            mTapDetector = new GestureDetector(c, this);
            mTapDetector.setIsLongpressEnabled(false);
            mScaleDetector = new ScaleGestureDetector(c, this);

            // Create an OpenGL ES 2.0 context.
            setEGLContextClientVersion(2);
            mRenderer = new TextureRenderer(c);
            setRenderer(mRenderer);
        }
        // Changes the effect of renderer
        public void setCurrentEffect(int effectID) {
            mRenderer.setCurrentEffect(effectID);
        }
        // Resets the textures to the original image
        public void resetEffect() {
            mRenderer.resetEffect();
        }
        @Override
        public boolean onTouchEvent(final MotionEvent e) {
            // Forward touch events to the gesture detectors.
            mScaleDetector.onTouchEvent(e);
            mTapDetector.onTouchEvent(e);
            return true;
        }
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                final float dx, final float dy) {
            // Forward the drag event to the renderer.
            queueEvent(new Runnable() {
                public void run() {
                    // This Runnable will be executed on the render
                    // thread.
                    // In a real app, you'd want to divide these by
                    // the display resolution first.
                    mRenderer.drag(dx, dy);
                }});
            mLastNonTapTouchEventTimeNS = System.nanoTime();
            return true;
        }
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // Forward the scale event to the renderer.
            final float amount = detector.getCurrentSpan() - mLastSpan;
            queueEvent(new Runnable() {
                public void run() {
                    // This Runnable will be executed on the render
                    // thread.
                    // In a real app, you'd want to divide this by
                    // the display resolution first.
                    mRenderer.zoom(amount);
                }});
            mLastSpan = detector.getCurrentSpan();
            mLastNonTapTouchEventTimeNS = System.nanoTime();
            return true;
        }
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            mLastSpan = detector.getCurrentSpan();
            return true;
        }
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) { }
        @Override
        public boolean onDown(MotionEvent e) { return false; }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy) { return false; }
        @Override
        public void onLongPress(MotionEvent e) {  }
        @Override
        public void onShowPress(MotionEvent e) {  }
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }
    }
}