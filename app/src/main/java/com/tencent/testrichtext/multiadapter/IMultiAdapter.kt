/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2020, a Tencent company. All rights reserved.
 * -----------------------------------------------------------------
 * Description:
 * Author: william
 * Create: 2020/08/24
 */
package com.tencent.testrichtext.multiadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface IMultiAdapter<T : RecyclerView.ViewHolder?, R : IMultiple?> {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T
    fun onBindViewHolder(holder: T, data: R)
}