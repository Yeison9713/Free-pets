package co.poligran.viralpaws.ui.screens

import androidx.lifecycle.ViewModel
import co.poligran.viralpaws.model.BehaviorRecord
import co.poligran.viralpaws.model.HealthRecord
import co.poligran.viralpaws.model.Pet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PetHealthState(
    val pet: Pet? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class PetHealthViewModel : ViewModel() {
    private val _state = MutableStateFlow(PetHealthState())
    val state: StateFlow<PetHealthState> = _state.asStateFlow()

    fun addHealthRecord(record: HealthRecord) {
        val currentPet = _state.value.pet ?: return
        val updatedHealthRecords = currentPet.healthRecords + record
        _state.value = _state.value.copy(
            pet = currentPet.copy(healthRecords = updatedHealthRecords)
        )
    }

    fun addBehaviorRecord(record: BehaviorRecord) {
        val currentPet = _state.value.pet ?: return
        val updatedBehaviorRecords = currentPet.behaviorRecords + record
        _state.value = _state.value.copy(
            pet = currentPet.copy(behaviorRecords = updatedBehaviorRecords)
        )
    }

    fun updatePetWeight(newWeight: Double) {
        val currentPet = _state.value.pet ?: return
        _state.value = _state.value.copy(
            pet = currentPet.copy(weight = newWeight)
        )
    }
} 