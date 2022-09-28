package com.example.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        var count by rememberSaveable {
            mutableStateOf(0)
        }
        if (count > 0) {
            Text(
                text = "You've had $count glasses of water. ",
                modifier = modifier.padding(16.dp)
            )
        }
        Button(
            onClick = { count++ },
            enabled = count < 20
        ) {
            Text(text = "Add a glass")
        }
    }
}
// doing state hoisting and splitting the WaterCounter function into 2 functions
// a stateful function(StatefulCounter) that owns a state and can change the state over time
// and a stateless function(StatelessCounter) that does not have any state.
// This split was done following the general pattern for state hoisting
// The state is replaced with 2 parameters :
// - value: T => the current value to display
// - onValueChange: (T) -> Unit => an event that requests the value to change, where
// T is the proposed new value.
@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
   Column(modifier = modifier.padding(16.dp)) {
       if (count > 0) {
           Text("You've had $count glasses of water today. ")
       }
       Button(
           onClick = onIncrement,
           modifier = Modifier.padding(top = 8.dp),
           enabled = count < 10
       ) {
           Text("Add one")
       }
   }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier){
    var count by rememberSaveable() {
        mutableStateOf(0)
    }
    StatelessCounter(count, onIncrement = { count++ }, modifier)
}
