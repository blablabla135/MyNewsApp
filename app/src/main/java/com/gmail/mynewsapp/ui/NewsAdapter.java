package com.gmail.mynewsapp.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.mynewsapp.R;
import com.gmail.mynewsapp.databinding.ListItemBinding;
import com.gmail.mynewsapp.model.room.News;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    private List<News> newsList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @Inject
    public NewsAdapter() {
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item, parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.bind(news, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder {


        private ListItemBinding binding;

        public NewsViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(News news, OnItemClickListener onItemClickListener) {
            binding.setNews(news);
            binding.executePendingBindings();
            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(news);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(News news);
    }
}
