/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2020, a Tencent company. All rights reserved.
 * -----------------------------------------------------------------
 * Description:
 * Author: william
 * Create: 2020/08/24
 */

package com.tencent.testrichtext.multiadapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2021, a Tencent company. All rights reserved.
 * -----------------------------------------------------------------
 * Description: 支持增量更新数据，通过subList提交数据
 * @author riccowen (1/9/21)
 */
public class MultiListAdapter extends ListAdapter<IMultiple, ViewHolder> {

    private final List<Class<? extends IMultiple>> datesCls = new ArrayList<>();
    private final List<IMultiAdapter<? extends ViewHolder, ? extends IMultiple>> holders = new ArrayList<>();
    private final List<String> types = new ArrayList<>();

    public MultiListAdapter(@NonNull DiffUtil.ItemCallback<IMultiple> diffCallback) {
        super(diffCallback);
    }

    public void addItemType(@NonNull Class<? extends IMultiple> dataCls,
            @NonNull IMultiAdapter<? extends ViewHolder, ? extends IMultiple> adapter,
            int viewType) {
        if (datesCls.contains(dataCls)) {
            throw new UnsupportedOperationException("dataCls " + dataCls + " is repeat!!");
        }
        if (types.contains(viewType + "")) {
            throw new UnsupportedOperationException("viewType " + viewType + " is repeat!!");
        }
        datesCls.add(dataCls);
        holders.add(adapter);
        types.add(viewType + "");
    }

    @Override
    public IMultiple getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int index = types.indexOf(viewType + "");
        if (index == -1) {
            throw new RuntimeException("No type with type " + viewType + ", Please check you have set it.");
        }
        IMultiAdapter<? extends ViewHolder, ? extends IMultiple> iMultiHolder = holders.get(index);
        if (iMultiHolder == null) {
            throw new RuntimeException("No holder with type " + viewType
                    + ", Please check your IMultiHolder.onCreateViewHolder() return is not null.");
        }
        return iMultiHolder.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IMultiple iMultiple = getItem(position);

        int targetIndex = datesCls.indexOf(iMultiple.getClass());
        if (targetIndex == -1) {
            throw new RuntimeException(
                    "Can't found data with holder " + holder + ", position " + position + " data " + iMultiple
                            + ", Please check your IMultiHolder.onCreateViewHolder() return is not null.");
        }
        //noinspection rawtypes
        IMultiAdapter iMultiHolder = holders.get(targetIndex);
        if (iMultiHolder == null) {
            throw new RuntimeException("No holder with holder " + holder + ", position " + position
                    + ", Please check your IMultiHolder.onCreateViewHolder() return is not null.");
        }
        //noinspection unchecked
        iMultiHolder.onBindViewHolder(holder, iMultiple);
    }

    @Override
    public int getItemViewType(int position) {
        IMultiple iMultiple = getItem(position);
        int targetIndex = datesCls.indexOf(iMultiple.getClass());
        if (targetIndex == -1) {
            throw new RuntimeException("Can't found data " + iMultiple + ", position " + position);
        }
        return Integer.parseInt(types.get(targetIndex));
    }

}
