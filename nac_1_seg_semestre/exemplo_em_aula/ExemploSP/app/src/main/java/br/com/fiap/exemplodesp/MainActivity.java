package br.com.fiap.exemplodesp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtSenha;
    CheckBox chkManterConectado;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha   = findViewById(R.id.edtSenha);
        chkManterConectado = findViewById(R.id.chkManterConectado);

        sp = getPreferences(MODE_PRIVATE);

        boolean conectado = sp.getBoolean("conectado", false);
        if ( conectado ) {
            String usuario = sp.getString("usuario", null);
            String senha   = sp.getString("senha", null);

            edtUsuario.setText( usuario );
            edtSenha.setText( senha );
            chkManterConectado.setChecked(true);
        }
    }

    public void login(View view) {
        String usuario = edtUsuario.getText().toString().trim();
        String senha   = edtSenha.getText().toString();

        if ( usuario.isEmpty() || senha.isEmpty() ) {
            Toast.makeText(this, "Informe corretamente os dados!", Toast.LENGTH_SHORT).show();
            return;
        }

        if ( usuario.equals("fiap") && senha.equals("fiap") ) {

            boolean conectado = chkManterConectado.isChecked();
            SharedPreferences.Editor editor = sp.edit();

            if ( conectado ) {
                editor.putString("usuario", usuario);
                editor.putString("senha", senha);
                editor.putBoolean("conectado", true);
            } else {
                //editor.remove("usuario");
                //editor.remove("senha");
                //editor.remove("conectado");

                editor.clear();
            }

            editor.commit();

            Toast.makeText(this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Usuário ou senha inválido!", Toast.LENGTH_SHORT).show();
    }
}
