package com.photoeditorsdk.android.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.photoeditorsdk.android.app.stickercategories.StickerCategoriesEditorTool;
import com.photoeditorsdk.android.app.stickercategories.StickerCategoriesSettings;
import com.photoeditorsdk.android.app.stickercategories.StickerCategory;

import java.io.File;
import java.util.ArrayList;

import ly.img.android.sdk.cropper.cropwindow.CropRect.CropRect;
import ly.img.android.sdk.models.chunk.ChunkModelInterface;
import ly.img.android.sdk.models.chunk.RelativeRect;
import ly.img.android.sdk.models.config.ImageStickerConfig;
import ly.img.android.sdk.models.constant.Directory;
import ly.img.android.sdk.models.state.CameraSettings;
import ly.img.android.sdk.models.state.CropSettings;
import ly.img.android.sdk.models.state.EditorLoadSettings;
import ly.img.android.sdk.models.state.EditorSaveSettings;
import ly.img.android.sdk.models.state.ImgLyConfig;
import ly.img.android.sdk.models.state.manager.SettingsList;
import ly.img.android.sdk.models.state.manager.StateHandler;
import ly.img.android.sdk.operator.CropOperation;
import ly.img.android.sdk.operator.ImageLoadOperation;
import ly.img.android.sdk.operator.ImageSaveOperation;
import ly.img.android.sdk.operator.Operator;
import ly.img.android.ui.activities.CameraPreviewActivity;
import ly.img.android.ui.activities.CameraPreviewBuilder;
import ly.img.android.ui.utilities.PermissionRequest;

public class MainActivity extends Activity implements PermissionRequest.Response {

    private static final String FOLDER = "ImgLy";
    public static int CAMERA_PREVIEW_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SettingsList settingsList = new SettingsList();


        settingsList.getConfig().getTools().add(0, new StickerCategoriesEditorTool(R.string.imgly_tool_name_sticker, R.drawable.imgly_icon_tool_sticker));

        ArrayList<StickerCategory> stickerCategories = new ArrayList<>();

        StickerCategory stickers = new StickerCategory(R.string.imgly_sticker_name_glasses_normal, R.drawable.imgly_sticker_preview_glasses_normal);
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_glasses_normal, R.drawable.imgly_sticker_preview_glasses_normal, R.drawable.imgly_sticker_glasses_normal));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_glasses_nerd, R.drawable.imgly_sticker_preview_glasses_nerd, R.drawable.imgly_sticker_glasses_nerd));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_glasses_shutter_green, R.drawable.imgly_sticker_preview_glasses_shutter_green, R.drawable.imgly_sticker_glasses_shutter_green));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_glasses_shutter_yellow, R.drawable.imgly_sticker_preview_glasses_shutter_yellow, R.drawable.imgly_sticker_glasses_shutter_yellow));

        stickerCategories.add(stickers);

        stickers = new StickerCategory(R.string.imgly_sticker_name_hat_cap, R.drawable.imgly_sticker_preview_hat_cap);
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_hat_cap, R.drawable.imgly_sticker_preview_hat_cap, R.drawable.imgly_sticker_hat_cap));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_hat_sherrif, R.drawable.imgly_sticker_preview_hat_sherrif, R.drawable.imgly_sticker_hat_sherrif));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_hat_party, R.drawable.imgly_sticker_preview_hat_party, R.drawable.imgly_sticker_hat_party));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_hat_zylinder, R.drawable.imgly_sticker_preview_hat_zylinder, R.drawable.imgly_sticker_hat_zylinder));

        stickerCategories.add(stickers);

        stickers = new StickerCategory(R.string.imgly_sticker_name_mustache1, R.drawable.imgly_sticker_preview_mustache1);
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_mustache1, R.drawable.imgly_sticker_preview_mustache1, R.drawable.imgly_sticker_mustache1));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_mustache2, R.drawable.imgly_sticker_preview_mustache2, R.drawable.imgly_sticker_mustache2));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_mustache3, R.drawable.imgly_sticker_preview_mustache3, R.drawable.imgly_sticker_mustache3));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_mustache_long, R.drawable.imgly_sticker_preview_mustache_long, R.drawable.imgly_sticker_mustache_long));

        stickerCategories.add(stickers);

        stickers = new StickerCategory(R.string.imgly_sticker_name_star, R.drawable.imgly_sticker_preview_star);
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_snowflake, R.drawable.imgly_sticker_preview_snowflake, R.drawable.imgly_sticker_snowflake));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_heart, R.drawable.imgly_sticker_preview_heart, R.drawable.imgly_sticker_heart));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_pipe, R.drawable.imgly_sticker_preview_pipe, R.drawable.imgly_sticker_pipe));
        stickers.add(new ImageStickerConfig(R.string.imgly_sticker_name_star, R.drawable.imgly_sticker_preview_star, R.drawable.imgly_sticker_star));

        stickerCategories.add(stickers);

        settingsList.getSettingsModel(StickerCategoriesSettings.class).setStickerCategories(stickerCategories);

        settingsList
                .getSettingsModel(CameraSettings.class)
                .setExportDir(Directory.DCIM, FOLDER)
                .setExportPrefix("camera_")

                .getSettingsModel(EditorSaveSettings.class)
                .setExportDir(Directory.DCIM, FOLDER)
                .setExportPrefix("result_")
                .setSavePolicy(EditorSaveSettings.SavePolicy.KEEP_SOURCE_AND_CREATE_ALWAYS_OUTPUT);

        new CameraPreviewBuilder(this)
                .setSettingsList(settingsList)
                .startActivityForResult(this, CAMERA_PREVIEW_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_PREVIEW_RESULT) {
            String path = data.getStringExtra(CameraPreviewActivity.RESULT_IMAGE_PATH);

            Toast.makeText(this, "Image saved at: " + path, Toast.LENGTH_LONG).show();

            File mMediaFolder = new File(path);

            MediaScannerConnection.scanFile(this, new String[] {mMediaFolder.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {

                        }
                    }
            );
        }
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void permissionGranted() {

    }

    @Override
    public void permissionDenied() {
        finish();
        System.exit(0);
    }
}
