package pinnacle.org.nidd.utils;

import pinnacle.org.nidd.model.Chat;

/**
 * Created by Genius on 11/16/2015.
 */
public interface Builder<T> {
    T build(T type) ;
    T build();

}
