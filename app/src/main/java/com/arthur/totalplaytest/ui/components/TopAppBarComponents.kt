package com.arthur.totalplaytest.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
internal fun TitledTopAppBar(
    iconImage: ImageVector = Icons.Filled.ArrowBack,
    iconContentDescription: String? = null,
    iconColor: Color = MaterialTheme.colors.primary,
    iconAction: () -> Unit,
    titleId: Int? = null,
    titleString: String? = null,
    backgroundColor: Color = MaterialTheme.colors.background,
    textColor: Color = MaterialTheme.colors.primary
) {
    Surface(elevation = 8.dp) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(color = backgroundColor)

        ) {
            Box(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = iconImage,
                        tint = iconColor,
                        contentDescription = iconContentDescription,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { iconAction() }
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 50.dp),
                ) {
                    Text(
                        text = titleString ?: stringResource(id = titleId!!) ,
                        color = textColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.h6,
                        maxLines = 2,
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}