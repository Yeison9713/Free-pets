package co.poligran.viralpaws.data.models

data class Veterinary(
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    val phone: String,
    val email: String,
    val website: String,
    val rating: Float,
    val imageUrl: String,
    val description: String,
    val services: List<String>,
    val openingHours: Map<String, String>,
    val isOpen24Hours: Boolean = false,
    val isEmergency: Boolean = false
) 