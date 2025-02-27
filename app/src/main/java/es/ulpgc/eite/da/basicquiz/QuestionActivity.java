package es.ulpgc.eite.da.basicquiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    public static final String TAG = "Quiz.QuestionActivity";

    public final static String KEY_INDEX = "KEY_INDEX";
    public final static String KEY_RESULT = "KEY_RESULT";
    public final static String KEY_ENABLED = "KEY_ENABLED";
    public static final int CHEAT_REQUEST = 1;
    public static final int STATS_REQUEST = 2;

    private Button falseButton, trueButton;
    private Button cheatButton, nextButton, statsButton;
    private TextView questionField, resultField;

    private String[] questionsArray;
    private int questionIndex = 0;
    private int[] answersArray;
    private boolean nextButtonEnabled, statsButtonEnabled;

    private String resultText;

    private int correctAnswers = 0;
    private int totalQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setTitle(R.string.question_screen_title);
        Log.d(TAG, "onCreate");

        initLayoutData();
        linkLayoutComponents();

        if (savedInstanceState != null) {
            questionIndex = savedInstanceState.getInt(KEY_INDEX);
            resultText = savedInstanceState.getString(KEY_RESULT);
            nextButtonEnabled = savedInstanceState.getBoolean(KEY_ENABLED);
        }

        updateLayoutContent();
        initLayoutButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");

        outState.putInt(KEY_INDEX, questionIndex);
        outState.putString(KEY_RESULT, resultText);
        outState.putBoolean(KEY_ENABLED, nextButtonEnabled);
    }

    private void updateLayoutContent() {
        Log.d(TAG, "updateLayoutContent");

        questionField.setText(questionsArray[questionIndex]);

        if (!nextButtonEnabled) {
            resultText = getString(R.string.empty_text);
        }

        resultField.setText(resultText);

        cheatButton.setEnabled(!nextButtonEnabled);
        falseButton.setEnabled(!nextButtonEnabled);
        trueButton.setEnabled(!nextButtonEnabled);

        if (nextButtonEnabled && questionIndex == questionsArray.length-1) {
            nextButtonEnabled = false;
            statsButtonEnabled = true;

        } else {
            statsButtonEnabled = false;
        }

        nextButton.setEnabled(nextButtonEnabled);
        statsButton.setEnabled(statsButtonEnabled);

    }


    private void onTrueButtonClicked() {
        Log.d(TAG, "onTrueButtonClicked");

        if (answersArray[questionIndex] == 1) {
            resultText = getString(R.string.correct_text);
            correctAnswers++;
        } else {
            resultText = getString(R.string.incorrect_text);
        }

        totalQuestions++;
        nextButtonEnabled = true;
        updateLayoutContent();
    }

    private void onFalseButtonClicked() {
        Log.d(TAG, "onFalseButtonClicked");

        if (answersArray[questionIndex] == 0) {
            resultText = getString(R.string.correct_text);
            correctAnswers++;
        } else {
            resultText = getString(R.string.incorrect_text);
        }

        totalQuestions++;
        nextButtonEnabled = true;
        updateLayoutContent();
    }

    @SuppressWarnings("ALL")
    private void onCheatButtonClicked() {
        Log.d(TAG, "onCheatButtonClicked");

        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(CheatActivity.EXTRA_ANSWER, answersArray[questionIndex]);
        startActivityForResult(intent, CHEAT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "onActivityResult");

        // TODO:
        //  la pantalla "Stats" debe devolver un resultado a la pantalla "Question"
        //  para que esta última deje el estado de la pantalla como está pero
        //  desactive el botón de "Next" para impedir avanzar a la siguiente pregunta

        // TODO:
        //  la pantalla "Stats" debe devolver un resultado a la pantalla "Question"
        //  para que esta última reinicie el Quiz

        // TODO:
        //  la pantalla "Stats" debe devolver un resultado a la pantalla "Question"
        //  para que esta última finalice la app Quiz



        if (requestCode == CHEAT_REQUEST && resultCode == RESULT_OK && intent != null) {

            boolean answerCheated = intent.getBooleanExtra(
                CheatActivity.EXTRA_CHEATED, false
            );

            //Log.d(TAG, "answerCheated: " + answerCheated);

            // TODO:
            //  Modificar el codigo del siguiente "if" para que se cumplan estas condiciones:
            //  Si el usuario ha visto la respuesta a la última pregunta en "Cheat",
            //  desactivar todos los botones de la pantalla "Question" (excepto "Stats")
            //  Si el usuario ha visto la respuesta a cualquier otra pregunta,
            //  llamar el metodo "onNextButtonClicked" para avanzar a la siguiente pregunta
            //  utilizando el codigo que contiene el "if" ahora

            if (answerCheated) {

                nextButtonEnabled = true;
                onNextButtonClicked();
            }

        }

    }


    private void onNextButtonClicked() {
        Log.d(TAG, "onNextButtonClicked");

        nextButtonEnabled = false;
        questionIndex++;

        if (questionIndex < questionsArray.length) {
            updateLayoutContent();

        }

    }


    @SuppressWarnings("ALL")
    private void openStatsScreen() {
        Log.d(TAG, "openStatsScreen");

        // TODO:
        //  iniciar pantalla "Stats" con el total de las preguntas respondidas
        //  y de respuestas acertadas

        Intent intent = new Intent(this, StatsActivity.class);
        startActivityForResult(intent, STATS_REQUEST); // Código de solicitud
    }


    private void initLayoutButtons() {

        trueButton.setOnClickListener(v -> onTrueButtonClicked());
        falseButton.setOnClickListener(v -> onFalseButtonClicked());
        nextButton.setOnClickListener(v -> onNextButtonClicked());
        cheatButton.setOnClickListener(v -> onCheatButtonClicked());
        statsButton.setOnClickListener(v -> openStatsScreen());
    }


    private void initLayoutData() {
        questionsArray = getResources().getStringArray(R.array.questions_array);
        answersArray = getResources().getIntArray(R.array.answers_array);
    }

    private void linkLayoutComponents() {
        falseButton = findViewById(R.id.falseButton);
        trueButton = findViewById(R.id.trueButton);
        cheatButton = findViewById(R.id.cheatButton);
        nextButton = findViewById(R.id.nextButton);

        statsButton = findViewById(R.id.statsButton);

        questionField = findViewById(R.id.questionField);
        resultField = findViewById(R.id.resultField);
    }
}
