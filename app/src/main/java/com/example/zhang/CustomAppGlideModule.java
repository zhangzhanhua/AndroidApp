package com.example.zhang;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import java.io.File;

@GlideModule
public class CustomAppGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
        int diskCacheSizeBytes = 1024 * 1024 * 100;  //100 MB
        String diskCacheDir = "data/Glide/";
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes))
//                .setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes));
                .setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, diskCacheDir, diskCacheSizeBytes));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

}
