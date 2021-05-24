package com.example.taipeizoomdemo.view.base

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule: AppGlideModule() {
    /**
     *     Failed to find GeneratedAppGlideModule.
     *     You should include an annotationProcessor
     *     compile dependency on com.github.bumptech.glide:compiler
     *     in your application and a @GlideModule annotated
     *     AppGlideModule implementation or LibraryGlideModules will be silently ignored.
     *     為了解決這個異常狀態，特地新件一個工具類，只要繼承了AppGlideModule，在加載圖片的時候就會自動被使用到
     */
}