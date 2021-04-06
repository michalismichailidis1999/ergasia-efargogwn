package michailidismichalis.com.ergasiasxolis.NextMeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import michailidismichalis.com.ergasiasxolis.R;

public class FoodAdapter extends ArrayAdapter<FoodObject> {
    private Context context;
    private int resource;

    public FoodAdapter(@NonNull Context context, int resource, ArrayList<FoodObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String foodName = getItem(position).getName();

        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(resource, parent, false);

        TextView foodNameTextView = convertView.findViewById(R.id.foodName);
        foodNameTextView.setText(foodName);

        return convertView;
    }
}
