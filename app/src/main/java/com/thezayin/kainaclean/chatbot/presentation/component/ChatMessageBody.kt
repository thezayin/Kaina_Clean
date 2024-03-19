package com.thezayin.kainaclean.chatbot.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thezayin.kainaclean.R
import com.thezayin.kainaclean.chatbot.domain.model.BotMessage

@Composable
fun ChatMessageBody(botMessage: BotMessage) {
    Box(
        modifier = Modifier.fillMaxWidth()

    ) {
        Card(
            colors = CardDefaults.cardColors(
                if (botMessage.sender.equals("bot")) colorResource(id = R.color.ed_background) else colorResource(
                    id = R.color.btn_primary
                ),
            ),
            modifier = Modifier
                .padding(10.dp)
                .wrapContentWidth()
                .widthIn(min = 50.dp, max = 300.dp)
                .align(if (botMessage.sender.equals("bot")) Alignment.CenterStart else Alignment.CenterEnd),
            shape = RoundedCornerShape(
                topStart = if (botMessage.sender.equals("bot")) 0.dp else 24.dp,
                topEnd = 24.dp,
                bottomEnd = if (botMessage.sender.equals("bot")) 24.dp else 0.dp,
                bottomStart = 24.dp,
            ),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Box(
                modifier = Modifier, contentAlignment = Alignment.Center
            ) {
                Text(
                    text = botMessage.cnt,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    color = if (botMessage.sender.equals("bot")) Color.Black else Color.White,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
        }
    }
}

