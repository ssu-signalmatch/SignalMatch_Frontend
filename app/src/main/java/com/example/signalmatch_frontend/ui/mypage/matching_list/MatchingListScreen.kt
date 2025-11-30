package com.example.signalmatch_frontend.ui.mypage.matching_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signalmatch_frontend.ui.components.card.MypageCard3
import com.example.signalmatch_frontend.ui.components.etc.Logo
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.signalmatch_frontend.data.model.request.MatchReasonCode

@Composable
fun MatchingListScreen(
    navController: NavController,
    uiState: MatchingListUiState,
    onRefresh: () -> Unit,
    onCancelMatch: (Int, MatchReasonCode, String?) -> Unit
) {
    var deleteId by remember { mutableStateOf<Int?>(null) }
    var reasonCode by remember { mutableStateOf(MatchReasonCode.NOT_INTERESTED) }
    var reasonText by remember { mutableStateOf("") }

    if (deleteId != null) {
        AlertDialog(
            onDismissRequest = { deleteId = null },
            title = { Text("매칭 취소") },
            text = {
                Column {
                    Text("취소 사유를 선택해주세요")

                    MatchReasonCode.entries.forEach { code ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { reasonCode = code }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = reasonCode == code,
                                onClick = { reasonCode = code }
                            )
                            Text(text = code.name)
                        }
                    }

                    if (reasonCode == MatchReasonCode.OTHER) {
                        OutlinedTextField(
                            value = reasonText,
                            onValueChange = { reasonText = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("사유를 입력하세요") }
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onCancelMatch(
                        deleteId!!,
                        reasonCode,
                        if (reasonCode == MatchReasonCode.OTHER) reasonText else null
                    )
                    deleteId = null
                    reasonText = ""
                }) {
                    Text("확인", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { deleteId = null }) {
                    Text("취소")
                }
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(63.dp))
        Logo(105.dp)
        Spacer(modifier = Modifier.height(5.dp))
        Text("매칭리스트",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.offset((-140).dp))
        Spacer(modifier = Modifier.height(10.dp))

        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            uiState.match.isEmpty() -> {
                Spacer(modifier = Modifier.height(200.dp))
                Text("매칭된 start-up/investor 가 없습니다.", color = Color.Gray)
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(uiState.match) { item ->
                        MypageCard3(
                            text = item.matchId.toString(),
                            onClick = { deleteId = item.matchId }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun MatchingListPreview(){
    //val navController = rememberNavController()
    //MatchingListScreen( navController, )
}