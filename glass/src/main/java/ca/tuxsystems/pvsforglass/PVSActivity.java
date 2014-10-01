package ca.tuxsystems.pvsforglass;

import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

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

    Object3dContainer _object2;
    int _count;

    private SensorManager mSensorManager;
    private Sensor mRotationVector;

    @Override
    public void initScene()
    {
        _object = new Sphere(4f, 15, 10);
        _object.normalsEnabled(false); // .. allows vertex colors to show through
        scene.addChild(_object);
        _object.renderType(RenderType.LINES);

        _object.pointSize( _object.pointSize()+0.12f );
        _object.lineWidth( _object.lineWidth()+0.12f );

        _object.doubleSidedEnabled(true);
        Log.i("sphere position", _object.position().toString());
        Log.i("cam position", scene.camera().position.toString());
        Log.i("cam target", scene.camera().target.toString());
        scene.camera().position.setAll(0, 0, 0f);
        scene.camera().target.setAll(0, 0, 1f);
        scene.camera().frustum.zNear(0.51f);
        scene.camera().frustum.zFar(10f);
        // ( btw, notice how the Scene contains no Lights)
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        mSensorManager.registerListener(this, mRotationVector, SensorManager.SENSOR_DELAY_UI);
        _count = 0;
    }

    @Override
    public void updateScene()
    {
        //_object.rotation().y++;
        _count++;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ROTATION_VECTOR)
            return;

        scene.camera().target.x = event.values[0];
        scene.camera().target.y = event.values[1];

        Log.i("cam target", scene.camera().target.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}