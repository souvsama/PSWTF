package com.guidewire.pstesting;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class TimeAmount {
    public static final String SECONDS      = "s";
    public static final String MINUTES      = "m";
    public static final String MILLISECONDS = "ms";

    private long amount;  // In milliseconds

    public TimeAmount(String time) {
        initialize(time);
    }

    public static TimeAmount create(String time) {return new TimeAmount(time);}

    public long toMilliseconds() {
        return amount;
    }

    public void setAmount(long time, TimeUnit timeUnit) {
        amount = TimeUnit.MILLISECONDS.convert(time, timeUnit);
    }

    public String toDescriptiveString() {
        DecimalFormat df = new DecimalFormat("#,###,##0.#");
        if (amount < 60000) {
            double unit = roundOneTenth(amount / 1000.0);
            return df.format(unit) + (unit == 1 ? " second" : " seconds");
        } else if (amount < 60000 * 60) {
            double unit = roundOneTenth(amount / 60000.0);
            return df.format(unit) + (unit == 1 ? " minute" : " minutes");
        } else if (amount < 60000 * 60 * 24) {
            double unit = roundOneTenth(amount / (60000.0 * 60));
            return df.format(unit) + (unit == 1 ? " hour" : " hours");
        } else {
            double unit = roundOneTenth(amount / (60000.0 * 60 * 24));
            return df.format(unit) + (unit == 1 ? " day" : " days");
        }
    }

    private void initialize(String time) {
        long timeAmount;
        TimeUnit timeUnit;
        if (time.endsWith(MILLISECONDS)) {
            timeUnit = TimeUnit.MILLISECONDS;
            timeAmount = Long.valueOf(time.substring(0, time.lastIndexOf(MILLISECONDS)));
        } else if (time.endsWith(SECONDS)) {
            timeUnit = TimeUnit.SECONDS;
            timeAmount = Long.valueOf(time.substring(0, time.lastIndexOf(SECONDS)));
        } else if (time.endsWith(MINUTES)) {
            timeUnit = TimeUnit.MINUTES;
            timeAmount = Long.valueOf(time.substring(0, time.lastIndexOf(MINUTES)));
        } else { // Default to milliseconds
            timeUnit = TimeUnit.MILLISECONDS;
            try {
                timeAmount = Long.valueOf(time);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid time amount: " + time);
            }
        }
        this.amount = TimeUnit.MILLISECONDS.convert(timeAmount, timeUnit);
    }

    private static double roundOneTenth(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    public String toString() {
        return toDescriptiveString();
    }
}
