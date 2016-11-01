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

import ly.img.android.sdk.models.state.EditorMenuState;
import ly.img.android.sdk.tools.AbstractEditorTool;

public class StickerCategoriesEditorTool extends AbstractEditorTool {

    private EditorMenuState menuState = null;

    public StickerCategoriesEditorTool(@StringRes int name, @DrawableRes int drawableId) {
        super(name, drawableId, StickerCategoriesToolPanel.class);
    }

    public void openStickerCategory(StickerCategory stickerCategory) {

        if (menuState == null) {
            menuState = getStateHandler().getStateModel(EditorMenuState.class);
        }

        menuState.openSubTool(new StickerCategoryEditorTool(stickerCategory.getCategoryTitle(), stickerCategory));
    }

    @Override
    public boolean isRevertible() {
        return false;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected StickerCategoriesEditorTool(Parcel in) {
        super(in);
    }

    public static final Creator<StickerCategoriesEditorTool> CREATOR = new Creator<StickerCategoriesEditorTool>() {
        @Override
        public StickerCategoriesEditorTool createFromParcel(Parcel source) {
            return new StickerCategoriesEditorTool(source);
        }

        @Override
        public StickerCategoriesEditorTool[] newArray(int size) {
            return new StickerCategoriesEditorTool[size];
        }
    };
}
