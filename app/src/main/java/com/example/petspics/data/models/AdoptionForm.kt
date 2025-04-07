package com.example.petspics.data.models

data class AdoptionForm(
    val petId: String,
    val applicantName: String = "",
    val applicantEmail: String = "",
    val applicantPhone: String = "",
    val applicantAddress: String = "",
    val applicantCity: String = "",
    val applicantOccupation: String = "",
    val applicantIncome: String = "",
    val hasOtherPets: Boolean = false,
    val otherPetsDescription: String = "",
    val hasChildren: Boolean = false,
    val childrenAges: String = "",
    val hasYard: Boolean = false,
    val yardSize: String = "",
    val hasExperienceWithPets: Boolean = false,
    val experienceDescription: String = "",
    val hoursAlone: String = "",
    val adoptionReason: String = "",
    val additionalInfo: String = "",
    val status: AdoptionStatus = AdoptionStatus.PENDING
)

enum class AdoptionStatus {
    PENDING,
    REVIEWING,
    APPROVED,
    REJECTED,
    COMPLETED
}

fun getAdoptionStatusName(status: AdoptionStatus): String {
    return when (status) {
        AdoptionStatus.PENDING -> "Pendiente"
        AdoptionStatus.REVIEWING -> "En revisión"
        AdoptionStatus.APPROVED -> "Aprobada"
        AdoptionStatus.REJECTED -> "Rechazada"
        AdoptionStatus.COMPLETED -> "Completada"
    }
} 