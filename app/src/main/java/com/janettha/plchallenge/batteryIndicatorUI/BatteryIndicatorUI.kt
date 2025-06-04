package com.janettha.plchallenge.batteryIndicatorUI

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.janettha.plchallenge.R
import com.janettha.plchallenge.ui.theme.PLChallengeTheme
import kotlinx.coroutines.delay

data object IconsAndColors {
    const val colorBatteryBackground = 0xFFE7E9EF
    const val colorBatteryWhite = 0xFFFFFFFF
    const val colorBatteryInactive = 0xFFC1C5D2
    const val colorBatteryLow = 0xFFFF4E51
    const val colorBatteryMedium = 0xFFFCB966
    const val colorBatteryHigh = 0xFF19D181
    const val colorHeartActive = 0xFFFE6264
    const val colorHeartInactive = 0xFFFF4E51
    val icons = listOf(
        R.drawable.ic_battery_heart_pulse_1,
        R.drawable.ic_battery_heart_pulse_2
    )
}

@Composable
fun BatteryIndicatorUI2(
    modifier: Modifier = Modifier
) {
    val batteryLevel = getBatteryLevel()
    val percentValue: Float = (batteryLevel/10).toFloat()/2
    val batteryStatus: Int
    val batteryColor: Long
    var settingsBck: Map<String, Any>
    when {
        batteryLevel >= 80 -> {
            batteryColor = IconsAndColors.colorBatteryHigh
            batteryStatus = 2
        }
        batteryLevel < 20 -> {
            batteryColor = IconsAndColors.colorBatteryLow
            batteryStatus = 0
        }
        else -> {
            batteryColor = IconsAndColors.colorBatteryMedium
            batteryStatus = 1
        }
    }

    // region Animation - Launcher used for heart beating
    var isAnimating by remember { mutableStateOf(true) }
    var currentIconIndex by remember { mutableIntStateOf(0) }
    LaunchedEffect(isAnimating) {
        while (isAnimating) {
            delay(500)
            currentIconIndex = (currentIconIndex + 1) % 2
        }
    }
    // endregion

    Row(modifier = modifier
        .fillMaxSize()
        .background(Color(IconsAndColors.colorBatteryBackground))
        .padding(vertical = 16.dp, horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // region Heart
        Box (modifier = Modifier
            .width(48.dp)
            .height(48.dp)
            .background(
                Color.Transparent,
                RoundedCornerShape(6.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            when(batteryStatus) {
                1 -> {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_battery_heart_inactive),
                        tint = Color(IconsAndColors.colorBatteryInactive),
                        contentDescription = null
                    )
                }
                0, 2 -> {
                    Icon(
                        painter = painterResource(id = IconsAndColors.icons[currentIconIndex]),
                        contentDescription = null,
                        tint = if(currentIconIndex == 0) Color(IconsAndColors.colorHeartActive) else Color(IconsAndColors.colorHeartInactive),
                        modifier = Modifier
                            .size(80.dp)
                            .clickable { isAnimating = false }
                    )
                }
            }
        }
        // endregion
        // region Battery
        Box(modifier = Modifier
            .padding(start = 16.dp)
            .width(200.dp)
            .height(60.dp)
            .background(
                Color(IconsAndColors.colorBatteryWhite),
                RoundedCornerShape(
                    topStart = 8.dp,
                    bottomStart = 8.dp,
                    topEnd = 8.dp,
                    bottomEnd = 8.dp,
                )
            )
        ){
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
            ) {
                // region NIVEL 0.5 - 1
                settingsBck = getBackgroundSt(percentValue, 0F, batteryColor)
                Box(
                    modifier = Modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                        )
                            /*Color(if(percentValue > 0) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topStart = 8.dp,
                                bottomStart = 8.dp,
                                topEnd = if (percentValue > 0 && percentValue <= 0.5F) 8.dp else 0.dp,
                                bottomEnd = if (percentValue > 0 && percentValue <= 0.5F) 8.dp else 0.dp,
                            )
                        )*/
                )
                settingsBck = getBackgroundSt(percentValue, 0.5F, batteryColor)
                Box(
                    modifier = modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                            /*Color(if(percentValue > 0.5) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topEnd = if (percentValue > 0.5 && percentValue <= 1F) 8.dp else 0.dp,
                                bottomEnd = if (percentValue > 0.5 && percentValue <= 1F) 8.dp else 0.dp,
                            )*/
                        )
                )
                // endregion
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(.03F)
                        .background(
                            Color(if(percentValue > 1) batteryColor else IconsAndColors.colorBatteryWhite).copy(alpha = .5F)
                        )
                )
                // region NIVEL 1.5 - 2
                settingsBck = getBackgroundSt(percentValue, 1F, batteryColor)
                Box(
                    modifier = modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            /*Color(if(percentValue > 1) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topEnd = if (percentValue > 1 && percentValue <= 1.5F) 8.dp else 0.dp,
                                bottomEnd = if (percentValue > 1 && percentValue <= 1.5F) 8.dp else 0.dp,
                            )*/
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                        )
                )
                settingsBck = getBackgroundSt(percentValue, 1.5F, batteryColor)
                Box(
                    modifier = modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            /*Color(if(percentValue > 1.5) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topEnd = if (percentValue > 1.5 && percentValue <= 2F) 8.dp else 0.dp,
                                bottomEnd = if (percentValue > 1.5 && percentValue <= 2F) 8.dp else 0.dp,
                            )*/
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                        )
                )
                // endregion
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(.03F)
                        .background(
                            Color(if(percentValue > 2) batteryColor else IconsAndColors.colorBatteryWhite).copy(alpha = .5F)
                        )
                )
                // region NIVEL 2.5 - 3
                settingsBck = getBackgroundSt(percentValue, 2F, batteryColor)
                Box(
                    modifier = modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            /*Color(if(percentValue > 2) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topEnd = if (percentValue > 2 && percentValue <= 2.5F) 8.dp else 0.dp,
                                bottomEnd = if (percentValue > 2 && percentValue <= 2.5F) 8.dp else 0.dp,
                            )*/
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                        )
                )
                settingsBck = getBackgroundSt(percentValue, 2.5F, batteryColor)
                Box(
                    modifier = modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            /*Color(if(percentValue > 2.5) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topEnd = if (percentValue > 2.5 && percentValue <= 3F) 8.dp else 0.dp,
                                bottomEnd = if (percentValue > 2.5 && percentValue <= 3F) 8.dp else 0.dp,
                            )*/
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                        )
                )
                // endregion
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(.03F)
                        .background(
                            Color(if(percentValue > 3) batteryColor else IconsAndColors.colorBatteryWhite).copy(alpha = .5F)
                        )
                )
                // region NIVEL 3.5 - 4
                settingsBck = getBackgroundSt(percentValue, 3F, batteryColor)
                Box(
                    modifier = modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            /*Color(if(percentValue > 3) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topEnd = if (percentValue > 3 && percentValue <= 3.5F) 8.dp else 0.dp,
                                bottomEnd = if (percentValue > 3 && percentValue <= 3.5F) 8.dp else 0.dp,
                            )*/
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                        )
                )
                settingsBck = getBackgroundSt(percentValue, 3.5F, batteryColor)
                Box(
                    modifier = modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            /*Color(if(percentValue > 3.5) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topEnd = if (percentValue > 3.5 && percentValue <= 4F) 8.dp else 0.dp,
                                bottomEnd = if (percentValue > 3.5 && percentValue <= 4F) 8.dp else 0.dp,
                            )*/
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                        )
                )
                // endregion
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(.03F)
                        .background(
                            Color(if(percentValue > 4) batteryColor else IconsAndColors.colorBatteryWhite).copy(alpha = .5F)
                        )
                )
                // region NIVEL 4.5 - 5
                settingsBck = getBackgroundSt(percentValue, 4F, batteryColor)
                Box(
                    modifier = modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            /*Color(if(percentValue > 4) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topEnd = if (percentValue > 4 && percentValue <= 4.5F) 8.dp else 0.dp,
                                bottomEnd = if (percentValue > 4 && percentValue <= 4.5F) 8.dp else 0.dp,
                            )*/
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                        )
                )
                settingsBck = getBackgroundSt(percentValue, 4.5F, batteryColor)
                Box(
                    modifier = modifier
                        .weight(.5F)
                        .fillMaxSize()
                        .background(
                            /*Color(if(percentValue > 4.5) batteryColor else Colors.colorBatteryWhite),
                            RoundedCornerShape(
                                topEnd = 8.dp,
                                bottomEnd = 8.dp,
                            )*/
                            color = settingsBck["color"] as Color,
                            shape = settingsBck["shape"] as RoundedCornerShape
                        )
                )
                // endregion
            }
        }
        // + Pila
        Box(modifier = Modifier
            .wrapContentSize()
        ){
            Box(modifier = Modifier
                .width(5.dp)
                .height(25.dp)
                .align(Alignment.CenterStart)
                .background(
                    Color(IconsAndColors.colorBatteryWhite),
                    RoundedCornerShape(
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                    )
                )
            )
        }
        // endregion
        // region Trebol
        Box(modifier = Modifier
            .wrapContentSize()
            .padding(start = 16.dp)
            .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ){
            Icon(
                modifier = Modifier.width(48.dp).height(48.dp),
                imageVector = ImageVector.vectorResource(
                    if (batteryStatus == 2) R.drawable.ic_battery_clover_active
                    else R.drawable.ic_battery_clover_inactive
                ),
                tint = when {
                    batteryStatus == 2 -> Color(IconsAndColors.colorBatteryHigh)
                    else -> Color(IconsAndColors.colorBatteryInactive)
                },
                contentDescription = null
            )
        }
        // endregion
    }
}

