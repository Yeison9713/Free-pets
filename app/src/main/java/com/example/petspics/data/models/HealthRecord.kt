package com.example.petspics.data.models

import java.util.Date

data class HealthRecord(
    val id: String,
    val petId: String,
    val type: RecordType,
    val date: Date,
    val description: String,
    val attachments: List<String> = emptyList()
)

enum class RecordType {
    VACCINE,
    MEDICAL_CHECKUP,
    BEHAVIOR_NOTE,
    MEDICATION,
    SURGERY,
    OTHER
}

data class PetBehavior(
    val id: String,
    val petId: String,
    val category: BehaviorCategory,
    val description: String,
    val date: Date,
    val notes: String = ""
)

enum class BehaviorCategory {
    TRAINING,
    SOCIALIZATION,
    AGGRESSION,
    ANXIETY,
    OBEDIENCE,
    OTHER
}

data class HealthTip(
    val id: String,
    val title: String,
    val description: String,
    val category: TipCategory,
    val imageUrl: String
)

enum class TipCategory {
    NUTRITION,
    EXERCISE,
    HYGIENE,
    PREVENTION,
    BEHAVIOR,
    FIRST_AID
} 