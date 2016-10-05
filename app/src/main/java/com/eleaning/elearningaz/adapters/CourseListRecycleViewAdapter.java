package com.eleaning.elearningaz.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eleaning.elearningaz.CourseActivity;
import com.eleaning.elearningaz.LoginActivity;
import com.eleaning.elearningaz.R;
import com.eleaning.elearningaz.application.ElearnApplication;
import com.eleaning.elearningaz.utils.rest.model.course.Course;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

import javax.inject.Inject;

import static com.eleaning.elearningaz.R.id.imageView;


public class CourseListRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    @Inject
    Context appContext;
    @Inject
    LayoutInflater layoutInflater;

    private List<Course> courseList;



    public CourseListRecycleViewAdapter(List<Course> courseList) {
        ElearnApplication.getComponent().inject(this);
        this.courseList = courseList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.horizontal_course_recycle_view_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CourseViewHolder courseViewHolder = (CourseViewHolder) holder;
        final Course content = courseList.get(position);

        courseViewHolder.titleTxtView.setText(content.getTitle());
        courseViewHolder.courseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Tag","view was just clicked");
                Intent intent = new Intent(appContext, CourseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                appContext.startActivity(intent);

            }
        });
        courseViewHolder.avatar.setImageURI(content.getThumbnail());


    }






    @Override
    public int getItemCount() {
        return courseList.size();
    }


    static class CourseViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxtView;
        SimpleDraweeView avatar;
        TextView playBtn;
        CardView courseCard;

        public CourseViewHolder(View view) {
            super(view);
            titleTxtView = (TextView) view.findViewById(R.id.horizontal_content_title);
            avatar = (SimpleDraweeView) view.findViewById(R.id.avatar);
            playBtn = (TextView) view.findViewById(R.id.horizontal_course_play);
            courseCard = (CardView) view.findViewById(R.id.courseCard);
        }
    }



}
