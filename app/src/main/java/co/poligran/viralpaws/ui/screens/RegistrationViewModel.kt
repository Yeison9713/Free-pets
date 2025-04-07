package co.poligran.viralpaws.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class RegistrationState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRegistrationComplete: Boolean = false
)

class RegistrationViewModel : ViewModel() {
    private val _registrationState = MutableStateFlow(RegistrationState())
    val registrationState: StateFlow<RegistrationState> = _registrationState.asStateFlow()

    fun registerUser(
        nombre: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        // Validar campos
        if (nombre.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _registrationState.value = RegistrationState(error = "Todos los campos son requeridos")
            return
        }

        if (password != confirmPassword) {
            _registrationState.value = RegistrationState(error = "Las contraseñas no coinciden")
            return
        }

        // TODO: Implementar la lógica de registro con Firebase o tu backend
        _registrationState.value = RegistrationState(isRegistrationComplete = true)
    }

    fun resetState() {
        _registrationState.value = RegistrationState()
    }
} 