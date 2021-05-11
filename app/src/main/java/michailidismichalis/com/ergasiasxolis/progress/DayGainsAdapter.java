package michailidismichalis.com.ergasiasxolis.progress;

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

import michailidismichalis.com.ergasiasxolis.NextMeal.MealAdapter;
import michailidismichalis.com.ergasiasxolis.progress.FoodObject2;
import michailidismichalis.com.ergasiasxolis.progress.ProgressActivity;
import michailidismichalis.com.ergasiasxolis.R;

public class DayGainsAdapter extends RecyclerView.Adapter<DayGainsAdapter.DayGainsViewHolder> {
    private ArrayList<FoodObject2> foodList2;
    private Context context;

    public DayGainsAdapter(Context context, ArrayList<FoodObject2> foodList2){
        this.context = context;
        this.foodList2 = foodList2;
    }



    @NonNull
    @Override
    public DayGainsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_gains, null, false);

        DayGainsAdapter.DayGainsViewHolder viewHolder3 = new DayGainsAdapter.DayGainsViewHolder(layoutView);

        return viewHolder3;
    }



    @Override
    public void onBindViewHolder(@NonNull DayGainsViewHolder holder, int position) {
        //Glide.with(context).load(foodList2.get(position).getPhoto()).into(holder.mealImage);

        holder.Date.setText(foodList2.get(position).getId());
        holder.totalKcals.setText("- " + foodList2.get(position).getKcals2() + " kcals");
        holder.totalProtein.setText("- " + foodList2.get(position).getProtein2() + "gr protein");
        holder.totalFat.setText("- " + foodList2.get(position).getFat2() + "gr fat");
        holder.totalCarbs.setText("- " + foodList2.get(position).getCarbs2() + "gr carbs");

        //holder.meal.setOnClickListener(v -> NextMealActivity.addMeal(position));
    }

    @Override
    public int getItemCount() {
        return foodList2.size();
    }

    public class DayGainsViewHolder extends RecyclerView.ViewHolder{
        public TextView Date;
        public TextView totalKcals;
        public TextView totalProtein;
        public TextView totalFat;
        public TextView totalCarbs;

        public DayGainsViewHolder(@NonNull View itemView) {
            super(itemView);


            Date = itemView.findViewById(R.id.Date);
            totalKcals = itemView.findViewById(R.id.totalKcals);
            totalProtein = itemView.findViewById(R.id.totalProtein);
            totalFat = itemView.findViewById(R.id.totalFat);
            totalCarbs = itemView.findViewById(R.id.totalCarbs);
        }
    }
}











































/*
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

import michailidismichalis.com.ergasiasxolis.progress.DayGainsAdapter;
import michailidismichalis.com.ergasiasxolis.progress.FoodObject2;
import michailidismichalis.com.ergasiasxolis.R;

public class DayGainsAdapter extends RecyclerView.Adapter<DayGainsAdapter.DailyGainsViewHolder>{
    private ArrayList<FoodObject2> Foodlist2;
    private Context context;
    private int resource;

    public DayGainsAdapter(Context context, int resource, ArrayList<FoodObject2> Foodlist2){
        this.context = context;
        this.resource = resource;
        this.Foodlist2 = Foodlist2;
    }

    @NonNull
    @Override
    public DayGainsAdapter.DailyGainsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_gains, null, false);

        DayGainsAdapter.DailyGainsViewHolder viewHolder2 = new DayGainsAdapter.DailyGainsViewHolder(layoutView);

        return viewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull DayGainsAdapter.DailyGainsViewHolder holder, int position) {
        holder.Date.setText(Foodlist2.get(position).getId());
        holder.totalCarbs.setText(Foodlist2.get(position).getCarbs2());
        holder.totalKcals.setText(Foodlist2.get(position).getKcals2());
        holder.totalFat.setText(Foodlist2.get(position).getFat2());
        holder.totalProtein.setText(Foodlist2.get(position).getProtein2());
        holder.DailyGainsViewHolder = new FoodAdapter(context, resource, mealList.get(position).getFoodList());
        holder.savedMealsListView2.setAdapter(holder.DailyGainsViewHolder);

        //holder.savedMeal2.setOnClickListener(v -> { NextMealActivity.addSavedMealFoodsToAddedMeals(position); });
    }

    @Override
    public int getItemCount() {
        return Foodlist2.size();
    }

    public class DailyGainsViewHolder extends RecyclerView.ViewHolder{
        public TextView Date;
        public TextView totalCarbs;
        public TextView totalKcals;
        public TextView totalProtein;
        public TextView totalFat;




        public DailyGainsViewHolder(@NonNull View itemView) {
            super(itemView);

            Date = itemView.findViewById(R.id.Date);
            totalCarbs = itemView.findViewById(R.id.totalCarbs);
            totalKcals = itemView.findViewById(R.id.totalKcals);
            totalProtein = itemView.findViewById(R.id.totalProtein);
            totalFat = itemView.findViewById(R.id.totalFat);

        }
    }
}
*/