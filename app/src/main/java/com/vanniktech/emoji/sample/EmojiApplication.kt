/*
 * Copyright (C) 2016 - Niklas Baudy, Ruben Gees, Mario Đanić and contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vanniktech.emoji.sample

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.googlecompat.GoogleCompatEmojiProvider
import timber.log.Timber

class EmojiApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    val emojiCompat = EmojiCompat.init(
      FontRequestEmojiCompatConfig(
        this,
        FontRequest(
          "com.google.android.gms.fonts",
          "com.google.android.gms",
          "Noto Color Emoji Compat",
          R.array.com_google_android_gms_fonts_certs,
        ),
      ).setReplaceAll(true),
    )
  EmojiManager.install(GoogleCompatEmojiProvider(emojiCompat!!))


    StrictMode.setThreadPolicy(ThreadPolicy.Builder().detectAll().build())
    StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().build())

    Timber.plant(Timber.DebugTree())
  }
}
