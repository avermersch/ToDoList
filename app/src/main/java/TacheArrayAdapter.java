import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.formation.todolist.R;

import java.util.List;

import model.Tache;

/**
 * Created by Formation on 11/01/2018.
 */

public class TacheArrayAdapter extends ArrayAdapter {

    private Activity context;
    private List<Tache> data;
    private LayoutInflater inflater;


    public TacheArrayAdapter(@NonNull Context context, @NonNull List<Tache> data) {
        super(context, 0, data);

        this.data = data;
        this.context = (Activity) context;
        this.inflater = this.context.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Instanciation de la vue
        View view = this.inflater.inflate(R.layout.tache_check_view, parent,false);

        //Récupération des données d'une ligne
        Tache tacheData = this.data.get(position);

        //Liaison entre les données et la vue
        TextView tacheTextView = view.findViewById(R.id.checkedTextView);
        tacheTextView.setText(tacheData.getTache());

        CheckBox checkBox = view.findViewById(R.id.checkbox);
        checkBox.setTag(tacheData.getTache());

        return view;
    }

    public void setSelection(Integer selectedIndex) {
    }
}
