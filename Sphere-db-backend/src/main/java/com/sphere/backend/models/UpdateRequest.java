package com.sphere.backend.models;

import java.util.Map;

public class UpdateRequest {
    private Map<String, String> updates;
    private String condition;
    private String db;

    public Map<String, String> getUpdates() {
        return updates;
    }

    public String getCondition() {
        return condition;
    }

    public void setUpdates(Map<String, String> updates) {
        this.updates = updates;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDb() {
        return db;
    }


}
