package com.example.quizheads.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderText(text: String, modi: Modifier){

    Text(text = text,
        fontSize = 50.sp,
        lineHeight = 50.sp,
        modifier = modi,
        textAlign = TextAlign.Center,

    )

}