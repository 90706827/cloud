package com.zgcenv.organization.controller;

import lombok.Data;

@Data
public class KeyLabel {
    private String key;
    private String label;

    KeyLabel(String key,String label){
        this.key = key;
        this.label = label;
    }
}
