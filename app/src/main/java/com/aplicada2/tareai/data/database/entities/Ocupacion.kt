package com.aplicada2.tareai.data.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tabla_ocupacion")
data class Ocupacion (
    @PrimaryKey(autoGenerate = true)
    val OcupacionId: Int,
    val Nombre: String
):Parcelable