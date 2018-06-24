package minhasanotacoes.cursoandroid.dell.minhasanotacoes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText texto;
    private ImageView salvar;

    private static final String NOME_ARQUIVO = "arquivoAnotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (EditText) findViewById(R.id.anotacao_id);
        salvar = (ImageView) findViewById(R.id.salvar_id);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoDigitado = texto.getText().toString();
                gravarArquivo(textoDigitado);

            }
        });

        texto.setText(lerArquivo());
    }

    private void gravarArquivo(String string) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(string);
            outputStreamWriter.close();
            Toast.makeText(getApplicationContext(), "Arquivo Salvo", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

    private String lerArquivo() {
        String resultado = "";

        try {
            InputStream inputStream = openFileInput(NOME_ARQUIVO);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String linha = "";
                while ( (linha = bufferedReader.readLine()) != null) {
                    resultado += linha;
                }
            }

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

        return resultado;
    }
}
