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

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import ly.img.android.sdk.models.config.AbstractConfig;
import ly.img.android.sdk.models.config.ImageStickerConfig;
import ly.img.android.sdk.models.config.TextStickerConfig;
import ly.img.android.sdk.tools.AbstractToolPanel;
import ly.img.android.ui.adapter.DataSourceListAdapter;
import ly.img.android.ui.widgets.HorizontalListView;

/**
 * Tool view a Stickers
 */
public class StickerCategoryToolPanel extends AbstractToolPanel<StickerCategoryEditorTool> implements DataSourceListAdapter.OnItemClickListener<AbstractConfig.StickerConfigInterface> {

    private static final int LAYOUT = ly.img.android.R.layout.imgly_panel_tool_sticker;

    private StickerCategoryEditorTool stickerTool;

    @Override
    protected int getLayoutResource() {
        return LAYOUT;
    }

    @Override
    protected void onAttached(Context context, @NonNull View panelView, StickerCategoryEditorTool tool) {
        super.onAttached(context, panelView, tool);

        HorizontalListView listView = (HorizontalListView) panelView.findViewById(ly.img.android.R.id.optionList);

        this.stickerTool = tool;

        DataSourceListAdapter listAdapter = new DataSourceListAdapter(context);
        listAdapter.setData(stickerTool.getStickerCategory().getStickerConfigs());
        listAdapter.setOnItemClickListener(this);
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onDetached() {

    }

    @Override
    public void onItemClick(@NonNull AbstractConfig.StickerConfigInterface entity) {
        if (entity instanceof ImageStickerConfig) {
            stickerTool.addSticker((ImageStickerConfig) entity);
        } else {
            stickerTool.addSticker((TextStickerConfig) entity);
        }
    }
}