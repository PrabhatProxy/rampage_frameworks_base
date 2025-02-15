/*
 * Copyright (C) 2017 The Android Open Source Project
 * Copyright (C) 2017 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use mHost file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.android.systemui.qs.tileimpl;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.ContextThemeWrapper;

import com.android.systemui.R;
import com.android.systemui.plugins.qs.QSFactory;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.tiles.AirplaneModeTile;
import com.android.systemui.qs.tiles.BatterySaverTile;
import com.android.systemui.qs.tiles.BluetoothTile;
import com.android.systemui.qs.tiles.CaffeineTile;
import com.android.systemui.qs.tiles.CastTile;
import com.android.systemui.qs.tiles.CellularTile;
import com.android.systemui.qs.tiles.ColorInversionTile;
import com.android.systemui.qs.tiles.CPUInfoTile;
import com.android.systemui.qs.tiles.DataSaverTile;
import com.android.systemui.qs.tiles.DataSwitchTile;
import com.android.systemui.qs.tiles.DcDimmingTile;
import com.android.systemui.qs.tiles.DndTile;
import com.android.systemui.qs.tiles.FlashlightTile;
import com.android.systemui.qs.tiles.FPSInfoTile;
import com.android.systemui.qs.tiles.GamingTile;
import com.android.systemui.qs.tiles.HeadsUpTile;
import com.android.systemui.qs.tiles.HotspotTile;
import com.android.systemui.qs.tiles.LiveDisplayTile;
import com.android.systemui.qs.tiles.LocationTile;
import com.android.systemui.qs.tiles.LteTile;
import com.android.systemui.qs.tiles.NavBarTile;
import com.android.systemui.qs.tiles.NfcTile;
import com.android.systemui.qs.tiles.NightDisplayTile;
import com.android.systemui.qs.tiles.ReadingModeTile;
import com.android.systemui.qs.tiles.RebootTile;
import com.android.systemui.qs.tiles.RotationLockTile;
import com.android.systemui.qs.tiles.SensorPrivacyTile;
import com.android.systemui.qs.tiles.ScreenRecordTile;
import com.android.systemui.qs.tiles.ScreenshotTile;
import com.android.systemui.qs.tiles.SyncTile;
import com.android.systemui.qs.tiles.ThemeTile;
import com.android.systemui.qs.tiles.UiModeNightTile;
import com.android.systemui.qs.tiles.UsbTetherTile;
import com.android.systemui.qs.tiles.UserTile;
import com.android.systemui.qs.tiles.VpnTile;
import com.android.systemui.qs.tiles.SoundTile;
import com.android.systemui.qs.tiles.SleepModeTile;
import com.android.systemui.qs.tiles.WeatherTile;
import com.android.systemui.qs.tiles.WifiTile;
import com.android.systemui.qs.tiles.WorkModeTile;
import com.android.systemui.util.leak.GarbageMonitor;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Lazy;

@Singleton
public class QSFactoryImpl implements QSFactory {

    private static final String TAG = "QSFactory";

    private final Provider<WifiTile> mWifiTileProvider;
    private final Provider<BluetoothTile> mBluetoothTileProvider;
    private final Provider<CellularTile> mCellularTileProvider;
    private final Provider<DndTile> mDndTileProvider;
    private final Provider<ColorInversionTile> mColorInversionTileProvider;
    private final Provider<AirplaneModeTile> mAirplaneModeTileProvider;
    private final Provider<WorkModeTile> mWorkModeTileProvider;
    private final Provider<RotationLockTile> mRotationLockTileProvider;
    private final Provider<FlashlightTile> mFlashlightTileProvider;
    private final Provider<LocationTile> mLocationTileProvider;
    private final Provider<CastTile> mCastTileProvider;
    private final Provider<HotspotTile> mHotspotTileProvider;
    private final Provider<UserTile> mUserTileProvider;
    private final Provider<BatterySaverTile> mBatterySaverTileProvider;
    private final Provider<DataSaverTile> mDataSaverTileProvider;
    private final Provider<NightDisplayTile> mNightDisplayTileProvider;
    private final Provider<NfcTile> mNfcTileProvider;
    private final Provider<SensorPrivacyTile> mSensorPrivacyTileProvider;
    private final Provider<GarbageMonitor.MemoryTile> mMemoryTileProvider;
    private final Provider<UiModeNightTile> mUiModeNightTileProvider;
    private final Provider<ScreenRecordTile> mScreenRecordTileProvider;
    private final Provider<ThemeTile> mThemeTileProvider;
    private final Provider<RebootTile> mRebootTileProvider;
    private final Provider<HeadsUpTile> mHeadsUpTileProvider;
    private final Provider<DataSwitchTile> mDataSwitchTileProvider;
    private final Provider<UsbTetherTile> mUsbTetherTileProvider;
    private final Provider<ScreenshotTile> mScreenshotTileProvider;
    private final Provider<CPUInfoTile> mCPUInfoTileProvider;
    private final Provider<CaffeineTile> mCaffeineTileProvider;
    private final Provider<FPSInfoTile> mFPSInfoTileProvider;
    private final Provider<DcDimmingTile> mDcDimmingTileProvider;
    private final Provider<LiveDisplayTile> mLiveDisplayTileProvider;
    private final Provider<ReadingModeTile> mReadingModeTileProvider;
    private final Provider<SyncTile> mSyncTileProvider;
    private final Provider<VpnTile> mVpnTileProvider;
    private final Provider<SoundTile> mSoundTileProvider;
    private final Provider<LteTile> mLteTileProvider;
    private final Provider<WeatherTile> mWeatherTileProvider;
    private final Provider<NavBarTile> mNavBarTileProvider;
    private final Provider<GamingTile> mGamingTileProvider;
    private final Provider<SleepModeTile> mSleepModeTileProvider;

    private final Lazy<QSHost> mQsHostLazy;

    @Inject
    public QSFactoryImpl(Lazy<QSHost> qsHostLazy,
            Provider<WifiTile> wifiTileProvider,
            Provider<BluetoothTile> bluetoothTileProvider,
            Provider<CellularTile> cellularTileProvider,
            Provider<DndTile> dndTileProvider,
            Provider<ColorInversionTile> colorInversionTileProvider,
            Provider<AirplaneModeTile> airplaneModeTileProvider,
            Provider<WorkModeTile> workModeTileProvider,
            Provider<RotationLockTile> rotationLockTileProvider,
            Provider<FlashlightTile> flashlightTileProvider,
            Provider<LocationTile> locationTileProvider,
            Provider<CastTile> castTileProvider,
            Provider<HotspotTile> hotspotTileProvider,
            Provider<UserTile> userTileProvider,
            Provider<BatterySaverTile> batterySaverTileProvider,
            Provider<DataSaverTile> dataSaverTileProvider,
            Provider<NightDisplayTile> nightDisplayTileProvider,
            Provider<NfcTile> nfcTileProvider,
            Provider<SensorPrivacyTile> sensorPrivacyTileProvider,
            Provider<GarbageMonitor.MemoryTile> memoryTileProvider,
            Provider<UiModeNightTile> uiModeNightTileProvider,
            Provider<ScreenRecordTile> screenRecordTileProvider,
            Provider<ThemeTile> themeTileProvider,
            Provider<RebootTile> rebootTileProvider,
            Provider<HeadsUpTile> headsUpTileProvider,
            Provider<DataSwitchTile> dataSwitchTileProvider,
            Provider<UsbTetherTile> usbTetherTileProvider,
            Provider<ScreenshotTile> screenshotTileProvider,
            Provider<CPUInfoTile> cpuInfoTileProvider,
            Provider<CaffeineTile> caffeineTileProvider,
            Provider<DcDimmingTile> dcDimTileProvider,
            Provider<FPSInfoTile> fpsInfoTileProvider,
            Provider<LiveDisplayTile> liveDisplayTileProvider,
            Provider<ReadingModeTile> readingModeTileProvider,
            Provider<SyncTile> syncTileProvider,
            Provider<VpnTile> vpnTileProvider,
            Provider<SoundTile> soundTileProvider,
            Provider<LteTile> lteTileProvider,
            Provider<WeatherTile> weatherTileProvider,
            Provider<GamingTile> gamingTileProvider,
            Provider<SleepModeTile> sleepModeTileProvider,
            Provider<NavBarTile> navbarTileProvider) {
        mQsHostLazy = qsHostLazy;
        mWifiTileProvider = wifiTileProvider;
        mBluetoothTileProvider = bluetoothTileProvider;
        mCellularTileProvider = cellularTileProvider;
        mDndTileProvider = dndTileProvider;
        mColorInversionTileProvider = colorInversionTileProvider;
        mAirplaneModeTileProvider = airplaneModeTileProvider;
        mWorkModeTileProvider = workModeTileProvider;
        mRotationLockTileProvider = rotationLockTileProvider;
        mFlashlightTileProvider = flashlightTileProvider;
        mLocationTileProvider = locationTileProvider;
        mCastTileProvider = castTileProvider;
        mHotspotTileProvider = hotspotTileProvider;
        mUserTileProvider = userTileProvider;
        mBatterySaverTileProvider = batterySaverTileProvider;
        mDataSaverTileProvider = dataSaverTileProvider;
        mNightDisplayTileProvider = nightDisplayTileProvider;
        mNfcTileProvider = nfcTileProvider;
        mSensorPrivacyTileProvider = sensorPrivacyTileProvider;
        mMemoryTileProvider = memoryTileProvider;
        mUiModeNightTileProvider = uiModeNightTileProvider;
        mScreenRecordTileProvider = screenRecordTileProvider;
        mThemeTileProvider = themeTileProvider;
        mRebootTileProvider = rebootTileProvider;
        mHeadsUpTileProvider = headsUpTileProvider;
        mDataSwitchTileProvider = dataSwitchTileProvider;
        mUsbTetherTileProvider = usbTetherTileProvider;
        mScreenshotTileProvider = screenshotTileProvider;
        mCPUInfoTileProvider = cpuInfoTileProvider;
        mCaffeineTileProvider = caffeineTileProvider;
        mFPSInfoTileProvider = fpsInfoTileProvider;
        mDcDimmingTileProvider = dcDimTileProvider;
        mLiveDisplayTileProvider = liveDisplayTileProvider;
        mReadingModeTileProvider = readingModeTileProvider;
        mSyncTileProvider = syncTileProvider;
        mVpnTileProvider = vpnTileProvider;
        mSoundTileProvider = soundTileProvider;
        mLteTileProvider = lteTileProvider;
        mWeatherTileProvider = weatherTileProvider;
        mNavBarTileProvider = navbarTileProvider;
        mGamingTileProvider = gamingTileProvider;
        mSleepModeTileProvider = sleepModeTileProvider;
    }

    public QSTile createTile(String tileSpec) {
        QSTileImpl tile = createTileInternal(tileSpec);
        if (tile != null) {
            tile.handleStale(); // Tile was just created, must be stale.
        }
        return tile;
    }

    private QSTileImpl createTileInternal(String tileSpec) {
        switch (tileSpec) {
            case "wifi":
                return mWifiTileProvider.get();
            case "bt":
                return mBluetoothTileProvider.get();
            case "cell":
                return mCellularTileProvider.get();
            case "dnd":
                return mDndTileProvider.get();
            case "inversion":
                return mColorInversionTileProvider.get();
            case "airplane":
                return mAirplaneModeTileProvider.get();
            case "work":
                return mWorkModeTileProvider.get();
            case "rotation":
                return mRotationLockTileProvider.get();
            case "flashlight":
                return mFlashlightTileProvider.get();
            case "location":
                return mLocationTileProvider.get();
            case "cast":
                return mCastTileProvider.get();
            case "hotspot":
                return mHotspotTileProvider.get();
            case "user":
                return mUserTileProvider.get();
            case "battery":
                return mBatterySaverTileProvider.get();
            case "saver":
                return mDataSaverTileProvider.get();
            case "night":
                return mNightDisplayTileProvider.get();
            case "nfc":
                return mNfcTileProvider.get();
            case "sensorprivacy":
                return mSensorPrivacyTileProvider.get();
            case "dark":
                return mUiModeNightTileProvider.get();
            case "screenrecord":
                return mScreenRecordTileProvider.get();
            case "theme":
                return mThemeTileProvider.get();
            case "reboot":
                return mRebootTileProvider.get();
            case "heads_up":
                return mHeadsUpTileProvider.get();
            case "dataswitch":
                return mDataSwitchTileProvider.get();
            case "usb_tether":
                return mUsbTetherTileProvider.get();
            case "screenshot":
                return mScreenshotTileProvider.get();
            case "cpuinfo":
                return mCPUInfoTileProvider.get();
            case "caffeine":
                return mCaffeineTileProvider.get();
            case "fpsinfo":
                return mFPSInfoTileProvider.get();
            case "dc_dimming":
                return mDcDimmingTileProvider.get();
            case "livedisplay":
                return mLiveDisplayTileProvider.get();
            case "reading_mode":
                return mReadingModeTileProvider.get();
            case "sync":
                return mSyncTileProvider.get();
            case "vpn":
                return mVpnTileProvider.get();
            case "sound":
                return mSoundTileProvider.get();
            case "lte":
                return mLteTileProvider.get();
            case "weather":
                return mWeatherTileProvider.get();
            case "navbar":
                return mNavBarTileProvider.get();
            case "gaming":
                return mGamingTileProvider.get();
            case "sleep_mode":
                return mSleepModeTileProvider.get();
        }

        // Custom tiles
        if (tileSpec.startsWith(CustomTile.PREFIX)) {
            return CustomTile.create(mQsHostLazy.get(), tileSpec,
                    mQsHostLazy.get().getUserContext());
        }

        // Debug tiles.
        if (Build.IS_ENG) {
            if (tileSpec.equals(GarbageMonitor.MemoryTile.TILE_SPEC)) {
                return mMemoryTileProvider.get();
            }
        }

        // Broken tiles.
        Log.w(TAG, "No stock tile spec: " + tileSpec);
        return null;
    }

    @Override
    public QSTileView createTileView(QSTile tile, boolean collapsedView) {
        Context context = new ContextThemeWrapper(mQsHostLazy.get().getContext(), R.style.qs_theme);
        QSIconView icon = tile.createTileView(context);
        if (collapsedView) {
            return new QSTileBaseView(context, icon, collapsedView);
        } else {
            return new com.android.systemui.qs.tileimpl.QSTileView(context, icon);
        }
    }
}
