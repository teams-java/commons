package canonical;

import com.manu.canonical.Canonical;
import com.manu.canonical.Header;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class CanonicalTest {

    @Test
    void canonicalShouldHoldHeaderAndData() {
        Header header = new Header(
                "msg-123",
                "systemA",
                "systemB",
                "USER_CREATED",
                Instant.now(),
                "user-1",
                "ADMIN"
        );

        Canonical<String> canonical = new Canonical<>(header, "payload-data");

        assertThat(canonical.header().messageId()).isEqualTo("msg-123");
        assertThat(canonical.data()).isEqualTo("payload-data");
    }
}