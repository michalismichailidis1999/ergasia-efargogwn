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
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.eated_meals, null, false);

        EatedMealsAdapter.EatedMealViewHolder viewHolder2 = new EatedMealsAdapter.EatedMealViewHolder(layoutView);

        return viewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull EatedMealsAdapter.EatedMealViewHolder holder, int position) {
        holder.mealName2.setText(mealList.get(position).getId());
        holder.EatedMealsAdapter = new FoodAdapter(context, resource, mealList.get(position).getFoodList());
        holder.savedMealsListView2.setAdapter(holder.EatedMealsAdapter);

        //holder.savedMeal2.setOnClickListener(v -> { NextMealActivity.addSavedMealFoodsToAddedMeals(position); });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class EatedMealViewHolder extends RecyclerView.ViewHolder{
        public TextView mealName2;
        public ListView savedMealsListView2;
        public FoodAdapter EatedMealsAdapter;
        public CardView savedMeal2;

        public EatedMealViewHolder(@NonNull View itemView) {
            super(itemView);

            savedMeal2 = itemView.findViewById(R.id.eatedmeals);
            mealName2 = itemView.findViewById(R.id.mealName2);
            savedMealsListView2 = itemView.findViewById(R.id.savedMealsListView2);
        }
    }
}
