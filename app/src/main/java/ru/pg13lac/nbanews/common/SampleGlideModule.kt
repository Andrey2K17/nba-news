package ru.pg13lac.nbanews.common

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class SampleGlideModule: AppGlideModule() {
    override fun isManifestParsingEnabled(): Boolean = false
}