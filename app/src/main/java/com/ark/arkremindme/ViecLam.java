package com.ark.arkremindme;

import android.content.Context;
import android.content.SharedPreferences;

public class ViecLam {

    private static final String PREFERENCES_NAMESPACE = "checkboxes_states";

    private String TenVL;
    private int IdVL;
    boolean selected = false;
    private SharedPreferences mSettings;
    private SharedPreferences.Editor mEditor;

    public ViecLam(Context context, String tenVL, int idVL, boolean selected) {
        TenVL = tenVL;
        IdVL = idVL;
        mSettings = context.getSharedPreferences(PREFERENCES_NAMESPACE, 0);
        mEditor = mSettings.edit();
        setSelected(mSettings.getBoolean(TenVL, selected));
    }

    public String getTenVL() {
        return TenVL;
    }

    public void setTenVL(String tenVL) {
        TenVL = tenVL;
    }

    public int getIdVL() {
        return IdVL;
    }

    public void setIdVL(int idVL) {
        IdVL = idVL;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        if(this.selected != selected) {
            mEditor.putBoolean(TenVL, selected);
            mEditor.commit();
            this.selected = selected;
        }
    }
}
