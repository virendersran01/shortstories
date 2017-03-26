package com.nickrout.shortcuts.ui.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.nickrout.shortcuts.BR;

public class DataBindingViewHolder extends RecyclerView.ViewHolder {

    private final ViewDataBinding mBinding;

    public DataBindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bind(Object obj) {
        mBinding.setVariable(BR.obj, obj);
        mBinding.executePendingBindings();
    }
}