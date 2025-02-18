Feature: Stats

  Scenario: Regresar a Question sin reiniciar
    Given iniciar pantalla Question
    And responder todas preguntas del Quiz
    And abrir pantalla Stats
    And pulsar boton Back en Stats
    Then mostrar pantalla Question con ultima pregunta

  Scenario: Reiniciar Quiz desde Stats
    Given iniciar pantalla Question
    And responder todas preguntas del Quiz
    And abrir pantalla Stats
    And pulsar boton Reiniciar
    Then mostrar pantalla Question con primera pregunta

  Scenario: Cerrar aplicación Quiz desde Stats
    Given iniciar pantalla Question
    And responder todas preguntas del Quiz
    And abrir pantalla Stats
    And pulsar boton Finalizar
    Then cerrar aplicación
