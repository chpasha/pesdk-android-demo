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


import android.support.annotation.StringRes;

import ly.img.android.sdk.tools.StickerEditorTool;

public class StickerCategoryEditorTool extends StickerEditorTool {

    private StickerCategory stickerCategory;

    @Override
    public boolean isRevertible() {
        return false;
    }

    public StickerCategoryEditorTool(@StringRes int name, StickerCategory stickerCategory) {
        super(name, ly.img.android.R.drawable.imgly_icon_cancel, StickerCategoryToolPanel.class);
        this.stickerCategory = stickerCategory;
    }

    public StickerCategory getStickerCategory() {
        return stickerCategory;
    }
}
