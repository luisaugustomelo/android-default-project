package ni.com.debugon.notificationinfo.ni.com.debugon.Intents;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import ni.com.debugon.notificationinfo.R;
import ni.com.debugon.notificationinfo.ni.com.debugon.DatabaseClasses.Keyword;
import ni.com.debugon.notificationinfo.ni.com.debugon.DatabaseHandlers.KeywordDatabaseHandler;

/**
 * Created by luisr on 19/03/2017.
 */

public class CreateKeyword extends AppCompatActivity {
    /*
    * Para botar a barra de titulo superior é necessário extender AppCompatActivity,
    * caso contrário pode ser extendido apenas Activity.
    * */

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_keyword);
        setTitle(R.string.create_keyword);

        final KeywordDatabaseHandler db = new KeywordDatabaseHandler(this);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.ok);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Keyword k = new Keyword();
                k.set_keyword(((EditText)findViewById(R.id.consulta)).getText().toString());
                db.addKeyword(k);
                finish();
            }
        });


    }
}
