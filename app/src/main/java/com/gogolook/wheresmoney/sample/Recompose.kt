package com.gogolook.wheresmoney.sample

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay

@Composable
fun RecomposePracticeView() {
    val a = remember { mutableStateOf(0) }
    val b = remember { mutableStateOf(0) }

    log("RecomposePracticeView before dump")
    DumpAB_1(a.value, b.value)
//    DumpAB_2({ a.value }, { b.value })
    log("RecomposePracticeView after dump")

    LaunchedEffect(key1 = Unit) {
        delay(1000)
        a.value = 1
        delay(1000)
        b.value = 2
    }
}

@Composable
fun DumpAB_1(a: Int, b: Int) {
    log("DumpAB: a = $a, b = $b")
}

@Composable
fun DumpAB_2(a: () -> Int, b: () -> Int) {
    log("DumpAB: a = ${a()}, b = ${b()}")
}

fun log(msg: String) {
    Log.d("CHRISS", msg)
}