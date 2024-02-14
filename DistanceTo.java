import java.util.Objects;
public class DistanceTo implements Comparable<DistanceTo> {
    private final String target;
    private final int distance;

    public DistanceTo(String target, int distance) {
        this.target = target;
        this.distance = distance;
    }

    public String getTarget() {
        return target;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(DistanceTo other) {
        int distanceCompare = Integer.compare(this.distance, other.distance);
        if (distanceCompare == 0) {
            return this.target.compareTo(other.target);
        }
        return distanceCompare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistanceTo that = (DistanceTo) o;
        return distance == that.distance && Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(target, distance);
    }
}