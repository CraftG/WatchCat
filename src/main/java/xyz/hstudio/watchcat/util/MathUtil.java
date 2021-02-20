package xyz.hstudio.watchcat.util;

import net.minecraft.server.v1_16_R3.MathHelper;

public class MathUtil {

    public static Vector3D getDirection(double yaw, double pitch) {
        float rotX = (float) Math.toRadians(yaw);
        float rotY = (float) Math.toRadians(pitch);
        double xz = MathHelper.cos(rotY);

        double x = -xz * MathHelper.sin(rotX);
        double y = -MathHelper.sin(rotY);
        double z = xz * MathHelper.cos(rotX);
        return new Vector3D(x, y, z);
    }
}