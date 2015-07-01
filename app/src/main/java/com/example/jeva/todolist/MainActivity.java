package com.example.jeva.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    ListView lvToDo;

    ListAdapter todoadapter;
    ArrayList<String> arraytodo;
    String todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvToDo =(ListView)findViewById(R.id.lvToDo);

        arraytodo = new ArrayList<String>();

        todoadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arraytodo);

        lvToDo.setAdapter(todoadapter);

        lvToDo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //
                delete(position);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            add();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void add(){
        AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(this);
        todoTaskBuilder.setTitle("Add Task");
        todoTaskBuilder.setMessage("What to do ?");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(40, 0, 40, 0);

        final EditText etTodo = new EditText(this);
        layout.addView(etTodo,params);

        todoTaskBuilder.setView(layout);

        todoTaskBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //add

                todo = etTodo.getText().toString();
                arraytodo.add(todo);
                setList();

            }
        });

        todoTaskBuilder.setNegativeButton("Cancel",null);

        todoTaskBuilder.create().show();

    }
    public void delete(final int position){
        AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(this);
        todoTaskBuilder.setTitle("Delete Task");
        todoTaskBuilder.setMessage("Are you sure want to delete this task?");

        todoTaskBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //delete
                Toast.makeText(MainActivity.this,arraytodo.get(position).toString() + " "+ "has been removed" ,Toast.LENGTH_SHORT).show();
                arraytodo.remove(position);
                setList();

            }
        });

        todoTaskBuilder.setNegativeButton("Cancel", null);

        todoTaskBuilder.create().show();
    }

    public void setList(){

        todoadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arraytodo);

        lvToDo.setAdapter(todoadapter);
    }

}
