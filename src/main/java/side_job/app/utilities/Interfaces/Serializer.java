package side_job.app.utilities.Interfaces;

public interface Serializer<T> {
    byte[] serialize(T value);
    T deserialize(byte[] data);
}
