package com.eleaning.elearningaz.adapters;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eleaning.elearningaz.R;
import com.eleaning.elearningaz.application.ElearnApplication;
import com.eleaning.elearningaz.utils.rest.model.course.CourseCategory;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;



public class CategotyListAdapter extends RecyclerView.Adapter<CategotyListAdapter.ViewHolder> {
    private List<CourseCategory> courseCategoryList;


    @Inject
    LayoutInflater layoutInflater;



    @Inject
    Context appContext;

    public CategotyListAdapter(List<CourseCategory> courseCategory) {
        //ElearnApplication.getComponent().inject(this);
        this.courseCategoryList = courseCategory;
    }

    public void refreshAdapterList(List<CourseCategory> courseCategory) {
        this.courseCategoryList = courseCategory;
        notifyDataSetChanged();
    }

    @Override
    public CategotyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ElearnApplication.getComponent().inject(this);
        View contactView = layoutInflater.inflate(R.layout.course_category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategotyListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final CourseCategory courseCategory = courseCategoryList.get(position);

        viewHolder.title.setText(courseCategory.getTitle());



    }

    @Override
    public int getItemCount() {
        return courseCategoryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public RecyclerView module_content_recycler_view;
        public ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            module_content_recycler_view = (RecyclerView) itemView.findViewById(R.id.module_content_recycler_view);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);

        }
    }



}
