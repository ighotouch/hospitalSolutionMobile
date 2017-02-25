package com.adroits.smartmedcare.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adroits.smartmedcare.R;
import com.adroits.smartmedcare.utils.rest.model.Information;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Matthew Igho on 6/20/2016.
 */
public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {
    private List<Information> courses;

    public InformationAdapter(List<Information> courses) {
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
        public SimpleDraweeView imageView;
        public ImageView subscriptionStatus;
        private Context context;



        public ViewHolder(Context context, final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.overview);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.avatar);
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
    public InformationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.information_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(context,contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InformationAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Information course = courses.get(position);

        // Set item views based on the data model
        TextView textView = viewHolder.title;
        TextView description = viewHolder.description;
        textView.setText(course.getHeader());
        description.setText(course.getContent());
        final SimpleDraweeView imageView = viewHolder.imageView;

        Uri uri = Uri.parse(course.getThumbnail());
        imageView.setImageURI(uri);



        //Button button = viewHolder.messageButton;

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
