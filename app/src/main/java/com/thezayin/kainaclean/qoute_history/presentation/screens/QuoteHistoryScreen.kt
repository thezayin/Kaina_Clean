package com.thezayin.kainaclean.qoute_history.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.kainaclean.R
import com.thezayin.kainaclean.destinations.QuoteDetailsScreenDestination
import com.thezayin.kainaclean.main.component.TopBar
import com.thezayin.kainaclean.main.component.dialogs.LoadingDialog
import com.thezayin.kainaclean.main.component.dialogs.NetworkDialog
import com.thezayin.kainaclean.qoute_history.presentation.screens.component.QuoteCard
import com.thezayin.kainaclean.qoute_history.presentation.viewmodel.QuoteHistoryViewModel
import com.thezayin.kainaclean.util.Response
import com.thezayin.kainaclean.util.Toast
import com.thezayin.kainaclean.util.checkForInternet
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@Destination
@Composable
fun QuoteHistoryScreen(
    navigator: DestinationsNavigator
) {
    val quoteViewModel: QuoteHistoryViewModel = hiltViewModel()
    val quoteUiState = quoteViewModel.getAllQuotes
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val showProgressBar = remember { mutableStateOf(false) }
    var checkNetwork by remember { mutableStateOf(false) }
    if (!checkForInternet(context)) {
        checkNetwork = true
    }

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (showProgressBar.value) {
        LoadingDialog()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .background(color = colorResource(id = R.color.background))
                .fillMaxSize()

        ) {
            TopBar(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                title = "Quote History"
            ) {
                navigator.navigateUp()
            }
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .background(color = colorResource(id = R.color.background))
            ) {
                when (quoteUiState.quoteList) {
                    is Response.Failure -> {
                        showProgressBar.value = false
                        Toast(message = "UnExpected error please try again")
                    }

                    Response.Loading -> {
                        showProgressBar.value = true
                    }

                    is Response.Success -> {
                        showProgressBar.value = false
                        LazyColumn {
                            items(quoteUiState.quoteList.data.sortedBy { it.currentDate!!.toIntOrNull() }) { quote ->
                                QuoteCard(quote = quote) {
                                    scope.launch {
                                        navigator.navigate(
                                            QuoteDetailsScreenDestination(quote.quoteId!!)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

