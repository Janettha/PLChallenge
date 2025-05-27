package com.janettha.plchallenge.ThousandsSeparatorPicker.presenter


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.janettha.plchallenge.R
import com.janettha.plchallenge.ui.theme.PLChallengeTheme
import kotlin.math.roundToInt

// Get access to Google fonts 'Figtree'
private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
private val fontName = GoogleFont("Figtree")

@Composable
fun ThousandsSeparatorPicker(
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Thousands separator",
            color = Color(0xFF1B1B1C),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(
                    Font(googleFont = fontName, fontProvider = provider)
                ),
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.align(alignment = Alignment.Start)
        )
        SingleChoiceSegmentedBtn(
            items = listOf("1.000", "1,000", "1 000"),
            onItemSelection = {}
        )
    }
}

@Composable
private fun SingleChoiceSegmentedBtn(
    modifier: Modifier = Modifier,
    items: List<String>,
    defaultSelectedItemIndex: Int = 0,
    onItemSelection: (selectedItemIndex: Int) -> Unit
) {
    val selectedIndex = remember { mutableIntStateOf(defaultSelectedItemIndex) }
    val itemIndex = remember { mutableIntStateOf(defaultSelectedItemIndex) }

    /* region animation */
    val density = LocalDensity.current
    val animationDuration = 200 // Duration for slide animation in (ms)

    // Segment measurements
    val segmentPositions = remember { mutableStateMapOf<Int, Float>() }
    val segmentWidths = remember { mutableStateMapOf<Int, Float>() }

    // Track if measurements are complete
    var measurementsReady by remember { mutableStateOf(false) }

    // Track if the component was clicked
    var hasUserInteracted by remember { mutableStateOf(false) }

    // Get selected item info
    val selectedPosition = segmentPositions[selectedIndex.intValue] ?: 0f
    val selectedWidth = segmentWidths[selectedIndex.intValue] ?: 0f

    // Verify when all measurements are complete
    LaunchedEffect(segmentPositions, segmentWidths) {
        if (items.indices.all {
            segmentPositions.containsKey(it) && segmentWidths.containsKey(it)
            } ) {
                measurementsReady = true
            }
    }

    // Position and width - only animate after user interaction
    val animatedPosition by animateFloatAsState(
        targetValue = selectedPosition,
        animationSpec = if (hasUserInteracted) {
            tween(durationMillis = animationDuration)
        } else {
            snap() // Instant with no animation for initial render
        },
        label = "position",
    )

    val animatedWidth by animateFloatAsState(
        targetValue = selectedWidth,
        animationSpec = if (hasUserInteracted) {
            tween(durationMillis = animationDuration)
        } else {
            snap() // Instant with no animation for initial render
        },
        label = "width"
    )
    /* endregion */

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(38.dp)
            .background(
                Color(0x148138FF),
                RoundedCornerShape(8.dp)
            ),
    ) {
        // Selector box-card
        if (measurementsReady) {
            Box(
                modifier = Modifier
                    .offset {
                        // Convert Dp to pixels with proper rounding for accurate positioning
                        val offsetX = with(density) {
                            animatedPosition
                                .toDp()
                                .toPx()
                        }
                        IntOffset(offsetX.roundToInt(), 0)
                    }
                    .width(with(density) { animatedWidth.toDp() })
                    .height(40.dp)
                    .padding(4.dp)
                    .background(
                        Color(0xFFFFFFFF),
                        RoundedCornerShape(8.dp)
                    )
                    .zIndex(1f)
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .zIndex(2f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, label ->
                val isSelected = index == selectedIndex.intValue
                val weight = 1f / items.size

                // TextColor change according to click selection
                val textColor by animateColorAsState(
                    targetValue = if(isSelected) Color(0xFF24005A) else Color(0xFF1B1B1C),
                    animationSpec = if (hasUserInteracted) {
                        tween(durationMillis = animationDuration)
                    } else {
                        snap() // Instant with no animation for initial render
                    },
                    label = "textColor"
                )

                // Segment button (no ripple effect)
                val interactionSource = remember { MutableInteractionSource() }

                itemIndex.intValue = index

                Box(
                    modifier = modifier
                        .weight(weight)
                        .height(40.dp)
                        .onGloballyPositioned { coordinates ->
                            // Store position and width for animations
                            segmentPositions[index] = coordinates.positionInParent().x
                            segmentWidths[index] = coordinates.size.width.toFloat()
                        }
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            hasUserInteracted = true
                            selectedIndex.intValue = index
                            onItemSelection(index)
                        }
                        .background(Color.Transparent)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = label,
                        color = textColor,
                        style = LocalTextStyle.current.copy(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(
                                Font(googleFont = fontName, fontProvider = provider)
                            ),
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewThousandsSeparatorPicker() {
    PLChallengeTheme {
        ThousandsSeparatorPicker(
            modifier = Modifier
        )
    }
}
