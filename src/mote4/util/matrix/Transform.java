package mote4.util.matrix;

import org.joml.Matrix4f;
import org.joml.Vector4f;

/**
 *
 * @author Peter
 */
public class Transform {
    public ProjectionMatrix projection;
    public ViewMatrix view;
    public ModelMatrix model;
    
    public Transform() {
        projection = new ProjectionMatrix();
        view = new ViewMatrix();
        model = new ModelMatrix();
    }
    public void makeCurrent() {
        projection.makeCurrent();
        view.makeCurrent();
        model.makeCurrent();
    }

    /**
     * Calculates the 2D screen location of a 3D point when passed through this Transform.
     * Useful for creating 2D UI elements that hover over 3D objects.
     * @return
     */
    public float[] get2DCoords(float... coord) {
        Vector4f vec = new Vector4f(coord[0], coord[1], coord[2],0);
        Matrix4f mvp = new Matrix4f();
        //view.matrix.mul(model.matrix,mvp);
        projection.matrix.mul(view.matrix, mvp);
        mvp.mul(model.matrix);
        mvp.transform(vec);
        //vec = vec.mul(projection.matrix).mul(view.matrix).mul(model.matrix);
        return new float[] {vec.x, vec.y, vec.z};
        //return new float[] {.5f,.5f};
    }
}