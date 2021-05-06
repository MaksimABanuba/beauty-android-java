package com.banuba.sdk.example.beautification.effects.beauty;

import java.util.Map;

public interface MakeupModelDataListener {
    void onModelDataChanged(Map<String, String> values);

    void onModelDataChanged(String name, String value);
}
