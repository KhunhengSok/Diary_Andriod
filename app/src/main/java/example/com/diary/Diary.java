package example.com.diary;


/*
    This class is representation of each diary
    the date,content is set when object create
    *******the id is set when the object is add to the database************


    ***id is used as primary key in database only and no other uses
 */
public class Diary {
    private String date;
    private String content;
    private int id ;

    public Diary(String date, String content) {
        this.date = date;
        this.content = content;
        this.id = -1 ;
    }

    public int getId(){
        return this.id ;
    }

    public void setDate(String date){
        this.date = date;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Diary setId(int id){
        this.id = id ;
        return this;
    }
    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}