package side_job.app.utilities.Interfaces;


//i'm not implementing no serializer rn, so idk maybe later 
public interface Serializer<T> {
    byte[] serialize(T value);
    T deserialize(byte[] data);
}
