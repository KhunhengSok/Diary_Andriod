package example.com.diary;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Diary_list_adapter extends ArrayAdapter<Diary> {
    private ArrayList<Diary> diaries;
    private Context context;

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.diary_list_view_item,parent,false);

        TextView date = convertView.findViewById(R.id.date);
        TextView content = convertView.findViewById(R.id.content);

        String formatDate = diaries.get(position).getDate();
        formatDate = TextUtils.join("\n",formatDate.split("\\s+"));
        date.setText(formatDate);
        content.setText(diaries.get(position).getContent());

        return convertView;
    }

    public Diary_list_adapter(Context context, int resource, ArrayList<Diary> diaries) {
        super(context, resource, diaries);
        this.context = context;
        this.diaries = diaries;
    }
}
