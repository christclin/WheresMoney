package com.gogolook.wheresmoney.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ModifierPractice() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .padding(20.dp)
            .clip(shape = RoundedCornerShape(32.dp))
            .background(Color.Red)
            .padding(20.dp)
            .background(Color.Green)
            .clip(shape = CutCornerShape(20.dp))
            .background(Color.Yellow),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(shape = RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(16.dp),
        ) {
            Text("Hello 1")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Hello 2")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Hello 3")
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.Cyan)
                    .padding(12.dp),
            ) {
                Text("Hello 4")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Hello 5")
            }

            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(Color.LightGray)
                    ,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(100) {
                    Text("Hello $it")
                }
            }
        }
    }
}

@Preview
@Composable
fun ClipVsBackgroundShape() {

    Column {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(32.dp))
                .background(Color.White)
                .padding(2.dp),
        ) {
            Text(
                modifier = Modifier
                    .background(Color.Cyan),
                text = "Clip",
                fontSize = 64.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(2.dp),
        ) {
            Text(
                modifier = Modifier
                    .background(Color.Cyan),
                text = "Background Shape",
                fontSize = 64.sp
            )
        }
    }
}