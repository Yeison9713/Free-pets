package co.poligran.viralpaws.data.models

data class User(
    val id: String,
    val username: String,
    val email: String,
    val password: String,
    val profilePicture: String = "",
    val pets: List<Pet> = emptyList()
)

data class Pet(
    val id: String,
    val name: String,
    val type: String,
    val breed: String,
    val age: Int,
    val photo: String,
    val photos: List<String> = emptyList()
) 