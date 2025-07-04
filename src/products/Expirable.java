package products;

import java.time.LocalDateTime;

public interface Expirable {
    LocalDateTime getExpirationDate();

    boolean isExpired();
}
