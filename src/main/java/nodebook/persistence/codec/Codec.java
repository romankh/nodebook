package nodebook.persistence.codec;

public interface Codec<T> {
    int encode(T style);

    T decode(Integer style);
}
