/*
 * This file is part of the PhotoEditor Software Development Kit.
 *
 * Copyright (C) 2016 9elements GmbH <contact@9elements.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, without
 * modification, are permitted provided that the following license agreement
 * is approved and a legal/financial contract was signed by the user.
 *
 * The license agreement can be found under the following link:
 *
 * https://www.photoeditorsdk.com/LICENSE.txt
 */

package com.photoeditorsdk.android.app.stickercategories;

import android.os.Parcel;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.photoeditorsdk.android.app.R;

import java.util.ArrayList;

import ly.img.android.sdk.models.config.AbstractConfig;
import ly.img.android.sdk.models.config.DataSourceInterface;
import ly.img.android.sdk.models.config.ImageStickerConfig;

public class StickerCategory extends AbstractConfig implements DataSourceInterface<AbstractConfig.BindData> {
    private ArrayList<ImageStickerConfig> stickerConfigs = new ArrayList<>();
    private final @StringRes int categoryTitle;

    public StickerCategory(@StringRes int name, @DrawableRes int iconRes) {
        super(name, iconRes);
        this.categoryTitle = name;
    }

    public void add(ImageStickerConfig stickerConfig) {
        this.stickerConfigs.add(stickerConfig);
    }

    public ArrayList<ImageStickerConfig> getStickerConfigs() {
        return stickerConfigs;
    }

    public int getCategoryTitle() {
        return categoryTitle;
    }

    @Override
    public int getLayout() {
        return R.layout.imgly_list_item_sticker_category;
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    // Configs must be parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.stickerConfigs);
        dest.writeInt(this.categoryTitle);
    }

    protected StickerCategory(Parcel in) {
        super(in);
        this.stickerConfigs = in.createTypedArrayList(ImageStickerConfig.CREATOR);
        this.categoryTitle = in.readInt();
    }

    public static final Creator<StickerCategory> CREATOR = new Creator<StickerCategory>() {
        @Override
        public StickerCategory createFromParcel(Parcel source) {
            return new StickerCategory(source);
        }

        @Override
        public StickerCategory[] newArray(int size) {
            return new StickerCategory[size];
        }
    };
}