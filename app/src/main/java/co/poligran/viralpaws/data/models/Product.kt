package co.poligran.viralpaws.data.models

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: ProductCategory,
    val rating: Float = 0f,
    val reviews: Int = 0
)

enum class ProductCategory {
    FOOD,
    TOYS,
    ACCESSORIES,
    HEALTH,
    GROOMING
} 