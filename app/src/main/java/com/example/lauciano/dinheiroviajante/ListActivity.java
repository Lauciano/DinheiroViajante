package com.example.lauciano.dinheiroviajante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lauciano.dinheiroviajante.model.Transaction;

public class ListActivity extends AppCompatActivity {

    private TextView totalValor;
    private ListView transactionList;

    @Override
    protected void onResume() {
        super.onResume();
        //buildStudentsList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        totalValor = (TextView) findViewById(R.id.total);
        transactionList = (ListView) findViewById(R.id.list);

        transactionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Transaction transaction = (Transaction) transactionList.getItemAtPosition(position);
                Intent intentInfo = new Intent(ListActivity.this, InfoActivity.class);
                intentInfo.putExtra("transaction", transaction);
                startActivity(intentInfo);
            }
        });

        registerForContextMenu(transactionList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    final ContextMenu.ContextMenuInfo menuInfo) {
        final MenuItem informTransaction = menu.add("Informar transação");
        final MenuItem editTransaction = menu.add("Editar transação");
        final MenuItem deleteTransaction = menu.add("Deletar transação");

        AdapterView.AdapterContextMenuInfo adapterMenuInfo =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Transaction transacation =
                (Transaction) transactionList.getItemAtPosition(adapterMenuInfo.position);

    }
}
