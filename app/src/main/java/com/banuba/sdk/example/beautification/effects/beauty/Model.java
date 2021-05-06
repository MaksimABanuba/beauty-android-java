package com.banuba.sdk.example.beautification.effects.beauty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class MakeupModelDataKeys {
    static final String MORPH_FACE = "FaceMorph.face";
    static final String MORPH_EYES = "FaceMorph.eyes";
    static final String MORPH_NOSE = "FaceMorph.nose";

    static final String SKIN_SOFTENING = "SkinSoftening.strength";
    static final String SOFTLIGHT_STRENGTH = "Softlight.strength";
    static final String SKIN_COLOR = "Skin.color";

    static final String EYES_FLARE = "EyesFlare.strength";
    static final String EYES_WHITENING = "EyesWhitening.strength";
    static final String EYES_COLOR = "Eyes.color";

    static final String EYELINER_COLOR = "Eyeliner.color";
    static final String EYELINER_TEX = "Eyeliner.set";

    static final String EYESHADOW_COLOR = "Eyeshadow.color";
    static final String EYESHADOW_TEX = "Eyeshadow.set";

    static final String EYELASHES_COLOR = "Eyelashes.color";
    static final String EYELASHES_TEX = "Eyelashes.set";

    static final String EYEBROWS_COLOR = "Eyebrows.color";
    static final String EYEBROWS_TEX = "Eyebrows.set";

    static final String TEETH_WHITENING = "TeethWhitening.strength";

    static final String BLUSH_COLOR = "Blush.color";
    static final String BLUSH_TEX = "Blush.set";

    static final String HAIR_COLOR = "Hair.color";

    static final String LIPS_MATT_COLOR = "Lips.matt";
    static final String LIPS_SHINY_COLOR = "Lips.shiny";
    static final String LIPS_GLITTER_COLOR = "Lips.glitter";

    static final String HIGHLIGHTER_COLOR = "Highlighter.color";
    static final String HIGHLIGHTER_TEX = "Highlighter.set";

    static final String CONTOUR_COLOR = "Contour.color";
    static final String CONTOUR_TEX = "Contour.set";

    static final String FOUNDATION_COLOR = "Foundation.color";
    static final String FOUNDATION_STRENGTH = "Foundation.strength";
}

final class Model {
    private Map<String, List<ValueSetter>> mModel;

    Model(ValueSetterListener valueListener) {
        mModel = new HashMap<String, List<ValueSetter>>() {
            {
                put("Morph", new ArrayList<ValueSetter>() {
                    {
                        add(new SeekBarValueSetter(
                            "Face", MakeupModelDataKeys.MORPH_FACE, valueListener, 1));
                        add(new SeekBarValueSetter(
                            "Eyes", MakeupModelDataKeys.MORPH_EYES, valueListener, 1));
                        add(new SeekBarValueSetter(
                            "Nose", MakeupModelDataKeys.MORPH_NOSE, valueListener, 1));
                    }
                });
                put("Skin", new ArrayList<ValueSetter>() {
                    {
                        add(new SeekBarValueSetter(
                            "Softening", MakeupModelDataKeys.SKIN_SOFTENING, valueListener, 1));
                        add(new SeekBarValueSetter(
                            "Softlight", MakeupModelDataKeys.SOFTLIGHT_STRENGTH, valueListener, 1));
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.SKIN_COLOR, valueListener));
                    }
                });
                put("Eyes", new ArrayList<ValueSetter>() {
                    {
                        add(new SeekBarValueSetter(
                            "Flare", MakeupModelDataKeys.EYES_FLARE, valueListener, 1));
                        add(new SeekBarValueSetter(
                            "Whitening", MakeupModelDataKeys.EYES_WHITENING, valueListener, 1));
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.EYES_COLOR, valueListener));
                    }
                });
                put("Teeth", new ArrayList<ValueSetter>() {
                    {
                        add(new SeekBarValueSetter(
                            "Whitening", MakeupModelDataKeys.TEETH_WHITENING, valueListener, 1));
                    }
                });
                put("Eyeliner", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.EYELINER_COLOR, valueListener));
                        add(new MakeupSpinnerValueSetter(
                            "Texture", MakeupModelDataKeys.EYELINER_TEX, new ArrayList<String>() {
                            {
                                add("eyeliner.png");
                            }
                        }, valueListener));
                    }
                });
                put("Eyeshadow", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.EYESHADOW_COLOR, valueListener));
                        add(new MakeupSpinnerValueSetter(
                            "Texture", MakeupModelDataKeys.EYESHADOW_TEX, new ArrayList<String>() {
                            {
                                add("eyeshadow.png");
                            }
                        }, valueListener));
                    }
                });
                put("Eyelashes", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.EYELASHES_COLOR, valueListener));
                        add(new MakeupSpinnerValueSetter(
                            "Texture", MakeupModelDataKeys.EYELASHES_TEX, new ArrayList<String>() {
                            {
                                add("eyelashes.png");
                            }
                        }, valueListener));
                    }
                });
                put("Eyebrows", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.EYEBROWS_COLOR, valueListener));
                        add(new MakeupSpinnerValueSetter(
                            "Texture", MakeupModelDataKeys.EYEBROWS_TEX, new ArrayList<String>() {
                                {
                                    add("eyebrows.png");
                                }
                            }, valueListener));
                    }
                });
                put("Blush", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.BLUSH_COLOR, valueListener));
                        add(new MakeupSpinnerValueSetter(
                            "Texture", MakeupModelDataKeys.BLUSH_TEX, new ArrayList<String>() {
                                {
                                    add("blushes.png");
                                }
                            }, valueListener
                        ));
                    }
                });
                put("Hair", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.HAIR_COLOR, valueListener));
                    }
                });
                put("Lips matt", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.LIPS_MATT_COLOR, valueListener));
                    }
                });
                put("Lips shiny", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.LIPS_SHINY_COLOR, valueListener));
                    }
                });
                put("Lips glitter", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.LIPS_GLITTER_COLOR, valueListener));
                    }
                });
                put("Highlighting", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.HIGHLIGHTER_COLOR, valueListener));
                        add(new MakeupSpinnerValueSetter(
                            "Texture", MakeupModelDataKeys.HIGHLIGHTER_TEX, new ArrayList<String>() {
                            {
                                add("highlighter.png");
                            }
                        }, valueListener
                        ));
                    }
                });
                put("Contouring", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.CONTOUR_COLOR, valueListener));
                        add(new MakeupSpinnerValueSetter(
                            "Texture", MakeupModelDataKeys.CONTOUR_TEX, new ArrayList<String>() {
                            {
                                add("contour.png");
                            }
                        }, valueListener
                        ));
                    }
                });
                put("Foundation", new ArrayList<ValueSetter>() {
                    {
                        add(new ColorSeekBarValueSetter(
                            "Coloring", MakeupModelDataKeys.FOUNDATION_COLOR, valueListener));
                        add(new SeekBarValueSetter(
                            "Strength", MakeupModelDataKeys.FOUNDATION_STRENGTH, valueListener, 1));
                    }
                });
            }
        };
    }

    /**
     * @param displayName Group name
     * @return Requested group items to use for effect value setters
     */
    List<ValueSetter> getGroup(String displayName) {
        return mModel.get(displayName);
    }

    /**
     * @return Effect names to show in selector
     */
    List<String> getGroupNames() {
        List<String> ret = new ArrayList<>(mModel.keySet());
        Collections.sort(ret);
        return ret;
    }
}
