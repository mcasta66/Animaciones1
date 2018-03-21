package com.example.cfgs.animaciones1;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView sun;
    private AnimatorSet sunSet;
    private ImageView fish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reconocemos el image view
        sun = (ImageView) this.findViewById(R.id.sun);
        // inflamos el XML de la animación
        sunSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.sun_moving);
        // seleccionamos el objetivo de la animación inflada
        sunSet.setTarget(sun);

        fish = (ImageView) this.findViewById(R.id.fish);
        
    }

    public void sun_animation(View v) {
        // ejecutamos la animación con el objetivo ya establecido
        sunSet.start();

    }

    public void sun_animation_stop(View v) {
        // ejecutamos la animación con el objetivo ya establecido
        sunSet.end();
        //sunSet.cancel();
        sunSet.setupEndValues();
    }

    public void fish_animation1(View v) {
        // declaramos un interpolador de keyframes, en este caso un interpolador
        // linear porque es el más básico y simple que hay.
        TimeInterpolator inter = new LinearInterpolator();

        // Esta variable será la animación a representar.
        // Como parámetros le pasamos: el objeto objetivo, el tipo de propiedad a animar (en este
        // caso, la propiedad de rotación), los grados desde los que empieza (360), hasta los grados
        // finales.
        // Dependiendo de cuál sea mayor, giraremos horaria o antihorariamente.
        ValueAnimator rotateFish = ObjectAnimator.ofFloat(fish, "rotation", 360, 0);
        rotateFish.setDuration(3000);
        rotateFish.setRepeatMode(ValueAnimator.REVERSE);
        rotateFish.setRepeatCount(1); // ValueAnimator.INFINITE : animación infinita
        rotateFish.setInterpolator(inter); // establecemos el interpolador
        AnimatorSet fishSet = new AnimatorSet();
        // Añadimos la animación dentro del AnimatorSet
        fishSet.play(rotateFish);
        // Ejecutamos el set y por ende, nuestra animación
        fishSet.start();
    }

    public void fish_animation2(View v) {
        TimeInterpolator inter = new LinearInterpolator();
        // Esta es la animación que se encarga del desplazamiento
        ValueAnimator moveFish = ObjectAnimator.ofFloat(fish, "x", 300);
        moveFish.setDuration(3000)
                .setRepeatMode(ValueAnimator.REVERSE); // al llegar al final, volverá al inicio
        moveFish.setRepeatCount(1);
        moveFish.setInterpolator(inter);
        ValueAnimator rotateFish = ObjectAnimator.ofFloat(fish, "rotation", 360, 0);
        rotateFish.setDuration(3000)
                .setRepeatCount(1); // animación infinita
        rotateFish.setRepeatMode(ValueAnimator.REVERSE);
        rotateFish.setInterpolator(inter); // establecemos el interpolador
        AnimatorSet fishSet = new AnimatorSet();
        // sustituimos la línea "fishSet.play(...)" anterior por esta...
        fishSet.playTogether(rotateFish, moveFish); // se ejecutarán a la vez
        //fishSet.play( moveFish ).before( rotateFish );

        // Ejecutamos el set y por ende, nuestra animación
        fishSet.start();
    }

    public void fish_animation3(View v) {
        // comenzamos con el objeto visible y lo vamos haciendo invisible
        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(fish, "alpha", 1f, 0f);
        fadeAnim.setDuration(3000);
        fadeAnim.setRepeatCount(1);
        fadeAnim.setRepeatMode(ValueAnimator.REVERSE);
        AnimatorSet fishSet = new AnimatorSet();
        fishSet.play(fadeAnim);
        fishSet.start();

    }

    public void fish_animation4(View v) {
        // animación: de un color a otro color
        ValueAnimator skyAnim = ObjectAnimator.ofInt(findViewById(R.id.sky), "backgroundColor", Color.rgb(0x66, 0xcc, 0xff), Color.rgb(0x00, 0x66, 0x99));

        skyAnim.setDuration(3000);
        skyAnim.setRepeatCount(1);
        skyAnim.setRepeatMode(ValueAnimator.REVERSE);

        skyAnim.setEvaluator(new ArgbEvaluator()); // parecido al interpolador en cuanto a función

        skyAnim.start();
    }

    public void fish_animation_stop(View v) {
        // ejecutamos la animación con el objetivo ya establecido
        //fishSet.end();

    }
}
