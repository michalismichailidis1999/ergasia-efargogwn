package michailidismichalis.com.ergasiasxolis.NextMeal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import michailidismichalis.com.ergasiasxolis.R;

public class AddedMealAdapter extends RecyclerView.Adapter<AddedMealAdapter.AddedMealViewHolder> {
    ArrayList<FoodObject> foodList;

    public AddedMealAdapter(ArrayList<FoodObject> foodList){
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public AddedMealAdapter.AddedMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.added_meal, null, false);

        AddedMealViewHolder viewHolder = new AddedMealViewHolder(layoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddedMealAdapter.AddedMealViewHolder holder, int position) {
        holder.addedMeal.setText(foodList.get(position).getName());

        holder.addedMeal.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextMealActivity.deleteAddedMeal(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class AddedMealViewHolder extends RecyclerView.ViewHolder{
        public Chip addedMeal;

        public AddedMealViewHolder(@NonNull View itemView) {
            super(itemView);
            addedMeal = itemView.findViewById(R.id.addedMeal);
        }
    }
}
