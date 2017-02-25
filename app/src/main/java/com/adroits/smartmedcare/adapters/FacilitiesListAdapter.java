package com.adroits.smartmedcare.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.adroits.smartmedcare.R;
import com.adroits.smartmedcare.dbmodels.Facilities;

import java.util.List;

/**
 * Created by Matthew Igho on 6/20/2016.
 */
public class FacilitiesListAdapter extends RecyclerView.Adapter<FacilitiesListAdapter.ViewHolder> {
    private List<Facilities> courses;

    public FacilitiesListAdapter(List<Facilities> courses) {
        this.courses = courses;
    }

    // Define listener member variable
    private static OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public ImageView imageView;
        public ImageView subscriptionStatus;
        private Context context;


        public ViewHolder(Context context, final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.overview);
            imageView = (ImageView) itemView.findViewById(R.id.avatar);
            subscriptionStatus = (ImageView) itemView.findViewById(R.id.subscriptionStatus);
            this.context = context;
            // Attach a click listener to the entire row view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }


    }

    @Override
    public FacilitiesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.courses_course_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context, contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FacilitiesListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Facilities course = courses.get(position);

        // Set item views based on the data model
        TextView textView = viewHolder.title;
        textView.setText(course.getTitle());
        final ImageView imageView = viewHolder.imageView;
        ImageView subscriptionStatus = viewHolder.subscriptionStatus;
        final Context context = viewHolder.context;


        String uri = "@drawable/" + course.getThumbnail();  // where myresource (without the extension) is the file

        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        try {
            Drawable res = context.getResources().getDrawable(imageResource);
            imageView.setImageDrawable(res);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Button button = viewHolder.messageButton;

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
