package com.banuba.sdk.example.beautification.effects.beauty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.banuba.sdk.example.beautification.R;
import com.banuba.sdk.example.beautification.effects.EffectValuesView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.BLUSH_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.BLUSH_TEX;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.CONTOUR_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.CONTOUR_TEX;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYEBROWS_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYEBROWS_TEX;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYELASHES_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYELASHES_TEX;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYELINER_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYELINER_TEX;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYESHADOW_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYESHADOW_TEX;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYES_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYES_FLARE;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.EYES_WHITENING;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.FOUNDATION_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.FOUNDATION_STRENGTH;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.HAIR_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.HIGHLIGHTER_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.HIGHLIGHTER_TEX;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.LIPS_GLITTER_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.LIPS_MATT_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.LIPS_SHINY_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.MORPH_EYES;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.MORPH_FACE;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.MORPH_NOSE;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.SKIN_COLOR;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.SKIN_SOFTENING;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.SOFTLIGHT_STRENGTH;
import static com.banuba.sdk.example.beautification.effects.beauty.MakeupModelDataKeys.TEETH_WHITENING;

public class MakeupController implements ValueSetterListener, VSListSelectorListener,
                                         EffectValuesView, AdapterView.OnItemSelectedListener {

    private final static String CUSTOM_OPTION = "Custom";
    private final static Map<String, String> CLEAR_DATA;

    static {
        Function<String, String> getClearMethod = (method) -> {
            if (method.contains(".")) {
                return method.substring(0, method.indexOf(".")) + ".clear";
            }
            return "";
        };

        CLEAR_DATA = new HashMap<>();
        final List<String> methods = Arrays.asList(
                MORPH_FACE, MORPH_EYES, MORPH_NOSE,
                SKIN_SOFTENING, SOFTLIGHT_STRENGTH, SKIN_COLOR,
                EYES_FLARE, EYES_WHITENING, EYES_COLOR,
                EYELINER_COLOR, EYELINER_TEX,
                EYESHADOW_COLOR, EYESHADOW_TEX,
                EYELASHES_COLOR, EYELASHES_TEX,
                EYEBROWS_COLOR, EYEBROWS_TEX,
                TEETH_WHITENING,
                BLUSH_COLOR, BLUSH_TEX,
                HAIR_COLOR,
                LIPS_MATT_COLOR, LIPS_SHINY_COLOR, LIPS_GLITTER_COLOR,
                HIGHLIGHTER_COLOR, HIGHLIGHTER_TEX,
                CONTOUR_COLOR, CONTOUR_TEX,
                FOUNDATION_COLOR, FOUNDATION_STRENGTH);
        for (String method : methods) {
            CLEAR_DATA.put(getClearMethod.apply(method), "");
        }
    }

    private final Map<String, String> mModelData = new HashMap<String, String>() {
        {
            put(MORPH_FACE, "0.0");
            put(MORPH_EYES, "0.0");
            put(MORPH_NOSE, "0.0");

            put(SKIN_SOFTENING, "0.0");
            put(SOFTLIGHT_STRENGTH, "0.0");
            put(SKIN_COLOR, "0.0 0.0 0.0 0.0");

            put(EYES_FLARE, "0.0");
            put(EYES_WHITENING, "0.0");
            put(EYES_COLOR, "0.0 0.0 0.0 0.0");

            put(EYELINER_COLOR, "0.0 0.0 0.0 0.0");
            put(EYELINER_TEX, "eyeliner.png");

            put(EYESHADOW_COLOR, "0.0 0.0 0.0 0.0");
            put(EYESHADOW_TEX, "eyeshadow.png");

            put(EYELASHES_COLOR, "0.0 0.0 0.0 0.0");
            put(EYELASHES_TEX, "eyelashes.png");

            put(EYEBROWS_COLOR, "0.0 0.0 0.0 0.0");
            put(EYEBROWS_TEX, "eyebrows.png");

            put(TEETH_WHITENING, "0.0");

            put(BLUSH_COLOR, "0.0 0.0 0.0 0.0");
            put(BLUSH_TEX, "blushes.png");

            put(HAIR_COLOR, "0.0 0.0 0.0 0.0");

            put(LIPS_MATT_COLOR, "0.0 0.0 0.0 0.0");
            put(LIPS_SHINY_COLOR, "0.0 0.0 0.0 0.0");
            put(LIPS_GLITTER_COLOR, "0.0 0.0 0.0 0.0");

            put(HIGHLIGHTER_COLOR, "0.0 0.0 0.0 0.0");
            put(HIGHLIGHTER_TEX, "highlighter.png");

            put(CONTOUR_COLOR, "0.0 0.0 0.0 0.0");
            put(CONTOUR_TEX, "contour.png");

            put(FOUNDATION_COLOR, "0.0 0.0 0.0 0.0");
            put(FOUNDATION_STRENGTH, "0.0");
        }
    };

    private final Map<String, Map<String, String>> mPresets =
        new LinkedHashMap<String, Map<String, String>>() {
            {
                put(CUSTOM_OPTION, null);
                put("Disabled", new HashMap<String, String>() {
                    {
                        put(MORPH_FACE, "0.0");
                        put(MORPH_EYES, "0.0");
                        put(MORPH_NOSE, "0.0");

                        put(SKIN_SOFTENING, "0.0");
                        put(SOFTLIGHT_STRENGTH, "0.0");
                        put(SKIN_COLOR, "0.0 0.0 0.0 0.0");

                        put(EYES_FLARE, "0.0");
                        put(EYES_WHITENING, "0.0");
                        put(EYES_COLOR, "0.0 0.0 0.0 0.0");

                        put(EYELINER_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYELINER_TEX, "eyeliner.png");

                        put(EYESHADOW_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYESHADOW_TEX, "eyeshadow.png");

                        put(EYELASHES_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYELASHES_TEX, "eyelashes.png");

                        put(EYEBROWS_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYEBROWS_TEX, "eyebrows.png");

                        put(TEETH_WHITENING, "0.0");

                        put(BLUSH_COLOR, "0.0 0.0 0.0 0.0");
                        put(BLUSH_TEX, "blushes.png");

                        put(HAIR_COLOR, "0.0 0.0 0.0 0.0");

                        put(LIPS_MATT_COLOR, "0.0 0.0 0.0 0.0");
                        put(LIPS_SHINY_COLOR, "0.0 0.0 0.0 0.0");
                        put(LIPS_GLITTER_COLOR, "0.0 0.0 0.0 0.0");

                        put(HIGHLIGHTER_COLOR, "0.0 0.0 0.0 0.0");
                        put(HIGHLIGHTER_TEX, "highlighter.png");

                        put(CONTOUR_COLOR, "0.0 0.0 0.0 0.0");
                        put(CONTOUR_TEX, "contour.png");

                        put(FOUNDATION_COLOR, "0.0 0.0 0.0 0.0");
                        put(FOUNDATION_STRENGTH, "0.0");
                    }
                });
                put("Lesser", new HashMap<String, String>() {
                    {
                        put(MORPH_FACE, "0.33");
                        put(MORPH_EYES, "0.33");
                        put(MORPH_NOSE, "0.33");

                        put(SKIN_SOFTENING, "0.33");
                        put(SOFTLIGHT_STRENGTH, "0.0");
                        put(SKIN_COLOR, "0.0 0.0 0.0 0.0");

                        put(EYES_FLARE, "0.33");
                        put(EYES_WHITENING, "0.33");
                        put(EYES_COLOR, "0.0 0.0 0.0 0.0");

                        put(EYELINER_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYELINER_TEX, "eyeliner.png");

                        put(EYESHADOW_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYESHADOW_TEX, "eyeshadow.png");

                        put(EYELASHES_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYELASHES_TEX, "eyelashes.png");

                        put(EYEBROWS_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYEBROWS_TEX, "eyebrows.png");

                        put(TEETH_WHITENING, "0.0");

                        put(BLUSH_COLOR, "0.0 0.0 0.0 0.0");
                        put(BLUSH_TEX, "blushes.png");

                        put(HAIR_COLOR, "0.0 0.0 0.0 0.0");

                        put(LIPS_MATT_COLOR, "0.0 0.0 0.0 0.0");
                        put(LIPS_SHINY_COLOR, "0.0 0.0 0.0 0.0");
                        put(LIPS_GLITTER_COLOR, "0.0 0.0 0.0 0.0");

                        put(HIGHLIGHTER_COLOR, "0.0 0.0 0.0 0.0");
                        put(HIGHLIGHTER_TEX, "highlighter.png");

                        put(CONTOUR_COLOR, "0.0 0.0 0.0 0.0");
                        put(CONTOUR_TEX, "contour.png");

                        put(FOUNDATION_COLOR, "0.0 0.0 0.0 0.0");
                        put(FOUNDATION_STRENGTH, "0.0");
                    }
                });
                put("Medium", new HashMap<String, String>() {
                    {
                        put(MORPH_FACE, "0.66");
                        put(MORPH_EYES, "0.66");
                        put(MORPH_NOSE, "0.66");

                        put(SKIN_SOFTENING, "0.66");
                        put(SOFTLIGHT_STRENGTH, "0.0");
                        put(SKIN_COLOR, "0.0 0.0 0.0 0.0");

                        put(EYES_FLARE, "0.66");
                        put(EYES_WHITENING, "0.66");
                        put(EYES_COLOR, "0.0 0.0 0.0 0.0");

                        put(EYELINER_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYELINER_TEX, "eyeliner.png");

                        put(EYESHADOW_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYESHADOW_TEX, "eyeshadow.png");

                        put(EYELASHES_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYELASHES_TEX, "eyelashes.png");

                        put(EYEBROWS_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYEBROWS_TEX, "eyebrows.png");

                        put(TEETH_WHITENING, "0.0");

                        put(BLUSH_COLOR, "0.0 0.0 0.0 0.0");
                        put(BLUSH_TEX, "blushes.png");

                        put(HAIR_COLOR, "0.0 0.0 0.0 0.0");

                        put(LIPS_MATT_COLOR, "0.0 0.0 0.0 0.0");
                        put(LIPS_SHINY_COLOR, "0.0 0.0 0.0 0.0");
                        put(LIPS_GLITTER_COLOR, "0.0 0.0 0.0 0.0");

                        put(HIGHLIGHTER_COLOR, "0.0 0.0 0.0 0.0");
                        put(HIGHLIGHTER_TEX, "highlighter.png");

                        put(CONTOUR_COLOR, "0.0 0.0 0.0 0.0");
                        put(CONTOUR_TEX, "contour.png");

                        put(FOUNDATION_COLOR, "0.0 0.0 0.0 0.0");
                        put(FOUNDATION_STRENGTH, "0.0");
                    }
                });
                put("Extreme", new HashMap<String, String>() {
                    {
                        put(MORPH_FACE, "1.0");
                        put(MORPH_EYES, "1.0");
                        put(MORPH_NOSE, "1.0");

                        put(SKIN_SOFTENING, "1.0");
                        put(SOFTLIGHT_STRENGTH, "0.0");
                        put(SKIN_COLOR, "0.0 0.0 0.0 0.0");

                        put(EYES_FLARE, "1.0");
                        put(EYES_WHITENING, "1.0");
                        put(EYES_COLOR, "0.0 0.0 0.0 0.0");

                        put(EYELINER_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYELINER_TEX, "eyeliner.png");

                        put(EYESHADOW_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYESHADOW_TEX, "eyeshadow.png");

                        put(EYELASHES_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYELASHES_TEX, "eyelashes.png");

                        put(EYEBROWS_COLOR, "0.0 0.0 0.0 0.0");
                        put(EYEBROWS_TEX, "eyebrows.png");

                        put(TEETH_WHITENING, "0.0");

                        put(HAIR_COLOR, "0.0 0.0 0.0 0.0");

                        put(LIPS_MATT_COLOR, "0.0 0.0 0.0 0.0");
                        put(LIPS_SHINY_COLOR, "0.0 0.0 0.0 0.0");
                        put(LIPS_GLITTER_COLOR, "0.0 0.0 0.0 0.0");

                        put(HIGHLIGHTER_COLOR, "0.0 0.0 0.0 0.0");
                        put(HIGHLIGHTER_TEX, "highlighter.png");

                        put(CONTOUR_COLOR, "0.0 0.0 0.0 0.0");
                        put(CONTOUR_TEX, "contour.png");

                        put(FOUNDATION_COLOR, "0.0 0.0 0.0 0.0");
                        put(FOUNDATION_STRENGTH, "0.0");
                    }
                });
            }
        };

    // UI components model
    private final Model mModel = new Model(this);

    // Parent view for selected effect ui components
    private ViewGroup mValuesParentView;

    // Listener which calls js methods
    private MakeupModelDataListener mModelDataListener;

    private List<ValueSetter> mActiveGroup;
    private VSGroupSelector mGroupSelector;
    private Spinner mPresetsSelector;

    public MakeupController(
        RecyclerView VSGroupSelectorView,
        ViewGroup valuesView,
        Spinner presetsSelector,
        MakeupModelDataListener modelDataListener) {
        mGroupSelector = new VSGroupSelector(this, VSGroupSelectorView);
        mValuesParentView = valuesView;
        mModelDataListener = modelDataListener;
        mPresetsSelector = presetsSelector;

        ArrayAdapter<String> presets_selector_adapter = new ArrayAdapter<>(
                presetsSelector.getContext(),
                R.layout.effect_spinner_dropdown_item,
                new ArrayList<>(mPresets.keySet()));
        presetsSelector.setAdapter(presets_selector_adapter);
        presetsSelector.setOnItemSelectedListener(this);
    }

    /**
     * Used to emit model data changed event on any effect value change
     * @param name  Changed effect parameter name
     * @param value Changed effect value
     */
    @Override
    public void onSetterValueChanged(String name, String value) {
        String v = mModelData.get(name);
        if (v == null) {
            throw new RuntimeException("Wrong value assign attempt: " + name);
        }
        if (v.equals(value)) {
            return;
        }
        mModelData.put(name, value);
        emitValueUpdate(name, value);
        // emitValuesUpdate();
    }

    /**
     * Used to reinitialize current effect group UI setters on selected group changed event
     * @param name New selected group name
     */
    @Override
    public void onVSListSelectorValueChanged(String name) {
        mValuesParentView.removeAllViews();
        resetActiveSetters(mModel.getGroup(name));

        LayoutInflater inflater = LayoutInflater.from(mValuesParentView.getContext());
        for (ValueSetter item : mActiveGroup) {
            TextView tv =
                    (TextView) inflater.inflate(R.layout.vsetter_title, mValuesParentView, false);
            tv.setText(item.getDisplayName());
            mValuesParentView.addView(tv);

            View view;
            if (item instanceof SeekBarValueSetter) {
                view = inflater.inflate(R.layout.vsetter_seekbar, mValuesParentView, false);
            } else if (item instanceof MakeupSpinnerValueSetter) {
                view = inflater.inflate(R.layout.vsetter_resource_selector, mValuesParentView, false);
            } else if (item instanceof SwitchValueSetter) {
                view = inflater.inflate(R.layout.vsetter_switch, mValuesParentView, false);
            } else if (item instanceof ColorSeekBarValueSetter) {
                view = inflater.inflate(R.layout.color_selector, mValuesParentView, false);
            } else {
                throw new RuntimeException("Unexpected type: " + item.getClass().getName());
            }

            View setter = view.findViewById(R.id.value_setter);
            String value = mModelData.get(item.getParameterName());
            if (value == null) {
                throw new RuntimeException("No value for key: " + item.getParameterName());
            }

            item.setView(setter);
            item.setValue(value);

            mValuesParentView.addView(view);
        }
    }

    private void resetActiveSetters(List<ValueSetter> newSetters) {
        if (mActiveGroup != null) {
            for (ValueSetter item : mActiveGroup) {
                item.setView(null);
            }
        }
        mActiveGroup = newSetters;
    }

    /**
     * Show beautification UI and call effect update
     */
    @Override
    public void activate() {
        mPresetsSelector.setVisibility(View.VISIBLE);
        mPresetsSelector.setSelection(0);
        mGroupSelector.populate(mModel.getGroupNames());
        emitValuesUpdate();
    }

    /**
     * Hide beautification UI
     */
    @Override
    public void deactivate() {
        mPresetsSelector.setVisibility(View.GONE);
        resetActiveSetters(null);
        mGroupSelector.clear();
        mValuesParentView.removeAllViews();
    }

    /**
     * Send data to effect
     */
    private void emitValuesUpdate() {
        mModelDataListener.onModelDataChanged(CLEAR_DATA);
        mModelDataListener.onModelDataChanged(mModelData);
    }

    /**
     * Send data to effect
     */
    private void emitValueUpdate(String name, String value) {
        mModelDataListener.onModelDataChanged(name, value);
    }

    /**
     * On preset selected
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String preset = (String) ((TextView) view).getText();

        if (preset.equals(CUSTOM_OPTION)) {
            mGroupSelector.populate(mModel.getGroupNames());
            return;
        }

        resetActiveSetters(null);
        mGroupSelector.clear();
        mValuesParentView.removeAllViews();

        mModelData.putAll(Objects.requireNonNull(mPresets.get(preset)));
        emitValuesUpdate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
