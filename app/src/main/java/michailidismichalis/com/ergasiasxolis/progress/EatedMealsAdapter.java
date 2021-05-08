package michailidismichalis.com.ergasiasxolis.progress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import michailidismichalis.com.ergasiasxolis.NextMeal.FoodAdapter;
import michailidismichalis.com.ergasiasxolis.NextMeal.MealObject;
import michailidismichalis.com.ergasiasxolis.NextMeal.NextMealActivity;
import michailidismichalis.com.ergasiasxolis.R;

public class EatedMealsAdapter extends RecyclerView.Adapter<EatedMealsAdapter.EatedMealViewHolder>{
    private ArrayList<MealObject> mealList;
    private Context context;
    private int resource;

    public EatedMealsAdapter(Context context, int resource, ArrayList<MealObject> mealList){
        this.context = context;
        this.resource = resource;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public EatedMealsAdapter.EatedMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.eatedmeals, null, false);

        EatedMealsAdapter.EatedMealViewHolder viewHolder = new EatedMealsAdapter.EatedMealViewHolder(layoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EatedMealsAdapter.EatedMealViewHolder holder, int position) {
        holder.mealName.setText(mealList.get(position).getId());
        holder.foodAdapter = new FoodAdapter(context, resource, mealList.get(position).getFoodList());
        holder.savedMealsListView.setAdapter(holder.foodAdapter);

        holder.savedMeal.setOnClickListener(v -> { NextMealActivity.addSavedMealFoodsToAddedMeals(position); });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class EatedMealViewHolder extends RecyclerView.ViewHolder{
        public TextView mealName;
        public ListView savedMealsListView;
        public FoodAdapter foodAdapter;
        public CardView savedMeal;

        public EatedMealViewHolder(@NonNull View itemView) {
            super(itemView);

            savedMeal = itemView.findViewById(R.id.saved_meal);
            mealName = itemView.findViewById(R.id.mealName);
            savedMealsListView = itemView.findViewById(R.id.savedMealsListView);
        }
    }
}
