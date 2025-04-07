package co.poligran.viralpaws.model

data class Pet(
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val breed: String = "",
    val birthDate: String = "",
    val weight: Double = 0.0,
    val healthRecords: List<HealthRecord> = emptyList(),
    val behaviorRecords: List<BehaviorRecord> = emptyList()
)

data class HealthRecord(
    val id: String = "",
    val date: String = "",
    val type: String = "", // Vacuna, Control, Emergencia, etc.
    val description: String = "",
    val veterinarian: String = "",
    val nextAppointment: String? = null
)

data class BehaviorRecord(
    val id: String = "",
    val date: String = "",
    val type: String = "", // Entrenamiento, Socialización, Problema, etc.
    val description: String = "",
    val notes: String = ""
) 