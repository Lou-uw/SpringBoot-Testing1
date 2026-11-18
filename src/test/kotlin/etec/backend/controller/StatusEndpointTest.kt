package etec.backend.controller

import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(Status::class)
class StatusEndpointTest(
    private val navegador: MockMvc,
) : BehaviorSpec({

    Given("el endpoint GET /status") {
        When("se consulta el estado de la aplicación") {
            val respuesta = navegador.perform(get("/status"))

            Then("responde 200 con el mensaje ok") {
                respuesta.andExpect(status().isOk)
                respuesta.andExpect(jsonPath("$.message").value("ok"))
            }
        }
    }
})
