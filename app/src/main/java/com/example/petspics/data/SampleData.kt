package com.example.petspics.data

import com.example.petspics.data.models.PetPost
import com.example.petspics.data.models.Comment
import com.example.petspics.data.models.*

class SampleData {
    companion object {
        val samplePosts = listOf(
            PetPost(
                id = "1",
                petName = "Luna",
                ownerName = "María García",
                description = "Luna es una perra muy juguetona y cariñosa. Le encanta jugar con la pelota y dar paseos largos.",
                imageUrl = "https://images.unsplash.com/photo-1548199973-03cce0bbc87b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                likes = 120,
                comments = listOf(
                    Comment(
                        id = "1",
                        userName = "Juan Pérez",
                        text = "¡Qué linda! 😍",
                        timestamp = "2024-03-15T10:30:00Z"
                    ),
                    Comment(
                        id = "2",
                        userName = "Ana López",
                        text = "Me encanta su energía",
                        timestamp = "2024-03-15T11:15:00Z"
                    )
                )
            ),
            PetPost(
                id = "2",
                petName = "Michi",
                ownerName = "Carlos Rodríguez",
                description = "Michi es un gato muy independiente pero cariñoso. Le encanta dormir en lugares altos y observar todo desde arriba.",
                imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                likes = 85,
                comments = listOf(
                    Comment(
                        id = "3",
                        userName = "Laura Martínez",
                        text = "¡Qué ojos más bonitos tiene! 😺",
                        timestamp = "2024-03-15T12:00:00Z"
                    )
                )
            )
        )

        val sampleProducts = listOf(
            Product(
                id = "1",
                name = "Premium Dog Food",
                description = "Alimento premium para perros con ingredientes naturales y balanceados.",
                price = 159900.0,
                imageUrl = "https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                category = ProductCategory.FOOD,
                rating = 4.5f,
                reviews = 128
            ),
            Product(
                id = "2",
                name = "Juguete Interactivo para Gatos",
                description = "Juguete interactivo con luces y sonidos para mantener a tu gato entretenido.",
                price = 89900.0,
                imageUrl = "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                category = ProductCategory.TOYS,
                rating = 4.2f,
                reviews = 85
            ),
            Product(
                id = "3",
                name = "Collar GPS para Mascotas",
                description = "Collar con GPS integrado para localizar a tu mascota en tiempo real.",
                price = 299900.0,
                imageUrl = "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                category = ProductCategory.ACCESSORIES,
                rating = 4.8f,
                reviews = 256
            )
        )

        val sampleServices = listOf(
            Service(
                id = "1",
                name = "Peluquería Canina Profesional",
                description = "Servicio completo de peluquería para perros, incluye baño, corte y cepillado.",
                price = 85000.0,
                imageUrl = "https://images.unsplash.com/photo-1516734212186-a967f81ad0d7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                category = ServiceCategory.GROOMING,
                duration = "2 horas",
                rating = 4.7f,
                reviews = 95,
                provider = ServiceProvider(
                    id = "1",
                    name = "PetStyle Grooming",
                    imageUrl = "https://images.unsplash.com/photo-1601758228041-f3b2795255f1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                    rating = 4.8f,
                    reviews = 312,
                    location = "Calle Principal 123, Bogotá"
                )
            ),
            Service(
                id = "2",
                name = "Consulta Veterinaria",
                description = "Consulta general con veterinario especializado, incluye examen físico y recomendaciones.",
                price = 95000.0,
                imageUrl = "https://images.unsplash.com/photo-1628009368231-7bb7cfcb0def?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                category = ServiceCategory.VETERINARY,
                duration = "30 minutos",
                rating = 4.9f,
                reviews = 150,
                provider = ServiceProvider(
                    id = "2",
                    name = "Centro Veterinario PetCare",
                    imageUrl = "https://images.unsplash.com/photo-1628009368231-7bb7cfcb0def?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                    rating = 4.9f,
                    reviews = 425,
                    location = "Avenida Central 456, Medellín"
                )
            ),
            Service(
                id = "3",
                name = "Entrenamiento Canino",
                description = "Sesiones de entrenamiento personalizado para mejorar el comportamiento de tu perro.",
                price = 120000.0,
                imageUrl = "https://images.unsplash.com/photo-1601758124510-52d02ddb7cbd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                category = ServiceCategory.TRAINING,
                duration = "1 hora",
                rating = 4.6f,
                reviews = 75,
                provider = ServiceProvider(
                    id = "3",
                    name = "DogTraining Pro",
                    imageUrl = "https://images.unsplash.com/photo-1601758124510-52d02ddb7cbd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                    rating = 4.7f,
                    reviews = 167,
                    location = "Plaza Mayor 789, Cali"
                )
            )
        )

        val sampleAdoptionPets = listOf(
            AdoptionPet(
                id = "1",
                name = "Max",
                type = PetType.DOG,
                breed = "Labrador Retriever",
                age = PetAge.YOUNG,
                gender = PetGender.MALE,
                size = PetSize.MEDIUM,
                description = "Max es un Labrador joven muy juguetón y cariñoso. Le encanta jugar con la pelota y es muy bueno con los niños.",
                imageUrl = "https://images.unsplash.com/photo-1548199973-03cce0bbc87b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                location = "Bogotá, Colombia",
                shelterName = "Refugio Animal Bogotá",
                shelterImageUrl = "https://images.unsplash.com/photo-1601758228041-f3b2795255f1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                shelterRating = 4.8f,
                shelterReviews = 156,
                isVaccinated = true,
                isNeutered = true,
                isMicrochipped = true,
                isHouseTrained = true,
                isGoodWithKids = true,
                isGoodWithDogs = true,
                isGoodWithCats = true,
                adoptionFee = "Para adoptar"
            ),
            AdoptionPet(
                id = "2",
                name = "Luna",
                type = PetType.CAT,
                breed = "Siamés",
                age = PetAge.ADULT,
                gender = PetGender.FEMALE,
                size = PetSize.SMALL,
                description = "Luna es una gata siamesa adulta muy tranquila y cariñosa. Es independiente pero disfruta de la compañía humana.",
                imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                location = "Medellín, Colombia",
                shelterName = "Refugio Felino Medellín",
                shelterImageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                shelterRating = 4.6f,
                shelterReviews = 98,
                isVaccinated = true,
                isNeutered = true,
                isMicrochipped = true,
                isHouseTrained = true,
                isGoodWithKids = true,
                isGoodWithDogs = false,
                isGoodWithCats = true,
                adoptionFee = "Para adoptar"
            ),
            AdoptionPet(
                id = "3",
                name = "Rocky",
                type = PetType.DOG,
                breed = "Pitbull",
                age = PetAge.PUPPY,
                gender = PetGender.MALE,
                size = PetSize.MEDIUM,
                description = "Rocky es un cachorro Pitbull muy enérgico y juguetón. Necesita entrenamiento básico y mucho amor.",
                imageUrl = "https://images.unsplash.com/photo-1548199973-03cce0bbc87b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                location = "Cali, Colombia",
                shelterName = "Refugio Canino Cali",
                shelterImageUrl = "https://images.unsplash.com/photo-1601758228041-f3b2795255f1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                shelterRating = 4.7f,
                shelterReviews = 112,
                isVaccinated = true,
                isNeutered = false,
                isMicrochipped = true,
                isHouseTrained = false,
                isGoodWithKids = true,
                isGoodWithDogs = true,
                isGoodWithCats = false,
                adoptionFee = "Para adoptar"
            ),
            AdoptionPet(
                id = "4",
                name = "Pico",
                type = PetType.BIRD,
                breed = "Periquito",
                age = PetAge.ADULT,
                gender = PetGender.MALE,
                size = PetSize.SMALL,
                description = "Pico es un periquito adulto muy sociable y cantarín. Le encanta interactuar con las personas.",
                imageUrl = "https://images.unsplash.com/photo-1552728089-57bdde30beb3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                location = "Barranquilla, Colombia",
                shelterName = "Refugio de Aves Barranquilla",
                shelterImageUrl = "https://images.unsplash.com/photo-1552728089-57bdde30beb3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80",
                shelterRating = 4.5f,
                shelterReviews = 45,
                isVaccinated = true,
                isNeutered = false,
                isMicrochipped = false,
                isHouseTrained = true,
                isGoodWithKids = true,
                isGoodWithDogs = false,
                isGoodWithCats = false,
                adoptionFee = "Para adoptar"
            )
        )

        val sampleHealthTips = listOf(
            HealthTip(
                id = "1",
                title = "Alimentación balanceada",
                description = "Una dieta equilibrada es fundamental para la salud de tu mascota. Asegúrate de proporcionar alimentos de calidad y en las cantidades adecuadas según su edad, tamaño y nivel de actividad.",
                category = TipCategory.NUTRITION,
                imageUrl = "https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
            ),
            HealthTip(
                id = "2",
                title = "Ejercicio regular",
                description = "El ejercicio diario es esencial para mantener a tu mascota saludable y feliz. Adapta la intensidad y duración según la edad y condición física de tu mascota.",
                category = TipCategory.EXERCISE,
                imageUrl = "https://images.unsplash.com/photo-1548199973-03cce0bbc87b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
            ),
            HealthTip(
                id = "3",
                title = "Higiene dental",
                description = "La salud dental es crucial. Cepilla los dientes de tu mascota regularmente y programa limpiezas profesionales cuando sea necesario.",
                category = TipCategory.HYGIENE,
                imageUrl = "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
            ),
            HealthTip(
                id = "4",
                title = "Vacunación al día",
                description = "Mantén el calendario de vacunación de tu mascota actualizado. Las vacunas son esenciales para prevenir enfermedades graves.",
                category = TipCategory.PREVENTION,
                imageUrl = "https://images.unsplash.com/photo-1628009368231-7bb7cfcb0def?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
            ),
            HealthTip(
                id = "5",
                title = "Socialización temprana",
                description = "La socialización temprana es clave para desarrollar un comportamiento equilibrado. Expón a tu mascota a diferentes situaciones, personas y otros animales desde cachorro.",
                category = TipCategory.BEHAVIOR,
                imageUrl = "https://images.unsplash.com/photo-1548199973-03cce0bbc87b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
            ),
            HealthTip(
                id = "6",
                title = "Primeros auxilios básicos",
                description = "Aprende los conceptos básicos de primeros auxilios para mascotas. Ten un botiquín de emergencia y conoce los números de contacto de veterinarios de emergencia.",
                category = TipCategory.FIRST_AID,
                imageUrl = "https://images.unsplash.com/photo-1628009368231-7bb7cfcb0def?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80"
            )
        )
    }
} 