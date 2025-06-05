package com.janettha.plchallenge.segmentedControls

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnalyticsDashboard() {
    val timeRanges = listOf("Day", "Week", "Month", "Year")
    var selectedRange by remember { mutableIntStateOf(1) } // Default to weekly

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Performance Overview", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        ModernSegmentedControl(
            items = timeRanges,
            selectedIndex = selectedRange,
            onItemSelected = { selectedRange = it },
            modifier = Modifier.fillMaxWidth()
        )

        // Content changes based on selected time range
        when (selectedRange) {
            0 -> CustomView("DailyChart")
            1 -> CustomView("WeeklyChart")
            2 -> CustomView("MonthlyChart")
            3 -> CustomView("YearlyChart")
        }
    }
}

@Composable
fun PhotoGallery() {
    val viewTypes = listOf("Grid", "List", "Carousel")
    var selectedViewType by remember { mutableIntStateOf(0) } // Default to grid

    Column {
        ModernSegmentedControl(
            items = viewTypes,
            selectedIndex = selectedViewType,
            onItemSelected = { selectedViewType = it },
            modifier = Modifier.fillMaxWidth()
        )

        // Layout changes based on selected view type
        when (selectedViewType) {
            0 -> CustomView("PhotoGrid")
            1 -> CustomView("PhotoList")
            2 -> CustomView("PhotoCarousel")
        }
    }
}

@Composable
fun TaskListScreen() {
    val filterOptions = listOf("All", "Active", "Completed")
    var selectedFilter by remember { mutableIntStateOf(0) }

    Column {
        ModernSegmentedControl(
            items = filterOptions,
            selectedIndex = selectedFilter,
            onItemSelected = { selectedFilter = it },
            modifier = Modifier.fillMaxWidth()
        )

        // Filter content based on selection
        val filteredTasks = when (selectedFilter) {
            0 -> CustomView("allTasks")
            1 -> CustomView("allTasks.filter { it.isActive }")
            2 -> CustomView("allTasks.filter { it.isCompleted }")
            else -> CustomView("allTasks")
        }

        //TaskList(tasks = filteredTasks)
    }
}

@Composable
private fun CustomView(lapsTitle: String) {
    Text(lapsTitle)
}

@Preview
@Composable
private fun AnalyticsDashboardPreview(){
    MaterialTheme {
        AnalyticsDashboard()
    }
}

@Preview
@Composable
private fun PhotoGalleryPreview(){
    MaterialTheme {
        PhotoGallery()
    }
}

@Preview
@Composable
private fun TaskListScreenPreview(){
    MaterialTheme {
        TaskListScreen()
    }
}
