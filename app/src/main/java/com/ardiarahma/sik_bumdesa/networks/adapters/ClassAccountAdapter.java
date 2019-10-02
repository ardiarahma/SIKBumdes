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
    private ArrayList<ClassAccount> accountAll = new ArrayList<>();

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
        final ClassAccount account = accountAll.get(position);
        holder.name.setText(account.getName());
        holder.id.setText(String.valueOf(account.getId()));

        holder.itemView.setTag(R.string.MODEL, "list");
        holder.itemView.setTag(R.string.position, "posistion");

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
        layoutParams.setMargins(((int) convertDpToPixel(20, context)) * accountAll.get(position).getLevel(), layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);


        switch (account.state){
            case CLOSED:
                holder.arrow.setImageResource(R.drawable.svg_arrow_right_filled);
                break;
            case OPENED:
                holder.arrow.setImageResource(R.drawable.svg_arrow_down_filled);
                break;
        }

        if (account.classAccounts.isEmpty()){
            holder.arrow.setVisibility(View.INVISIBLE);
            holder.view.setVisibility(View.VISIBLE);
        }else {
            holder.arrow.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int position = context.getString(R.string.position);
//                ClassAccount rootModel = (ClassAccount) v.getTag(R.string.MODEL);
                ClassAccount rootModel = accountAll.get(position);

                if (rootModel.classAccounts.isEmpty()){
                    return;
                }
                switch (rootModel.state){
                    case CLOSED:
                        accountAll.addAll(position + 1, rootModel.classAccounts);
                        notifyItemRangeInserted(position + 1, rootModel.classAccounts.size());
                        notifyItemRangeChanged(position + rootModel.classAccounts.size(), accountAll.size() - (position + rootModel.classAccounts.size()));
                        notifyItemRangeChanged(position, accountAll.size() - position);
                        rootModel.state = ClassAccount.STATE.OPENED;
                        break;
                    case OPENED:
                        int start = position + 1;
                        int end = accountAll.size();
                        for (int i = start; i < accountAll.size(); i++){
                            ClassAccount model = accountAll.get(i);
                            if (model.level <= rootModel.level){
                                end = i;
                                break;
                            }else {
                                if (model.state == ClassAccount.STATE.OPENED){
                                    model.state = ClassAccount.STATE.CLOSED;
                                }
                            }
                        }
                        if (end != -1){
                            accountAll.subList(start, end).clear();
                            notifyItemRangeRemoved(start, end - start);
                            notifyItemRangeChanged(start, end - start);
                            notifyItemRangeChanged(position, accountAll.size() - position);
                        }

                        rootModel.state = ClassAccount.STATE.CLOSED;
                        break;
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return accountAll.size();
    }

    public void setData(ArrayList<ClassAccount> list){
        accountAll = list;
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
