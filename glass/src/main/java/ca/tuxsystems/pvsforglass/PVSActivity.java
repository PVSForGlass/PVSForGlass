package ca.tuxsystems.pvsforglass;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.objectPrimitives.Sphere;
import min3d.vos.RenderType;

/**
 * An {@link Activity} showing a tuggable "Hello World!" card.
 * <p>
 * The main content view is composed of a one-card {@link CardScrollView} that provides tugging
 * feedback to the user when swipe gestures are detected.
 * If your Glassware intends to intercept swipe gestures, you should set the content view directly
 * and use a {@link com.google.android.glass.touchpad.GestureDetector}.
 * @see <a href="https://developers.google.com/glass/develop/gdk/touch">GDK Developer Guide</a>
 */
public class PVSActivity extends RendererActivity implements SensorEventListener {
    Object3dContainer _object;
    int _count;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @Override
    public void initScene()
    {
        _object = new Sphere(1f, 15, 10);
        _object.normalsEnabled(false); // .. allows vertex colors to show through
        scene.addChild(_object);
        _object.renderType(RenderType.LINES);

        _object.pointSize( _object.pointSize()+0.12f );
        _object.lineWidth( _object.lineWidth()+0.12f );

        // ( btw, notice how the Scene contains no Lights)

        _count = 0;
    }

    @Override
    public void updateScene()
    {
        _count++;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
