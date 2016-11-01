package com.photoeditorsdk.android.app.stickercategories;

import android.os.Parcel;

import java.util.ArrayList;

import ly.img.android.processor.StateEvents;
import ly.img.android.sdk.models.state.manager.Settings;

/**
 * Created by svennahler on 01.11.16.
 */

public class StickerCategoriesSettings extends Settings<StickerCategoriesSettings.Events> {

    @StateEvents
    enum Events {
        // Settings can be fire events but we don't need it in this example.
    }

    private ArrayList<StickerCategory> stickerCategories = null;

    public StickerCategoriesSettings() {
        super(Events.class); // Must be call the super constructor
    }

    public ArrayList<StickerCategory> getStickerCategories() {
        return stickerCategories;
    }

    public void setStickerCategories(ArrayList<StickerCategory> stickerCategories) {
        this.stickerCategories = stickerCategories;
    }

    // Settings must be parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.stickerCategories);
    }

    protected StickerCategoriesSettings(Parcel in) {
        super(in);
        this.stickerCategories = in.createTypedArrayList(StickerCategory.CREATOR);
    }

    public static final Creator<StickerCategoriesSettings> CREATOR = new Creator<StickerCategoriesSettings>() {
        @Override
        public StickerCategoriesSettings createFromParcel(Parcel source) {
            return new StickerCategoriesSettings(source);
        }

        @Override
        public StickerCategoriesSettings[] newArray(int size) {
            return new StickerCategoriesSettings[size];
        }
    };


}
