package com.example.usuario.manageproductsdb.presenter;

import android.util.SparseBooleanArray;

/**
 * Created by usuario on 16/12/16.
 */

public class MultiChoicePresenter {
    ProductPresenterImpl presenter;
    SparseBooleanArray sparseBoolean;

    public MultiChoicePresenter(ProductPresenterImpl presenter) {
        this.presenter = presenter;
        sparseBoolean = new SparseBooleanArray();
    }

    public boolean isPositionChecked(int position) {
        Boolean result = sparseBoolean.get(position);
        return result;
    }
}
