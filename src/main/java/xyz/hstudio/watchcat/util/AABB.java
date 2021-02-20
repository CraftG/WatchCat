package xyz.hstudio.watchcat.util;

import java.util.Objects;

public class AABB {

    public final Vector3D min, max;

    public AABB(Vector3D min, Vector3D max) {
        this.min = min;
        this.max = max;
    }

    public AABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this(new Vector3D(minX, minY, minZ), new Vector3D(maxX, maxY, maxZ));
    }

    public static AABB def() {
        return new AABB(new Vector3D(-0.3, 0, -0.3), new Vector3D(0.3, 1.8, 0.3));
    }

    public AABB add(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        min.add(minX, minY, minZ);
        max.add(maxX, maxY, maxZ);
        return this;
    }

    public AABB add(Vector3D vec) {
        min.add(vec);
        max.add(vec);
        return this;
    }

    public AABB plus(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return new AABB(min.x + minX, min.y + minY, min.z + minZ, max.x + maxX, max.y + maxY, max.z + maxZ);
    }

    public AABB plus(Vector3D vec) {
        return plus(vec.x, vec.y, vec.z, vec.x, vec.y, vec.z);
    }

    public AABB expand(double x, double y, double z) {
        min.subtract(x, y, z);
        max.add(x, y, z);
        return this;
    }

    public AABB shrink(double x, double y, double z) {
        min.add(x, y, z);
        max.subtract(x, y, z);
        return this;
    }

    public boolean collides(AABB other) {
        if (max.x < other.min.x || min.x > other.max.x) {
            return false;
        }
        if (max.y < other.min.y || min.y > other.max.y) {
            return false;
        }
        return !(max.z < other.min.z) && !(min.z > other.max.z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AABB)) {
            return false;
        }
        AABB other = (AABB) obj;
        return Objects.equals(min, other.min) && Objects.equals(max, other.max);
    }

    @Override
    public int hashCode() {
        int result = min.hashCode();
        result = 31 * result + max.hashCode();
        return result;
    }
}