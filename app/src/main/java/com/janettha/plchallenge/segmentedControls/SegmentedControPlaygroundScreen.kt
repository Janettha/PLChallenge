package com.janettha.plchallenge.segmentedControls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedControlPlaygroundScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    // Define modern theme colors
    val backgroundColor = Color(0xFFF8FAFC)
    val cardBackground = Color.White
    val dividerColor = Color(0xFFE2E8F0)
    val primaryTextColor = Color(0xFF0F172A)
    val secondaryTextColor = Color(0xFF64748B)
    val accentColor = Color(0xFF2563EB)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Segmented Control",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            letterSpacing = 0.sp,
                            color = primaryTextColor
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = primaryTextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = primaryTextColor,
                    navigationIconContentColor = primaryTextColor
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Section 1: Basic Examples
            PlaygroundSection(
                title = "Basic Examples",
                cardBackground = cardBackground,
                textColor = primaryTextColor,
                dividerColor = dividerColor
            ) {
                // Two segments example
                TwoSegmentsExample(secondaryTextColor)

                // Three segments example
                ThreeSegmentsExample(secondaryTextColor)

                // Four segments example
                FourSegmentsExample(secondaryTextColor)
            }

            // Section 2: Separator Options
            PlaygroundSection(
                title = "Separator Options",
                cardBackground = cardBackground,
                textColor = primaryTextColor,
                dividerColor = dividerColor
            ) {
                // With separators
                WithSeparatorsExample(secondaryTextColor)

                // Without separators
                WithoutSeparatorsExample(secondaryTextColor)
            }

            // Section 3: Color Variations
            PlaygroundSection(
                title = "Color Variations",
                cardBackground = cardBackground,
                textColor = primaryTextColor,
                dividerColor = dividerColor
            ) {
                // Default color scheme
                DefaultColorExample(secondaryTextColor)

                // Custom color scheme
                CustomColorExample(secondaryTextColor)
            }

            // Section 4: Interactive Example with Content
            PlaygroundSection(
                title = "Interactive Example",
                cardBackground = cardBackground,
                textColor = primaryTextColor,
                dividerColor = dividerColor
            ) {
                InteractiveSegmentedControlExample(
                    backgroundColor = Color(0xFFF1F5F9),
                    textColor = primaryTextColor
                )
            }
        }
    }
}

/**
 * Basic example with two segments
 */
@Composable
private fun TwoSegmentsExample(textColor: Color) {
    val items = listOf("Day", "Month")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Text(
        text = "Two Segments",
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            color = textColor
        )
    )

    ModernSegmentedControl(
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        showSeparators = true,
        modifier = Modifier.fillMaxWidth()
    )
}

/**
 * Three segments example
 */
@Composable
private fun ThreeSegmentsExample(textColor: Color) {
    val items = listOf("Day", "Week", "Month")
    var selectedIndex by remember { mutableIntStateOf(1) }

    Text(
        text = "Three Segments",
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),
        modifier = Modifier.padding(top = 16.dp)
    )

    ModernSegmentedControl(
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        showSeparators = true,
        modifier = Modifier.fillMaxWidth()
    )
}

/**
 * Four segments example
 */
@Composable
private fun FourSegmentsExample(textColor: Color) {
    val items = listOf("Day", "Week", "Month", "Year")
    var selectedIndex by remember { mutableIntStateOf(2) }

    Text(
        text = "Four Segments",
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),
        modifier = Modifier.padding(top = 16.dp)
    )

    ModernSegmentedControl(
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        showSeparators = true,
        modifier = Modifier.fillMaxWidth()
    )
}

/**
 * Example with separators
 */
@Composable
private fun WithSeparatorsExample(textColor: Color) {
    val items = listOf("Option 1", "Option 2", "Option 3")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Text(
        text = "With Separators",
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            color = textColor
        )
    )

    ModernSegmentedControl(
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        showSeparators = true,
        modifier = Modifier.fillMaxWidth()
    )
}

/**
 * Example without separators
 */
@Composable
private fun WithoutSeparatorsExample(textColor: Color) {
    val items = listOf("Option 1", "Option 2", "Option 3")
    var selectedIndex by remember { mutableIntStateOf(1) }

    Text(
        text = "Without Separators",
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),
        modifier = Modifier.padding(top = 16.dp)
    )

    ModernSegmentedControl(
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        showSeparators = false,
        modifier = Modifier.fillMaxWidth()
    )
}

/**
 * Default color example
 */
@Composable
private fun DefaultColorExample(textColor: Color) {
    val items = listOf("Default", "Colors")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Text(
        text = "Default Color Scheme",
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            color = textColor
        )
    )

    ModernSegmentedControl(
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        showSeparators = true,
        modifier = Modifier.fillMaxWidth()
    )
}

/**
 * Custom color example
 */
@Composable
private fun CustomColorExample(textColor: Color) {
    val items = listOf("Custom", "Colors")
    var selectedIndex by remember { mutableIntStateOf(1) }

    Text(
        text = "Custom Color Scheme",
        style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
            color = textColor
        ),
        modifier = Modifier.padding(top = 16.dp)
    )

    // Teal color scheme
    val tealColors = SegmentedControlColors(
        containerBackground = Color(0xFFE0F2F1),
        selectedBackground = Color(0xFF009688),
        selectedTextColor = Color.White,
        unselectedTextColor = Color(0xFF00796B),
        separatorColor = Color(0xFFB2DFDB)
    )

    ModernSegmentedControl(
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        showSeparators = true,
        colors = tealColors,
        modifier = Modifier.fillMaxWidth()
    )
}

/**
 * Interactive segmented control example with content changing based on selection
 */
@Composable
private fun InteractiveSegmentedControlExample(
    backgroundColor: Color,
    textColor: Color
) {
    val items = listOf("Daily", "Weekly", "Monthly")
    var selectedIndex by remember { mutableIntStateOf(0) }

    ModernSegmentedControl(
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { selectedIndex = it },
        showSeparators = true,
        modifier = Modifier.fillMaxWidth()
    )

    // Display content based on selected segment
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        when (selectedIndex) {
            0 -> Text(
                text = "Showing daily view. This displays data at a daily granularity, showing fluctuations throughout each day.",
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.15.sp,
                    color = textColor
                )
            )

            1 -> Text(
                text = "Showing weekly view. This aggregates data by week, which is useful for identifying trends over medium timeframes.",
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.15.sp,
                    color = textColor
                )
            )

            2 -> Text(
                text = "Showing monthly view. This view provides a high-level overview of long-term trends across months.",
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.15.sp,
                    color = textColor
                )
            )

            else -> Text(
                text = "No view selected",
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    letterSpacing = 0.15.sp,
                    color = textColor.copy(alpha = 0.6f)
                )
            )
        }
    }
}

@Composable
private fun PlaygroundSection(
    title: String,
    cardBackground: Color,
    textColor: Color,
    dividerColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = cardBackground
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    letterSpacing = 0.sp,
                    color = textColor
                )
            )

            HorizontalDivider(color = dividerColor)

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                content()
            }
        }
    }
}

@Preview(
    showBackground = false
)
@Composable
fun SegmentedControlPlaygroundScreenPreview(){
    MaterialTheme {
        SegmentedControlPlaygroundScreen(onBack = {})
    }
}