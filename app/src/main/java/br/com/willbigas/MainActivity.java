package br.com.willbigas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

import br.com.willbigas.api.DaoServer;
import br.com.willbigas.model.Piada;

public class MainActivity extends AppCompatActivity {

    private TextView tvId;
    private TextView tvPergunta;
    private TextView tvResposta;

    private ProgressBar progressBar;


    private Button btnGerarPiada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
        listenerButtonGerarPiada();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }


    private void inicializarComponentes() {
        tvId = findViewById(R.id.tvId);
        tvPergunta = findViewById(R.id.tvPergunta);
        tvResposta = findViewById(R.id.tvResposta);

        btnGerarPiada = findViewById(R.id.btnGerarPiada);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

    }


    private void listenerButtonGerarPiada() {
        btnGerarPiada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarAPI();

            }
        });
    }


    public void chamarAPI() {
        mostrarProgressBar();
        try {
            Piada piada = DaoServer.get();
            exibirDadosNaView(piada);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exibirDadosNaView(Piada piada) {
        tvId.setText("ID - " + String.valueOf(piada.getId()));
        tvPergunta.setText("Pergunta - " + piada.getPergunta());
        tvResposta.setText("Resposta - " + piada.getResposta());
        escondeProgressBar();
    }


    public void mostrarProgressBar() {
        new Thread(new Runnable() {
            public void run() {
                progressBar.post(new Runnable() {
                    public void run() {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();
    }

    public void escondeProgressBar() {
        new Thread(new Runnable() {
            public void run() {
                progressBar.post(new Runnable() {
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }


}
