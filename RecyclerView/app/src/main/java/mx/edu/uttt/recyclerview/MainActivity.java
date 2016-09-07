package mx.edu.uttt.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnCreate;
    private Button btnDelete;
    private Button btnMove;

    private ArrayList<Title> titles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = new ArrayList<Title>();

        for (int i = 0; i<30; i++){
            titles.add(new Title("Title "+i, "Subtitle "+i));
        }

        recyclerView = (RecyclerView) findViewById(R.id.rcv_titles);

        recyclerView.setHasFixedSize(true);

        final TitleAdapter adapter = new TitleAdapter(titles);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

        //recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pulso en el elemento: "
                        +recyclerView.getChildPosition(v),
                        Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        btnCreate = (Button) findViewById(R.id.btn_create);
        btnMove = (Button) findViewById(R.id.btn_move);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               titles.add(1, new Title("New title", "New subtitle"));
               adapter.notifyItemInserted(1);


            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              titles.remove(1);
              adapter.notifyItemRemoved(1);
            }
        });

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title aux = titles.get(1);
                titles.set(1, titles.get(2));
                titles.set(2, aux);
                adapter.notifyItemMoved(1,2);
            }
        });
    }
}