@Composable
private fun getBatteryLevel(): Int {
    val context = LocalContext.current
    var batteryLevel by remember { mutableIntStateOf(0) }

    // Register receiver
    DisposableEffect(Unit) {
        val batteryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                batteryLevel = (level * 100) / scale
            }
        }

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(batteryReceiver, intentFilter)

        onDispose {
            context.unregisterReceiver(batteryReceiver)
        }
    }

    return batteryLevel
}

// Get Setting to Background for each Box level in Battery
private fun getBackgroundSt(percentValue: Float, batteryLevel: Float, batteryColor: Long): Map<String, Any> {
    return mapOf(
        "color" to Color(if(percentValue > batteryLevel) batteryColor else IconsAndColors.colorBatteryWhite),
        "shape" to RoundedCornerShape(
            topStart = if(batteryLevel == 0F) 8.dp else 0.dp,
            bottomStart = if(batteryLevel == 0F) 8.dp else 0.dp,
            topEnd = if (batteryLevel == 4.5F || percentValue > batteryLevel && percentValue <= batteryLevel+.5F) 8.dp else 0.dp,
            bottomEnd = if (batteryLevel == 4.5F || percentValue > batteryLevel && percentValue <= batteryLevel+.5F) 8.dp else 0.dp,
        )
    )
}

@Preview
@Composable
private fun BatteryIndicatorUIPreview() {
    PLChallengeTheme {
        BatteryIndicatorUI2()
    }
}
