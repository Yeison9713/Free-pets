package co.poligran.viralpaws.data.models

data class Service(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: ServiceCategory,
    val duration: String,
    val rating: Float = 0f,
    val reviews: Int = 0,
    val provider: ServiceProvider
)

data class ServiceProvider(
    val id: String,
    val name: String,
    val imageUrl: String,
    val rating: Float,
    val reviews: Int,
    val location: String
)

enum class ServiceCategory {
    GROOMING,
    VETERINARY,
    TRAINING,
    WALKING,
    DAYCARE,
    BOARDING
} 