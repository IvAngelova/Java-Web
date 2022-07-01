package com.example.finalexam.util;

public class TimeConverter {
    public static String calculateTime(long seconds) {
        long sec = seconds % 60;
        long minutes = seconds % 3600 / 60;
        long hours = seconds % 86400 / 3600;
      return String.format("%01d:%02d", minutes, sec);
    }
}
