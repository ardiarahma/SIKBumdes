package com.ardiarahma.sik_bumdesa.networks.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Windows 10 on 10/8/2019.
 */

public class KlasifikasiAkun extends ExpandableGroup<AkunExp> {

    public KlasifikasiAkun(String title, List<AkunExp> items) {
        super(title, items);
    }
}
