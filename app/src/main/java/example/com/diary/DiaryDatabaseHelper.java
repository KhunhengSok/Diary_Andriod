package example.com.diary;

import java.util.ArrayList;


/*
    table(
        id (primary key)
        date (string)
        content( text)/(string)
    )

    Format of date
       ' Month days year'
        june 10 2019


    ***** always set id to the object when getting data from database
            *by     object.setId(id)

    ***** Diary object are create by
            * object = new Diary(date, content)
            * object.setId(id)
    ***** when add the data to database need to extract |date(string), content{string), id(int)|

 */
public class DiaryDatabaseHelper {
    private ArrayList<Diary> diaries;

    public void setDiaries(ArrayList<Diary> diaries ){
        //no need to work on this
        //delete after implement the database
        this.diaries = diaries;
    }

    public ArrayList<Diary> getAllDiaries(){
        //TO DO
        return diaries;
    }

    public boolean add_diary(Diary diary){
        return true;
    }

    public Diary getDiary(int id){
        return null;
    }

    public boolean update_diary(int id ){
        return true;
    }
}
