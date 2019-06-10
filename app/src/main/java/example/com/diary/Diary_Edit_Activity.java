package example.com.diary;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class Diary_Edit_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, FloatingActionButton.OnClickListener {
    private static String TAG = "KHUNHENG SOK";
    private Button date;
    private String date_str ;
    private String content_str;
    private int id ;
    private int position ;
    private FloatingActionButton fab ;
    private EditText content;
    private DatePickerDialog datePickerDialog;
    private static final String text = "date clicked";
    private boolean isEditMode = false ;



    @Override
    protected void onStop() {

        super.onStop();
    }


    void initilize() {
        fab = findViewById(R.id.fab);
        date = findViewById(R.id.date_button);
        content = findViewById(R.id.content);

        setViewMode();
        Intent intent = getIntent();
        date_str =  intent.getStringExtra("date");
        content_str = intent.getStringExtra("content");
        id = intent.getIntExtra("id",0);
        position = intent.getIntExtra("position",0);

        final Calendar calendar = Calendar.getInstance();

        if(date_str == null || date_str == ""){
            setEditMode();
            Log.i(TAG, "initilize: data_str null");
            date.setText("Add date");
        }else{
            Log.i(TAG, "initilize: data_str  not null" + date_str);

            date.setText(date_str);
        }

        if(content_str == null || content_str == ""){
            content.setHint("Start your diary here");
        }else {
            content.setText(content_str);
        }

        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        fab.setOnClickListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary__edit_);
        initilize();
    }

    @Override
    public void onBackPressed() {
        onSave();
        super.onBackPressed();
        finish();
    }

    public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    public void onSave(){
        Intent intent = new Intent();
        intent.putExtra("date", date.getText() );
        intent.putExtra("content",content.getText().toString());
        intent.putExtra("id",id);
        intent.putExtra("position",position);
        setResult(RESULT_OK,intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String formattedDate = new DateFormatSymbols().getMonths()[month] + " " + dayOfMonth + " " + year;
        date.setText(formattedDate);
    }

    @Override
    public void onClick(View v) {
        if(isEditMode){
            onSave();
            setViewMode();
            finish();
        }else{
            setEditMode();
        }
    }

    private void setEditMode(){
        isEditMode = true;
        date.setClickable(true);
        content.setTextIsSelectable(true);
        content.setFocusable(true);
        fab.setImageDrawable(getDrawable(R.drawable.ic_save_black_24dp));
    }

    private void setViewMode(){
        isEditMode = false;
        date.setClickable(false);
        content.setTextIsSelectable(true);
        content.setFocusable(true);
        fab.setImageDrawable(getDrawable(R.drawable.ic_edit_black_24dp));
    }


}
