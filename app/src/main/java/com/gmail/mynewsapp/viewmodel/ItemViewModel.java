package com.gmail.mynewsapp.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Browser;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

import com.gmail.mynewsapp.R;
import com.gmail.mynewsapp.model.retrofit.Info;
import com.gmail.mynewsapp.model.room.News;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class ItemViewModel extends ViewModel {

    private String author;
    private String url;
    private String urlToImage;
    private String content;
    private String description;
    private String publishedAt;

    @Inject
    public ItemViewModel() {
    }

    public void setNews(String author, String url, String urlToImage, String content, String description, String publishedAt) {
        this.author = author;
        this.url = url;
        this.urlToImage = urlToImage;
        this.description = description;
        this.publishedAt = publishedAt;
        if (content != null) {
            String[] strings = content.split("\\[");
            this.content = strings[0];
        }

    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).resizeDimen(R.dimen.image_w, R.dimen.image_w).centerCrop().into(view);
    }

    @BindingAdapter({"app:article"})
    public static void openArticle(TextView view, String article) {
        SpannableString string = new SpannableString("Follow to read full article.");
        string.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Context context = view.getContext();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article));
                intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());

                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            }
        }, 0, 28, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        view.setText(string);
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
