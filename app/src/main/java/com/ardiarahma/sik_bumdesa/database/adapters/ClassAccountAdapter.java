package com.ardiarahma.sik_bumdesa.database.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorSpace;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.database.models.ClassAccount;

import java.util.ArrayList;

import static com.ardiarahma.sik_bumdesa.database.models.ClassAccount.STATE.CLOSED;
import static com.ardiarahma.sik_bumdesa.database.models.ClassAccount.STATE.OPENED;

/**
 * Created by Windows 10 on 8/8/2019.
 */

public class ClassAccountAdapter extends RecyclerView.Adapter<ClassAccountAdapter.ViewHolder> {

    Context context;
    private ArrayList<ClassAccount> classAccounts = new ArrayList<>();

    public ClassAccountAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ClassAccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_class_accounts, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClassAccountAdapter.ViewHolder holder, final int position) {
        final ClassAccount account = classAccounts.get(position);
        holder.name.setText(account.getName());
        holder.id.setText(String.valueOf(account.getId()));

        holder.itemView.setTag(R.string.CLASSACCOUNT, classAccounts);
        holder.itemView.setTag(R.string.position, classAccounts);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
        layoutParams.setMargins(((int) convertDpToPixel(20, context)) * classAccounts.get(position).getLevel(), layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);


        switch (account.getState()){
            case CLOSED:
                holder.arrow.setImageResource(R.drawable.svg_arrow_right_filled);
                break;
            case OPENED:
                holder.arrow.setImageResource(R.drawable.svg_arrow_down_filled);
                break;
        }

        if (account.getClassAccounts().isEmpty()){
            holder.arrow.setVisibility(View.INVISIBLE);
            holder.view.setVisibility(View.VISIBLE);
        }else {
            holder.arrow.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (classAccounts.isEmpty()){
                    return;
                }

                if (classAccounts.get(position).getState().equals("CLOSED")){
                    classAccounts.addAll(position + 1, classAccounts.get(position).getClassAccounts());
                    notifyItemRangeInserted(position + 1, classAccounts.get(position).getClassAccounts().size());
                    notifyItemRangeChanged(position + 1, classAccounts.get(position).getClassAccounts().size(), classAccounts.size() - (position + account.getClassAccounts().size()));
                    notifyItemRangeChanged(position, classAccounts.size() - position);
                    classAccounts.get(position).state = OPENED;
                }else if (classAccounts.get(position).getState().equals("OPENED")){
                    int start = position + 1;
                    int end = classAccounts.size();
                    for (int i=start; i < classAccounts.size(); i++){
                        ClassAccount account1 = classAccounts.get(i);
                        if (account1.getLevel() <= classAccounts.get(position).getLevel()){
                            end = i;
                            break;
                        }
                    }

                    if (end != -1){
                        classAccounts.subList(start, end).clear();
                        notifyItemRangeRemoved(start, end - start);
                        notifyItemRangeChanged(start, end - start);
                        notifyItemRangeChanged(position, classAccounts.size() - position);
                    }
                    classAccounts.get(position).state = ClassAccount.STATE.CLOSED;
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return classAccounts.size();
    }

    public void setData(ArrayList<ClassAccount> list){
        classAccounts = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout content;
        TextView name, id;
        ImageView arrow;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            id = itemView.findViewById(R.id.tvId);
            arrow = itemView.findViewById(R.id.imgArrow);
            content = itemView.findViewById(R.id.rlContent);
            view = itemView.findViewById(R.id.viewDashed);
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
