package portal.util;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Component
@SuppressWarnings({"unused", "WeakerAccess"})
public class TransactionDate {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionDate.class);


    @Value("${app.nav:1}")
    @Setter
    private Long nav;

    @Value("${app.cutOff:0}")
    @Setter
    private Long cutOff;

    public Timestamp getMidnight(LocalDateTime date) {
        return Timestamp.valueOf(date.withHour(23).withMinute(59).withSecond(59).withNano(0));
    }

    public Timestamp getMidnight(LocalDate date) {
        return getMidnight(date.atStartOfDay());
    }

    public Timestamp getStartOfDay(LocalDateTime date) {
        return Timestamp.valueOf(date.withHour(0).withMinute(0).withSecond(0).withNano(0));
    }

    public Timestamp getStartOfDay(LocalDate date) {
        return getStartOfDay(date.atStartOfDay());
    }

    public Timestamp now() {
        return toTimestamp(LocalDateTime.now());
    }

    public Timestamp toTimestamp(LocalDate date) {
        return Timestamp.valueOf(date.atStartOfDay());
    }

    public Timestamp toTimestamp(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }

    public LocalDate getTransactionDate(Timestamp transactionDate) {
        LocalTime dayStart = LocalDate.now().atStartOfDay().toLocalTime();
        LocalTime cutoffTime = dayStart.plusHours(cutOff).minusMinutes(15);
        LocalDateTime transactionDateTime = transactionDate.toLocalDateTime();
        LocalDateTime cutOff1 = LocalDateTime.of(transactionDateTime.toLocalDate(), cutoffTime);
        LocalDateTime cutOff2 = cutOff1.minusDays(1);
        if (transactionDateTime.isAfter(cutOff1)) {
            return cutOff1.plusDays(1).toLocalDate();
        } else if (transactionDateTime.isBefore(cutOff1) && transactionDateTime.isAfter(cutOff2)) {
            return cutOff1.toLocalDate();
        }

        MDC.put("request-date", transactionDate.toString());
        MDC.put("result-date", transactionDateTime.toString());
        LOG.debug("requesting transaction date");
        MDC.remove("request-date");
        MDC.remove("result-date");

        return transactionDateTime.toLocalDate();
    }

    public LocalDateTime getFirstDay(LocalDateTime today) {
        return today.toLocalDate().withMonth(1).withDayOfMonth(1).atStartOfDay();
    }

    public LocalDateTime getLastDayOfTheMonth(LocalDateTime firstDay) {
        return firstDay.plusMonths(1).plusDays(-1);
    }

    public LocalDateTime getFirstTime(LocalDateTime date) {
        return date.toLocalDate().withMonth(1).withDayOfMonth(1).atStartOfDay();
    }

    public LocalDateTime getFirstTimeOfMonth(LocalDateTime today) {
        return today.withDayOfMonth(1);
    }

    public LocalDateTime getLastDay(LocalDateTime date) {
        return date.toLocalDate().withMonth(12).withDayOfMonth(31).atTime(23, 59, 59);
    }


    public long getRunningYear(LocalDateTime now, LocalDateTime compared) {
        return compared.until(now, ChronoUnit.YEARS) + 1;
    }

    public long getRunningYear(Timestamp date) {
        return getRunningYear(date.toLocalDateTime());
    }

    public long getRunningYear(LocalDate date) {
        return getRunningYear(date.atStartOfDay());
    }

    public long getRunningYear(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        return getRunningYear(now, date);
    }

}
