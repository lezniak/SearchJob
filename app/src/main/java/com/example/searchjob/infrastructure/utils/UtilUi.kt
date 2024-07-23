package com.example.searchjob.infrastructure.utils

import android.graphics.Color
import android.widget.ImageButton
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.searchjob.R
import com.example.searchjob.infrastructure.navigation.HomeScreen
import com.example.searchjob.infrastructure.navigation.ListScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HeaderAndText(@StringRes header: Int,@StringRes text: Int, isCenterded : Boolean = false ,modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = if(isCenterded) Alignment.CenterHorizontally else Alignment.Start) {
        Text(text = stringResource(id = header), fontSize = 28.sp, fontWeight = Bold, lineHeight = 30.sp, textAlign = if (isCenterded) TextAlign.Center else TextAlign.Unspecified)
        Text(text = stringResource(id = text), fontSize = 16.sp,textAlign = if (isCenterded) TextAlign.Center else TextAlign.Unspecified)
    }
}


@Preview
@Composable
private fun HeaderAndTextPrev() {
    HeaderAndText(header = R.string.welcome_header, text = R.string.welcome_text)
}

@Composable
fun ButtonWithLoader(
    buttonText: String,
    backgroundColor: androidx.compose.ui.graphics.Color,
    contentColor: androidx.compose.ui.graphics.Color = White,
    showLoader: Boolean = false,
    showBorder: Boolean,
    onClick: () -> Unit,
) {
    if (showLoader) {
        Row(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .height(48.dp)
                .clickable { onClick() }
                .border(
                    if (showBorder) 1.dp else 0.dp,
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(50.dp)
                )
                .background(backgroundColor, RoundedCornerShape(50.dp)),
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.surface, modifier = Modifier.size(28.dp))
        }
    } else {
        Row(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .height(48.dp)
                .clickable { onClick() } // motionClickEvent is my custom click modifier, use clickable modifier over here
                .border(
                    if (showBorder) 1.dp else 0.dp,
                    MaterialTheme.colorScheme.primary.copy(0.08f),
                    RoundedCornerShape(50.dp)
                )
                .background(backgroundColor, RoundedCornerShape(50.dp)),
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.bodyMedium,
                color = contentColor,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(title: String) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                IconButton(onClick = { FirebaseAuth.getInstance().signOut() }){
                    Image(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "User Icon",
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )
                }

            }
        },
        actions = {

        }
    )
}

@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigateTo: (String) -> Unit
) {
    val items = listOf(
        HomeScreen,
        ListScreen,
    )

    NavigationBar(
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon!!, contentDescription = null) },
                label = { Text(item.name) },
                selected = currentRoute == item.route,
                onClick = { onNavigateTo(item.route) }
            )
        }
    }
}