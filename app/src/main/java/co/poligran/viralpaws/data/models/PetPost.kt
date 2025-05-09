package co.poligran.viralpaws.data.models

data class PetPost(
    val id: String,
    val petName: String,
    val animal: String,
    val ownerName: String,
    val description: String,
    val imageUrl: String,
    val likes: Int = 0,
    val comments: List<Comment> = emptyList(),
    val youtubeShortUrl: String
)

data class Comment(
    val id: String,
    val userName: String,
    val text: String,
    val timestamp: String
) 