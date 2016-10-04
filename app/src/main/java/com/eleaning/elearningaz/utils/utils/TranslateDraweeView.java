package com.eleaning.elearningaz.utils.utils;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by ighotouch on 02/10/2016.
 */

public class TranslateDraweeView extends SimpleDraweeView {
    public TranslateDraweeView(Context context) {
        super(context);
    }

    public TranslateDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TranslateDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public void animateTransform(Matrix matrix){
        invalidate();
    }
}
