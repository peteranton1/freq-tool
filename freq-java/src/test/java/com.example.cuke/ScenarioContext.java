package com.example.cuke;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    public enum ScenarioKeys {
        FDP_testData,
        FDP_fixedDataConfig,
        FDP_underTest,
        FDP_actual;
    }

    private Map<String, Object> internalContext;

    public ScenarioContext() {
        internalContext = new HashMap<>();
    }

    public void setScenarioValue(ScenarioKeys key, Object value) {
        internalContext.put(key.toString(), value);
    }

    public Object getScenarioValue(ScenarioKeys key) {
        return internalContext.get(key.toString());
    }

    public Boolean isContains(ScenarioKeys key) {
        return internalContext.containsKey(key.toString());
    }
}
