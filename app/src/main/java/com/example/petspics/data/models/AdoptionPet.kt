package com.example.petspics.data.models

import androidx.compose.ui.graphics.Color

enum class PetType {
    DOG, CAT, BIRD, RABBIT, HAMSTER, OTHER
}

enum class PetGender {
    MALE, FEMALE, UNKNOWN
}

enum class PetAge {
    PUPPY, YOUNG, ADULT, SENIOR
}

enum class PetSize {
    SMALL, MEDIUM, LARGE, XLARGE
}

data class AdoptionPet(
    val id: String,
    val name: String,
    val type: PetType,
    val breed: String,
    val age: PetAge,
    val gender: PetGender,
    val size: PetSize,
    val description: String,
    val imageUrl: String,
    val location: String,
    val shelterName: String,
    val shelterImageUrl: String,
    val shelterRating: Float,
    val shelterReviews: Int,
    val isVaccinated: Boolean,
    val isNeutered: Boolean,
    val isMicrochipped: Boolean,
    val isHouseTrained: Boolean,
    val isGoodWithKids: Boolean,
    val isGoodWithDogs: Boolean,
    val isGoodWithCats: Boolean,
    val adoptionFee: String? = null
)

fun getPetTypeName(type: PetType): String {
    return when (type) {
        PetType.DOG -> "Perro"
        PetType.CAT -> "Gato"
        PetType.BIRD -> "Ave"
        PetType.RABBIT -> "Conejo"
        PetType.HAMSTER -> "Hámster"
        PetType.OTHER -> "Otro"
    }
}

fun getPetGenderName(gender: PetGender): String {
    return when (gender) {
        PetGender.MALE -> "Macho"
        PetGender.FEMALE -> "Hembra"
        PetGender.UNKNOWN -> "Desconocido"
    }
}

fun getPetAgeName(age: PetAge): String {
    return when (age) {
        PetAge.PUPPY -> "Cachorro"
        PetAge.YOUNG -> "Joven"
        PetAge.ADULT -> "Adulto"
        PetAge.SENIOR -> "Senior"
    }
}

fun getPetSizeName(size: PetSize): String {
    return when (size) {
        PetSize.SMALL -> "Pequeño"
        PetSize.MEDIUM -> "Mediano"
        PetSize.LARGE -> "Grande"
        PetSize.XLARGE -> "Extra Grande"
    }
} 