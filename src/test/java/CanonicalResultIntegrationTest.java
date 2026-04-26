import com.manu.canonical.Header;
import com.manu.canonical.Canonical;
import com.manu.result.*;
import com.manu.exceptions.BusinessException;
import com.manu.exceptions.TechnicalException;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class CanonicalResultIntegrationTest {

    @Test
    void canonicalShouldWrapSuccessResult() {
        Header header = new Header(
                "msg-001",
                "systemA",
                "systemB",
                "USER_CREATED",
                Instant.now(),
                "user-123",
                "ADMIN"
        );

        Result<String> success = new Success<>("User created successfully");
        Canonical<Result<String>> canonical = new Canonical<>(header, success);

        assertThat(canonical.header().eventType()).isEqualTo("USER_CREATED");
        assertThat(canonical.data()).isInstanceOf(Success.class);
        assertThat(((Success<String>) canonical.data()).value()).isEqualTo("User created successfully");
    }

    @Test
    void canonicalShouldWrapBusinessFailure() {
        Header header = new Header(
                "msg-002",
                "systemA",
                "systemB",
                "USER_CREATION_FAILED",
                Instant.now(),
                "user-456",
                "USER"
        );

        Result<String> failure = new Failure<>(new BusinessException("Invalid user data"));
        Canonical<Result<String>> canonical = new Canonical<>(header, failure);

        assertThat(canonical.header().eventType()).isEqualTo("USER_CREATION_FAILED");
        assertThat(canonical.data()).isInstanceOf(Failure.class);
        assertThat(((Failure<String>) canonical.data()).exception()).isInstanceOf(BusinessException.class);
        assertThat(((Failure<String>) canonical.data()).exception().getMessage()).isEqualTo("Invalid user data");
    }

    @Test
    void canonicalShouldWrapTechnicalFailure() {
        Header header = new Header(
                "msg-003",
                "systemA",
                "systemB",
                "SYSTEM_ERROR",
                Instant.now(),
                "user-789",
                "SUPPORT"
        );

        Result<String> failure = new Failure<>(new TechnicalException("Database unavailable"));
        Canonical<Result<String>> canonical = new Canonical<>(header, failure);

        assertThat(canonical.header().eventType()).isEqualTo("SYSTEM_ERROR");
        assertThat(canonical.data()).isInstanceOf(Failure.class);
        assertThat(((Failure<String>) canonical.data()).exception()).isInstanceOf(TechnicalException.class);
        assertThat(((Failure<String>) canonical.data()).exception().getMessage()).isEqualTo("Database unavailable");
    }
}
