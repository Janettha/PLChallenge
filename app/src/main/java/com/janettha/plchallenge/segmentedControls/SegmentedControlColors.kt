package com.janettha.plchallenge.segmentedControls

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SegmentedControlColors(
    val containerBackground: Color,
    val selectedBackground: Color,
    val selectedTextColor: Color,
    val unselectedTextColor: Color,
    val separatorColor: Color
)

object ModernSegmentedControlDefaults {
    @Composable
    fun colors(
        containerBackground: Color = Color(0xFFF2F4F7),
        selectedBackground: Color = Color(0xFF2563EB),
        selectedTextColor: Color = Color.White,
        unselectedTextColor: Color = Color(0xFF64748B),
        separatorColor: Color = Color(0xFFCBD5E1)
    ) = SegmentedControlColors(
        containerBackground = containerBackground,
        selectedBackground = selectedBackground,
        selectedTextColor = selectedTextColor,
        unselectedTextColor = unselectedTextColor,
        separatorColor = separatorColor
    )
}

object ModernShapes {
    val small = RoundedCornerShape(6.dp)
    val medium = RoundedCornerShape(8.dp)
    val large = RoundedCornerShape(12.dp)
}

object ModernTypography {
    val segmentSelected = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )
    val segmentUnselected = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )
}