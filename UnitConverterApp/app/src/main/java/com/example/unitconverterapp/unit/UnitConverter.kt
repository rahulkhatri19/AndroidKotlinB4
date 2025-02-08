package com.example.unitconverterapp.unit

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.math.RoundingMode
import java.math.RoundingMode.*
import java.text.DecimalFormat


@Composable
fun UnitConverter(modifier: Modifier){

    val context = LocalContext.current
    var unitsList by remember { mutableStateOf(Utility.lengthList) }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(Utility.categoryList[0]) }
    var expandFrom by remember { mutableStateOf(false) }
    var expandTo by remember { mutableStateOf(false) }
    var selectedFrom by remember { mutableStateOf(unitsList[0]) }
    var selectedTo by remember { mutableStateOf(unitsList[0]) }

    var inputValue by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }

    Column(
        modifier.padding(16.dp)
    ) {

        Spacer(Modifier.height(56.dp))
        Text(
            "Pick Category",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Row {
            // categoryList
            Utility.categoryList.forEach { list ->
                Card(
                    modifier = Modifier.padding(8.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (list == selectedOption) Color.Yellow else Color.White
                    ),
                    onClick = {
                        onOptionSelected(list)
                        when(list.first){
                            Unit.LENGTH.name -> {
                                unitsList = Utility.lengthList
                            }

                            Unit.AREA.name -> {
                                unitsList = Utility.areaList
                            }

                            Unit.WEIGHT.name -> {
                                unitsList = Utility.weightList
                            }

                            Unit.TIME.name -> {
                                unitsList = Utility.timeList
                            }
                        }

                        selectedTo = unitsList[0]
                        selectedFrom = unitsList[0]
                    }

                ) {
                    Text(
                        text = list.first,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
        Text(
            "Unit Converter",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // convert from
        
        Row {
            Text(
                "Convert From : ",
                modifier.padding(top = 8.dp)
            )

            Box(
                modifier.padding(start = 16.dp)
            ) {
                Button(
                    onClick = {
                        expandFrom = true
                    }
                ) {
                    Text(
                        selectedFrom.unitName
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "Arrow"
                    )
                }

                DropdownMenu(
                    expanded = expandFrom,
                    onDismissRequest = { expandFrom = false },
                    modifier = Modifier.padding( horizontal = 16.dp)
                ) {
                    unitsList.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(it.unitName)
                            },
                            onClick = {
                                selectedFrom = it
                                expandFrom = false
                                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }

        }

        // convert to
        
        Row {
            Text(
                "Convert To : ",
                modifier.padding(top = 8.dp)
            )

            Box(
                modifier.padding(start = 16.dp)
            ) {
                Button(
                    onClick = {
                        expandTo = true
                    }
                ) {
                    Text(
                        selectedTo.unitName
                    )
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "Arrow"
                    )
                }

                DropdownMenu(
                    expanded = expandTo,
                    onDismissRequest = { expandTo = false },
                    modifier = Modifier.padding( horizontal = 16.dp)
                ) {
                    unitsList.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(it.unitName)
                            },
                            onClick = {
                                selectedTo = it
                                expandTo = false
                                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }

        }

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                result = ""
                inputValue = it
            },
            label = {
                Text("Enter Value")
            },
            placeholder = {
                Text("Enter Value")
            },
            modifier = Modifier.padding(18.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                val df = DecimalFormat("#.##")
                df.roundingMode = CEILING
                result = df.format(inputValue.text.toDouble() * selectedTo.unitFactor / selectedFrom.unitFactor)
            },
            enabled = inputValue.text != "",
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
        ) {
            Text(
                "Convert"
            )
        }

        Text(
            "Result : $result",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun demounit(){
    UnitConverter(Modifier)
}






























