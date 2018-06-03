package nodebook.service.codec;

public interface Codec<T> {
    int encode(T style);

    T decode(Integer style);
}
