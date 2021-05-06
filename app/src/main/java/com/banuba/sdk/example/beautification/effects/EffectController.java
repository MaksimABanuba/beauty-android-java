package com.banuba.sdk.example.beautification.effects;

import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.banuba.sdk.example.beautification.effects.beauty.BeautyController;
import com.banuba.sdk.example.beautification.effects.beauty.MakeupController;
import com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataListener;
import com.banuba.sdk.example.beautification.effects.beauty.ModelDataListener;
import com.banuba.sdk.example.beautification.effects.color.ColorController;
import com.banuba.sdk.example.beautification.effects.color.ColorValueListener;

import java.util.HashMap;
import java.util.Map;

public class EffectController {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private Map<String, EffectValuesView> mEffectViews;

    private String current_effect = "";

    /**
     * @param selectorView      Parent view for effect selector items
     * @param valuesView        Parent view for effect value setters
     * @param modelDataListener Listener for effect values change events
     */
    public EffectController(
        RecyclerView selectorView,
        ViewGroup valuesView,
        Spinner presetsSelector,
        ModelDataListener modelDataListener,
        ColorValueListener colorValueListener,
        MakeupModelDataListener makeupModelDataListener) {
        MakeupController makeupController =
            new MakeupController(selectorView, valuesView, presetsSelector, makeupModelDataListener);

        mEffectViews = new HashMap<String, EffectValuesView>() {
            {
                put("Makeup", makeupController);
            }
        };
    }

    public void onEffectChanged(String effect) {
        if (current_effect.equals(effect)) {
            return;
        }

        EffectValuesView prev = mEffectViews.get(current_effect);
        if (prev != null) {
            prev.deactivate();
        }

        EffectValuesView current = mEffectViews.get(effect);
        if (current != null) {
            current.activate();
        }

        current_effect = effect;
    }
}
