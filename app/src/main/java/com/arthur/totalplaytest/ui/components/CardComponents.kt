package com.arthur.totalplaytest.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.arthur.totalplaytest.R
import com.arthur.totalplaytest.data.remote.dto.BankReference

@Composable
fun BankReferenceList(
    bankReferenceList: List<BankReference>,
    reference: (BankReference) -> Unit
) {
    if (bankReferenceList.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.no_registers_issue),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 65.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(bankReferenceList){ bankRef ->
                BankReferenceCard(
                    bankRef = bankRef,
                    reference = reference
                )
            }
        }
    }
}


@Composable
fun BankReferenceCard(
    bankRef: BankReference,
    reference: (BankReference) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { reference(bankRef) },
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        shape = RoundedCornerShape(5),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primaryVariant
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ){
            if(bankRef.images.isNotEmpty() && !bankRef.images.first().url3X3.isNullOrBlank()){
                Image(
                    painter = rememberAsyncImagePainter(bankRef.images.first().url3X3),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = bankRef.bank ?: bankRef.aliasbank ?: "Nombre no disponible",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = bankRef.reference ?: "Referencia no disponible",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}