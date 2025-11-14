package com.example.signalmatch_frontend.ui.components.dropdown

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight


private val ExposedShape = RoundedCornerShape(14.dp)
private val ExposedColors @Composable get() = TextFieldDefaults.colors(
    unfocusedContainerColor = Color.Transparent,
    focusedContainerColor = Color.Transparent,
    unfocusedIndicatorColor = Color(0xFFE5E5E5),
    focusedIndicatorColor = Color(0xFFADF1EB)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileExposedDropdown(
    label: String,
    selected: String?,
    options: List<String>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selected.orEmpty(),
            onValueChange = {},
            placeholder = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            shape = ExposedShape,
            colors = ExposedColors
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { opt ->
                DropdownMenuItem(
                    text = { Text(opt, maxLines = Int.MAX_VALUE, softWrap = true) },
                    onClick = {
                        onSelect(opt)
                        expanded = false
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileExposedDropdownMulti(
    label: String,
    selected: Set<String>,
    options: List<String>,
    onToggle: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxSummaryItems: Int = 1
) {
    var expanded by remember { mutableStateOf(false) }
    val summary = remember(selected, maxSummaryItems) {
        when {
            selected.isEmpty() -> ""
            selected.size <= maxSummaryItems -> selected.joinToString(", ")
            else -> selected.take(maxSummaryItems).joinToString(", ") + " 외 ${selected.size - maxSummaryItems}개"
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = summary,
            onValueChange = {},
            placeholder = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            shape = ExposedShape,
            colors = ExposedColors
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.heightIn(max = 250.dp)
        ) {
            options.forEach { opt ->
                DropdownMenuItem(
                    text = {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Checkbox(
                                checked = selected.contains(opt),
                                onCheckedChange = null
                            )
                            Text(opt)
                        }
                    },
                    onClick = { onToggle(opt) },
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}
