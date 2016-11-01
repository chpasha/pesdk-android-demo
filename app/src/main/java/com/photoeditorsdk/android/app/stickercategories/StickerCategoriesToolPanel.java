package com.photoeditorsdk.android.app.stickercategories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.photoeditorsdk.android.app.R;

import ly.img.android.sdk.tools.AbstractToolPanel;
import ly.img.android.ui.adapter.DataSourceListAdapter;
import ly.img.android.ui.widgets.HorizontalListView;

/**
 * Created by svennahler on 01.11.16.
 */

public class StickerCategoriesToolPanel extends AbstractToolPanel<StickerCategoriesEditorTool> implements DataSourceListAdapter.OnItemClickListener<StickerCategory> {

    private static final int LAYOUT = R.layout.imgly_panel_tool_sticker_categories;

    private StickerCategoriesEditorTool stickerTool;

    @Override
    protected int getLayoutResource() {
        return LAYOUT;
    }

    @Override
    protected void onAttached(Context context, @NonNull View panelView, StickerCategoriesEditorTool tool) {
        super.onAttached(context, panelView, tool);

        HorizontalListView listView = (HorizontalListView) panelView.findViewById(ly.img.android.R.id.optionList);

        this.stickerTool = tool;

        // Get your StickerCategories over the StateHandler;
        StickerCategoriesSettings stickerCategoriesSettings = stickerTool.getStateHandler().getStateModel(StickerCategoriesSettings.class);

        DataSourceListAdapter listAdapter = new DataSourceListAdapter(context);

        listAdapter.setData(stickerCategoriesSettings.getStickerCategories());
        listAdapter.setOnItemClickListener(this);
        listView.setAdapter(listAdapter);
    }

    @Override
    protected void onDetached() {

    }

    @Override
    public void onItemClick(@NonNull StickerCategory entity) {
        stickerTool.openStickerCategory(entity);
    }

}
