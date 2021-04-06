package michailidismichalis.com.ergasiasxolis.NextMeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import michailidismichalis.com.ergasiasxolis.R;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private ArrayList<FoodObject> foodList;
    private Context context;

    public MealAdapter(Context context, ArrayList<FoodObject> foodList){
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal, null, false);

        MealAdapter.MealViewHolder viewHolder = new MealAdapter.MealViewHolder(layoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Glide.with(context).load(foodList.get(position).getPhoto()).into(holder.mealImage);

        holder.mealName.setText(foodList.get(position).getName());
        holder.mealKcals.setText("- " + foodList.get(position).getKcals() + " kcals");
        holder.mealProtein.setText("- " + foodList.get(position).getProtein() + "gr protein");
        holder.mealFat.setText("- " + foodList.get(position).getFat() + "gr fat");
        holder.mealCarbs.setText("- " + foodList.get(position).getCarbs() + "gr carbs");

        holder.meal.setOnClickListener(v -> NextMealActivity.addMeal(position));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder{
        public ImageView mealImage;
        public TextView mealName;
        public TextView mealKcals;
        public TextView mealProtein;
        public TextView mealFat;
        public TextView mealCarbs;
        public CardView meal;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);

            meal = itemView.findViewById(R.id.meal);
            mealImage = itemView.findViewById(R.id.mealImage);
            mealName = itemView.findViewById(R.id.mealName);
            mealKcals = itemView.findViewById(R.id.mealKcals);
            mealProtein = itemView.findViewById(R.id.mealProtein);
            mealFat = itemView.findViewById(R.id.mealFat);
            mealCarbs = itemView.findViewById(R.id.mealCarbs);
        }
    }
}
