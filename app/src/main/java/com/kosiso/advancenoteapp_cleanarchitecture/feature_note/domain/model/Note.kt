package com.kosiso.advancenoteapp_cleanarchitecture.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kosiso.advancenoteapp_cleanarchitecture.ui.theme.BabyBlue
import com.kosiso.advancenoteapp_cleanarchitecture.ui.theme.LightBlue
import com.kosiso.advancenoteapp_cleanarchitecture.ui.theme.LightGreen
import com.kosiso.advancenoteapp_cleanarchitecture.ui.theme.RedOrange
import com.kosiso.advancenoteapp_cleanarchitecture.ui.theme.RedPink
import com.kosiso.advancenoteapp_cleanarchitecture.ui.theme.Violet

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
){
    companion object{
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
