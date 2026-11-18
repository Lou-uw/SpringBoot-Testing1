package etec.backend.service

import etec.backend.model.PrecondicionInvalidaException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CalculadoraDeDescuentosUnitTest : BehaviorSpec({

    val calculadora = CalculadoraDeDescuentos()

    Given("una calculadora de descuentos instanciada directamente, sin levantar Spring") {

        When("el usuario 123 realiza una compra de 100.0") {
            Then("aplica el descuento del 25% y el monto final es 75.0") {
                calculadora.aplicarDescuentosSegunUsuario("123", 100.0) shouldBe 75.0
            }
        }

        When("el usuario 456 realiza una compra de 100.0") {
            Then("no se aplica descuento y el monto final queda en 100.0") {
                calculadora.aplicarDescuentosSegunUsuario("456", 100.0) shouldBe 100.0
            }
        }

        When("el monto es exactamente 0.0") {
            Then("lanza PrecondicionInvalidaException con el mensaje exacto") {
                val excepcion = shouldThrow<PrecondicionInvalidaException> {
                    calculadora.aplicarDescuentosSegunUsuario("123", 0.0)
                }
                excepcion.message shouldBe "El monto debe ser mayor a cero"
            }
        }

        When("el monto es negativo (-50.0)") {
            Then("lanza PrecondicionInvalidaException con el mensaje exacto") {
                val excepcion = shouldThrow<PrecondicionInvalidaException> {
                    calculadora.aplicarDescuentosSegunUsuario("123", -50.0)
                }
                excepcion.message shouldBe "El monto debe ser mayor a cero"
            }
        }
    }
})
