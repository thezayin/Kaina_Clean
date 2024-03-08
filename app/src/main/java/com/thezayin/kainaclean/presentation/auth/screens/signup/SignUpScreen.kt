package com.thezayin.kainaclean.presentation.auth.screens.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.kainaclean.R
import com.thezayin.kainaclean.presentation.auth.AuthViewModel
import com.thezayin.kainaclean.presentation.destinations.HomeScreenDestination
import com.thezayin.kainaclean.util.Response.Failure
import com.thezayin.kainaclean.util.Response.Loading
import com.thezayin.kainaclean.util.Response.Success
import com.thezayin.kainaclean.util.Utils


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SignUpScreen(navigator: DestinationsNavigator) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val emailInputValue = remember { mutableStateOf("") }
    val passwordNumberInputValue = remember { mutableStateOf("") }
    val rePasswordValue = remember { mutableStateOf("") }

    val context = LocalContext.current

    val isBottomSheetShow = rememberSaveable {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState()
    if (isBottomSheetShow.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isBottomSheetShow.value = false
            },
            dragHandle = null,
            containerColor = colorResource(id = R.color.background),
            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 40.dp),
            shape = RoundedCornerShape(30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.background))
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(70.dp),
                        color = colorResource(id = R.color.btn_primary)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Loading",
                        fontSize = 22.sp,
                        color = colorResource(id = R.color.text_color),
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp)
                    )
                }
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 20.dp)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .height(25.dp)
                    .width(25.dp)
                    .align(Alignment.Top)
                    .clickable {
                        navigator.navigateUp()
                    })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_icon),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Email",
                color = colorResource(id = R.color.black),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            )

            OutlinedTextField(
                value = emailInputValue.value,
                onValueChange = { emailInputValue.value = it },
                placeholder = {
                    Text(
                        text = "Enter your Email",
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_mail),
                        contentDescription = null,
                        tint = colorResource(id = R.color.black)
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.transparent),
                    unfocusedContainerColor = colorResource(id = R.color.transparent),
                    disabledLabelColor = colorResource(id = R.color.red),
                    focusedBorderColor = colorResource(id = R.color.black),
                    focusedTextColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black),
                )
            )

            Text(
                text = "Password",
                color = colorResource(id = R.color.black),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)

            )

            OutlinedTextField(
                value = passwordNumberInputValue.value,
                onValueChange = { passwordNumberInputValue.value = it },
                placeholder = {
                    Text(
                        text = "Enter your Password",
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null,
                        tint = colorResource(id = R.color.black)
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.transparent),
                    unfocusedContainerColor = colorResource(id = R.color.transparent),
                    disabledLabelColor = colorResource(id = R.color.red),
                    focusedBorderColor = colorResource(id = R.color.black),
                    focusedTextColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black),
                )
            )

            Text(
                text = "Confirm Password",
                color = colorResource(id = R.color.black),
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 5.dp)
            )

            OutlinedTextField(
                value = rePasswordValue.value,
                onValueChange = { rePasswordValue.value = it },
                placeholder = {
                    Text(
                        text = "Re-Enter your Password",
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null,
                        tint = colorResource(id = R.color.black)
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.transparent),
                    unfocusedContainerColor = colorResource(id = R.color.transparent),
                    disabledLabelColor = colorResource(id = R.color.red),
                    focusedBorderColor = colorResource(id = R.color.black),
                    focusedTextColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black),
                )
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp, 20.dp, 40.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = {
                if (emailInputValue.value.isNotEmpty() && passwordNumberInputValue.value.isNotEmpty() && rePasswordValue.value.isNotEmpty()) {
                    if (passwordNumberInputValue.value == rePasswordValue.value) {
                        authViewModel.signUp(
                            emailInputValue.value, passwordNumberInputValue.value
                        )
                    } else {

                        Toast.makeText(context, "Password does not match", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.btn_primary), contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),

            ) {
            Text(
                text = "Sign Up", color = colorResource(id = R.color.white), fontSize = 16.sp
            )

            when (val signUpResponse = authViewModel.signUpState.value) {
                is Loading -> {
                    isBottomSheetShow.value = true
                }

                is Success -> {
                    val isUserSignedUp = signUpResponse.data
                    LaunchedEffect(isUserSignedUp) {
                        isBottomSheetShow.value = false
                        if (isUserSignedUp) {
                            navigator.navigate(HomeScreenDestination)
                        }
                    }
                    isBottomSheetShow.value = false
                }

                is Failure -> signUpResponse.apply {
                    isBottomSheetShow.value = false
                    LaunchedEffect(e) {
                        Utils.print(e)
                    }
                }
            }
        }
    }
}

