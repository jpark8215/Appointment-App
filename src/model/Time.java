package model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Time {

        public static java.sql.Timestamp getTimeStamp() {
            ZoneId zoneid = ZoneId.of("UTC");
            LocalDateTime localDateTime = LocalDateTime.now(zoneid);

            return Timestamp.valueOf(localDateTime);
        }

        public static java.sql.Date getDate() {

            return java.sql.Date.valueOf(LocalDate.now());
        }
    }

