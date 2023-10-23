package metropolisInterview;

import java.util.Objects;

/**
 * Created by Andre Ticona.
 * Date: 3/30/2023
 */
class Key {
    private final Integer siteId;
    private final Integer weekNumber;

    public Key(Integer siteId, Integer weekNumber) {
        this.siteId = siteId;
        this.weekNumber = weekNumber;
    }

    public int getSiteId() {
        return siteId;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return Objects.equals(siteId, key.siteId) && Objects.equals(weekNumber, key.weekNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siteId, weekNumber);
    }

    @Override
    public String toString() {
        return "Key{" +
                "siteId=" + siteId +
                ", weekNumber=" + weekNumber +
                '}';
    }
}
