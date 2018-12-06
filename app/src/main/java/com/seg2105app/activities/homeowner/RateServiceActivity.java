package com.seg2105app.activities.homeowner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seg2105app.activities.R;
import com.seg2105app.services.Service;
import com.seg2105app.users.ServiceProvider;

public class RateServiceActivity extends AppCompatActivity {

    TextView textView;
    EditText ratingBox, commentBox;
    Button submitBtn;
    Service service;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_service);

        service = ServiceSearch.getSelectedService();
        String serviceName = service.getName(); //change to service provider
        ratingBox = findViewById(R.id.ratingBox);
        commentBox = findViewById(R.id.commentBox);
        textView = findViewById(R.id.textView);
        textView.setText("Please give " + serviceName + " a rating on a scale from 1 to 5."); //change to service provider
        submitBtn = findViewById(R.id.submitBtn);
    }

    public void onSubmitClick(View v){
        double rating = 0;
        String comment = null;

        try {
            rating = Double.parseDouble(ratingBox.getText().toString());
            comment = commentBox.getText().toString();
        } catch (NumberFormatException e){
            Toast invalidRating = Toast.makeText(getApplicationContext(), "Invalid rating", Toast.LENGTH_LONG);
            invalidRating.show();
        } catch (NullPointerException e){
            Toast noComment = Toast.makeText(getApplicationContext(), "Enter a comment", Toast.LENGTH_LONG);
            noComment.show();
        }

        if(rating < 1 || rating > 5){
            Toast invalidRating = Toast.makeText(getApplicationContext(), "Invalid rating", Toast.LENGTH_LONG);
            invalidRating.show();
        } else{
            //get service provider from bundle and add the comment and rating using appropriate methods in ServiceProvider
            service.addRating(rating);
            service.addComment(comment);

            Toast ratingSubmitted = Toast.makeText(getApplicationContext(), "Rating Submitted", Toast.LENGTH_LONG);
            ratingSubmitted.show();
            Intent backToHome = new Intent(RateServiceActivity.this, HomeOwnerWelcome.class);
            startActivity(backToHome);
            finish();
        }

    }
}
