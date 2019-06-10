package example.com.diary;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllDiaryActivity extends AppCompatActivity implements ListView.OnItemClickListener, FloatingActionButton.OnClickListener{
    private ListView listView;
    private FloatingActionButton fab;
    private static String TAG = "KHUNHENG SOK";
    private ArrayList<Diary> diaries ;
    private  DiaryDatabaseHelper db_helper;
    private Diary_list_adapter adapter;

    void initialize(){
        listView = findViewById(R.id.list_view);
        fab = findViewById(R.id.fab);
        db_helper = new DiaryDatabaseHelper();


        ArrayList<Diary> db = new ArrayList<Diary>();

        //TODO
        //Need to remove after implementation of dabatase helper
        db.add(new Diary("June 08 2019","Working on Diaries\n" +
                "Working on Realm"));
        db_helper.setDiaries( db)  ;
        //End

        diaries = db_helper.getAllDiaries();
        adapter = new Diary_list_adapter(this,R.layout.diary_list_view_item,diaries);//TODO
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_diary);
        initialize();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Diary_Edit_Activity.class);
        intent.putExtra("id",diaries.get(position).getId()) ;
        intent.putExtra("date",diaries.get(position).getDate()) ;
        intent.putExtra("content",diaries.get(position).getContent());
        intent.putExtra("position",position);
        startActivityForResult(intent,1 );
    }

    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(this,Diary_Edit_Activity.class), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        //request code 1 = open diary
        //request code 2 = new diary
        if(resultCode == RESULT_OK){
            String content ;
            String date ;
            int id ;
            int position; // position in list view

            content = data.getStringExtra("content");
            date = data.getStringExtra("date");
            id = data.getIntExtra("id",-1);
            position = data.getIntExtra("position",0);
           if (requestCode == 1){
               if (!diaries.get(position).getContent().equals(content) || !diaries.get(position).getDate().equals(date) ){

                   if(date != null && content != null){
                       adapter.notifyDataSetChanged();
                       db_helper.update_diary(id);
                       diaries.set(position,new Diary(date, content ).setId(id)); //the diary already exist in database so it has id
                       Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(this,"Incompleted " + (date != null) + (content != null) ,Toast.LENGTH_SHORT).show();
                   }

               }
           }

           if (requestCode == 2){
               if(date != null && content != null){
                   Diary new_diary = new Diary(date, content);
                   boolean is_success = db_helper.add_diary(new_diary);
                   if (is_success ){
                       Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                       diaries.add(new_diary); //no id object since it doesn't exist yet, need to set one when add to database
                       adapter.notifyDataSetChanged();
                   }else{
                       Toast.makeText(this,"Incompleted " + (date != null) + (content != null) ,Toast.LENGTH_SHORT).show();
                   }
               }else {
                   Toast.makeText(this,"Incompleted " + (date != null) + (content != null) ,Toast.LENGTH_SHORT).show();
               }
           }
        }



        Log.i(TAG, "onActivityResult: " + requestCode + resultCode) ;
        super.onActivityResult(requestCode, resultCode, data);
    }
}
