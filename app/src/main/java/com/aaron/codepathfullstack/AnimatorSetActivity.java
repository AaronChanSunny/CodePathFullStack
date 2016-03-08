package com.aaron.codepathfullstack;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AnimatorSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        findViewById(R.id.animation_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animationSet = new AnimatorSet();
                animationSet.playTogether(ObjectAnimator.ofFloat(v, View.SCALE_X, 1.0f, 2.0f)
                                .setDuration(2000),
                        ObjectAnimator.ofFloat(v, View.SCALE_Y, 1.0f, 2.0f).setDuration(2000),
                        ObjectAnimator.ofObject(v, "backgroundColor", new ArgbEvaluator(),
                                /*Red*/0xFFFF8080, /*Blue*/0xFF8080FF)
                                .setDuration(2000));
                animationSet.start();
            }
        });

        findViewById(R.id.animation_set1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define first set of animations
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(v, "scaleX", 2.0f);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(v, "scaleY", 2.0f);
                AnimatorSet set1 = new AnimatorSet();
                set1.playTogether(anim1, anim2);
                // Define second set of animations
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(v, "X", 100);
                ObjectAnimator anim4 = ObjectAnimator.ofFloat(v, "Y", 100);
                AnimatorSet set2 = new AnimatorSet();
                set2.playTogether(anim3, anim4);
                // Play the animation sets one after another
                AnimatorSet set3 = new AnimatorSet();
                set3.playSequentially(set1, set2);
                set3.start();
            }
        });

        findViewById(R.id.animation_set2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create two animations to play together
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(v, "scaleX", 2.0f);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(v, "scaleY", 2.0f);
                // Construct set 1 playing together
                AnimatorSet bouncer = new AnimatorSet();
                bouncer.play(anim1).with(anim2);
                // Create second animation to play after
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
                fadeAnim.setDuration(250);
                // Play bouncer before fade
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(bouncer).before(fadeAnim);
                animatorSet.start();
            }
        });
    }

}
