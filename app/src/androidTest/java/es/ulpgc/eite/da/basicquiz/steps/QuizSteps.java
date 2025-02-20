package es.ulpgc.eite.da.basicquiz.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import es.ulpgc.eite.da.basicquiz.QuestionActivity;
import es.ulpgc.eite.da.basicquiz.R;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Project: Basic Quiz
// Created by Luis Hernandez on 10/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class QuizSteps {

    ActivityScenario<QuestionActivity> scenario =
        ActivityScenario.launch(QuestionActivity.class);

    /*@Rule
    public ActivityScenarioRule<QuestionActivity> activityRule =
        new ActivityScenarioRule<>(QuestionActivity.class);*/

    @Given("iniciar pantalla Question")
    public void iniciarPantallaQuestion() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @And("mostrar pregunta {string}")
    public void mostrarPregunta(String question) {
        onView(withId(R.id.questionField)).check(matches(withText(question)));
    }

    @And("ocultar resultado")
    public void ocultarResultado() {
        //onView(withId(R.id.resultField)).check(matches(withText("???")));
        onView(withId(R.id.resultField)).check(matches(withText(R.string.empty_text)));
    }

    @And("mostrar botones True y False y Cheat activados")
    public void mostrarBotonesTrueYFalseYCheatActivados() {
        onView(withId(R.id.trueButton)).check(matches(isEnabled()));
        onView(withId(R.id.falseButton)).check(matches(isEnabled()));
        onView(withId(R.id.cheatButton)).check(matches(isEnabled()));
    }


    @And("mostrar boton Next desactivado")
    public void mostrarBotonNextDesactivado() {
        onView(withId(R.id.nextButton)).check(matches(not(isEnabled())));
    }

    @And("pulsar boton {string}")
    public void pulsarBoton(String button) {

        int btn = button.equals("True") ? R.id.trueButton :  R.id.falseButton;
        onView(withId(btn)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @And("mostrar resultado {string} a respuesta {string}")
    public void mostrarResultadoARespuesta(String result, String answer) {
        onView(withId(R.id.resultField)).check(matches(withText(result)));
    }

    @And("mostrar botones True y False y Cheat desactivados")
    public void mostrarBotonesTrueYFalseYCheatDesactivados() {
        onView(withId(R.id.trueButton)).check(matches(not(isEnabled())));
        onView(withId(R.id.falseButton)).check(matches(not(isEnabled())));
        onView(withId(R.id.cheatButton)).check(matches(not(isEnabled())));
    }

    @And("mostrar boton Next activado")
    public void mostrarBotonNextActivado() {
        onView(withId(R.id.nextButton)).check(matches(isEnabled()));
    }

    @When("girar pantalla")
    public void girarPantalla() {

        InstrumentationRegistry.getInstrumentation()
            .getUiAutomation().executeShellCommand("settings put system accelerometer_rotation 0");

        InstrumentationRegistry.getInstrumentation()
            .getUiAutomation().executeShellCommand("settings put system user_rotation 1");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        InstrumentationRegistry.getInstrumentation()
            .getUiAutomation().executeShellCommand("settings put system user_rotation 0");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    /*@When("girar pantalla")
    public void girarPantalla() {


        scenario.onActivity(activity -> {
            activity.setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            activity.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        });
    }*/

    @And("pulsar boton Cheat")
    public void pulsarBotonCheat() {
        onView(withId(R.id.cheatButton)).perform(click());
    }

    @And("iniciar pantalla Cheat")
    public void iniciarPantallaCheat() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @And("mostrar mensaje Warning")
    public void mostrarMensajeWarning() {
        onView(withId(R.id.warningField)).check(matches(withText("Are you sure?")));
    }


    @And("ocultar respuesta")
    public void ocultarRespuesta() {
        //onView(withId(R.id.answerField)).check(matches(withText("???")));
        onView(withId(R.id.answerField)).check(matches(withText(R.string.empty_text)));
    }

    @And("mostrar botones Yes y No activados")
    public void mostrarBotonesYesYNoActivados() {
        onView(withId(R.id.yesButton)).check(matches(isEnabled()));
        onView(withId(R.id.noButton)).check(matches(isEnabled()));
    }

    @And("pulsar boton Back")
    public void pulsarBotonBack() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        pressBack();
    }

    @And("pulsar boton No")
    public void pulsarBotonNo() {
        onView(withId(R.id.noButton)).perform(click());
    }

    @And("finalizar pantalla Cheat")
    public void finalizarPantallaCheat() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @And("resumir pantalla Question")
    public void resumirPantallaQuestion() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    @And("pulsar boton Yes")
    public void pulsarBotonYes() {
        onView(withId(R.id.yesButton)).perform(click());
    }

    @And("mostrar respuesta {string} a pregunta {string}")
    public void mostrarRespuestaAPregunta(String answer, String question) {
        onView(withId(R.id.answerField)).check(matches(withText(answer)));
    }

    @And("mostrar botones Yes y No desactivados")
    public void mostrarBotonesYesYNoDesactivados() {
        onView(withId(R.id.yesButton)).check(matches(not(isEnabled())));
        onView(withId(R.id.noButton)).check(matches(not(isEnabled())));
    }

    @And("pulsar boton Next")
    public void pulsarBotonNext() {
        onView(withId(R.id.nextButton)).perform(click());
    }


    // ----------------------------------------------------------


    @And("pulsar boton Finalizar")
    public void pulsarBotonFinalizar() {
        onView(withId(R.id.exitButton)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { }
    }

    @And("pulsar boton Reiniciar")
    public void pulsarBotonReiniciar() {
        onView(withId(R.id.restartButton)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { }
    }

    @And("pulsar boton Back en Stats")
    public void pulsarBotonBackEnStats() {
        pressBack();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { }
    }

    @Then("mostrar pantalla Question con primera pregunta")
    public void mostrarPantallaQuestionConPrimeraPregunta() {

        // Obtener lista de preguntas desde archivo "strings.xml"
        String[] questionsArray = ApplicationProvider
            .getApplicationContext().getResources()
            .getStringArray(R.array.questions_array);


        //onView(withId(R.id.questionField)).check(matches(withText("Question #1: True")));
        //onView(withId(R.id.questionField)).check(matches(withText(R.string.question_1)));
        onView(withId(R.id.questionField)).check(matches(withText(questionsArray[0])));
        //onView(withId(R.id.resultField)).check(matches(withText("???")));
        onView(withId(R.id.resultField)).check(matches(withText(R.string.empty_text)));
    }

    @Then("mostrar pantalla Question con ultima pregunta")
    public void mostrarPantallaQuestionConUltimaPregunta() {


        // Obtener lista de preguntas desde archivo "strings.xml"
        String[] questionsArray = ApplicationProvider
            .getApplicationContext().getResources()
            .getStringArray(R.array.questions_array);

        onView(withId(R.id.questionField))
            .check(matches(withText(questionsArray[questionsArray.length - 1])));

        //onView(withId(R.id.resultField)).check(matches(not(withText("???"))));
        onView(withId(R.id.resultField))
            .check(matches(not(withText(R.string.empty_text))));

    }


    @And("responder todas preguntas del Quiz")
    public void responderTodasPreguntasDelQuiz() {

        // Obtener lista de preguntas desde archivo "strings.xml"
        String[] questionsArray = ApplicationProvider
            .getApplicationContext().getResources()
            .getStringArray(R.array.questions_array);

        for (int i = 0; i < questionsArray.length-1; i++) {

            // Seleccionar una respuesta al azar (True o False)
            if (Math.random() > 0.5) {
                onView(withId(R.id.trueButton)).perform(click());
            } else {
                onView(withId(R.id.falseButton)).perform(click());
            }

            // Pulsar botón Next si está activado
            onView(withId(R.id.nextButton))
                .check(matches(isEnabled())).perform(click());


        }

        // Como hemos llegado al final, el botón Next no está habilitado
        onView(withId(R.id.nextButton)).check(matches(not(isEnabled())));
        onView(withId(R.id.questionField))
            .check(matches(withText(questionsArray[questionsArray.length - 1])));

        // El botón Stats debe estar no habilitado
        onView(withId(R.id.statsButton)).check(matches(not(isEnabled())));

        // Seleccionar una respuesta al azar (True o False)
        if (Math.random() > 0.5) {
            onView(withId(R.id.trueButton)).perform(click());
        } else {
            onView(withId(R.id.falseButton)).perform(click());
        }

        // El botón Stats debe estar habilitado
        onView(withId(R.id.statsButton)).check(matches(isEnabled()));


    }

    @And("abrir pantalla Stats")
    public void abrirPantallaStats() {
        onView(withId(R.id.statsButton)).perform(click());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { }

        // Verificar si la pantalla de Stats se ha abierto (final del Quiz)
        try {
            onView(withId(R.id.restartButton)).check(matches(isDisplayed()));
        } catch (Exception ignored) { }
    }

    @Then("cerrar aplicación")
    public void cerrarAplicacion() {

        // Esperar para asegurar que la app se cierra
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }

        // Verificar que no hay ninguna actividad visible
        try {
            onView(withId(R.id.questionField)).check(doesNotExist());
        } catch (Exception ignored) { }
    }

}
