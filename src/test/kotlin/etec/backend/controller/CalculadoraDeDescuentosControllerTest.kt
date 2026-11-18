package etec.backend.controller

import com.ninjasquad.springmockk.MockkBean
import etec.backend.exception.PrecondicionInvalidaException
import etec.backend.service.CalculadoraDeDescuentosService
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CalculadoraDeDescuentosController::class)
class CalculadoraDeDescuentosControllerTest(
    private val navegador: MockMvc,
    @MockkBean private val calculadora: CalculadoraDeDescuentosService,
) : BehaviorSpec({

    Given("el endpoint GET /api/descuentos con el servicio simulado") {

        When("el servicio devuelve 75.0 para userID=123 y monto=100.0") {
            every { calculadora.aplicarDescuentosSegunUsuario("123", 100.0) } returns 75.0

            val respuesta = navegador.perform(
                get("/api/descuentos")
                    .param("userID", "123")
                    .param("monto", "100.0"),
            )

            Then("responde 200 con montoFinal=75.0 y sin campo error") {
                respuesta.andExpect(status().isOk)
                respuesta.andExpect(jsonPath("$.montoFinal").value(75.0))
                respuesta.andExpect(jsonPath("$.error").doesNotExist())
            }
        }

        When("el servicio lanza PrecondicionInvalidaException para un monto inválido") {
            every {
                calculadora.aplicarDescuentosSegunUsuario("123", -10.0)
            } throws PrecondicionInvalidaException("El monto debe ser mayor a cero")

            val respuesta = navegador.perform(
                get("/api/descuentos")
                    .param("userID", "123")
                    .param("monto", "-10.0"),
            )

            Then("responde 400 con el mensaje en $.error y sin montoFinal") {
                respuesta.andExpect(status().isBadRequest)
                respuesta.andExpect(jsonPath("$.error").value("El monto debe ser mayor a cero"))
                respuesta.andExpect(jsonPath("$.montoFinal").doesNotExist())
            }
        }
    }
})
