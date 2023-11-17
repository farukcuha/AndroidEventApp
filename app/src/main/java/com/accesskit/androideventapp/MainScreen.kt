package com.accesskit.androideventapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = AndroidEventConfig.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }, actions = {
                Image(
                    painter = painterResource(id = R.drawable.gdsc),
                    contentDescription = "",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 8.dp)
                )
            })
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            item {
                AsyncImage(
                    model = AndroidEventConfig.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            item { ParticipantTitleView("Eğitmenler", modifier = Modifier.padding(8.dp)) }
            items(AndroidEventConfig.trainers.size) { index ->
                Spacer(modifier = Modifier.height(8.dp))
                ParticipantView(
                    participant = AndroidEventConfig.trainers[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 8.dp, end = 8.dp),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    navController.navigate("detail/${URLEncoder.encode(it.imageUrl, StandardCharsets.UTF_8.toString())}/${it.name} ${it.surname}")
                }
            }
            item { ParticipantTitleView("Katılımcılar", modifier = Modifier.padding(8.dp)) }
            items(AndroidEventConfig.participants.size) { index ->
                Spacer(modifier = Modifier.height(8.dp))
                ParticipantView(
                    participant = AndroidEventConfig.participants[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 8.dp, end = 8.dp),
                    color = when(index % 4) {
                        0 -> Color(0xFF4285F4)
                        1 -> Color(0xFFDB4437)
                        2 -> Color(0xFFF4B400)
                        3 -> Color(0xFF0F9D58)
                        else -> MaterialTheme.colorScheme.primary
                    }
                ) {
                    navController.navigate("detail/${URLEncoder.encode(it.imageUrl, StandardCharsets.UTF_8.toString())}/${it.name} ${it.surname}")
                }
            }
            item {  Spacer(modifier = Modifier.height(8.dp)) }
            item { Text(
                text = "🗓️ 17 Kasım 2023 🗓️",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            ) }
            item { Text(
                text = "Düzenlediğimiz 3 günlük Android-Kotlin eğitimine katılan tüm arkadaşlarımıza teşekkürler!",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )}
            item { Text(
                text = "\uD83E\uDD88",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )}
            item {  Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@Composable
fun ParticipantTitleView(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParticipantView(
    participant: Participant,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: (Participant) -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
        onClick = {
            onClick(participant)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = participant.profileImageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            bottomStart = 12.dp
                        )
                    )
                    .size(64.dp)
            )
            Text(
                text = "${participant.name} ${participant.surname}",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
                    .padding(start = 8.dp),
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(
                text = participant.emoji,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .padding(end = 8.dp),
                textAlign = TextAlign.End,
                fontSize = 24.sp,
            )
        }
    }
}