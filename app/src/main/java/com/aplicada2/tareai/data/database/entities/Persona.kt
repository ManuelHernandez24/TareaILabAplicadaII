package com.aplicada2.tareai.data.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tabla_persona")
data class Persona (
    @PrimaryKey(autoGenerate = true)
    val PersonaId: Int,
    val Nombres: String,
    val Email: String,
    val OcupacionId: Int,
    val Balance: Double
):Parcelable