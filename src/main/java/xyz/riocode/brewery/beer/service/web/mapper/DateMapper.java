package xyz.riocode.brewery.beer.service.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime asOffsetDateTime(Timestamp t) {
        if (t != null) {
            return OffsetDateTime.of(t.toLocalDateTime().getYear(), t.toLocalDateTime().getMonthValue(),
                    t.toLocalDateTime().getDayOfMonth(), t.toLocalDateTime().getHour(), t.toLocalDateTime().getMinute(),
                    t.toLocalDateTime().getSecond(), t.toLocalDateTime().getNano(), ZoneOffset.UTC);
        } else {
            return null;
        }
    }

    public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }
}
